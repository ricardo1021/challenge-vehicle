package com.api.vehicle.apiRestVehicleConversion.application.service.impl;

import com.api.vehicle.apiRestVehicleConversion.application.dto.SumAccumulator;
import com.api.vehicle.apiRestVehicleConversion.application.mapper.Mapper;
import com.api.vehicle.apiRestVehicleConversion.domain.model.QuotationModel;
import com.api.vehicle.apiRestVehicleConversion.domain.model.VehicleVersion;
import com.api.vehicle.apiRestVehicleConversion.domain.model.api.*;
import com.api.vehicle.apiRestVehicleConversion.domain.model.cache.QuotationCacheModel;
import com.api.vehicle.apiRestVehicleConversion.domain.port.cache.QuotationCacheRepository;
import com.api.vehicle.apiRestVehicleConversion.domain.port.external.CarModelConnector;
import com.api.vehicle.apiRestVehicleConversion.domain.port.external.CryptoCurrencyConnector;
import com.api.vehicle.apiRestVehicleConversion.domain.port.repository.ModelRepository;
import com.api.vehicle.apiRestVehicleConversion.domain.port.repository.QuotationRepository;
import com.api.vehicle.apiRestVehicleConversion.domain.port.service.QuotationService;
import com.api.vehicle.apiRestVehicleConversion.domain.port.service.ReportService;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.exception.ApplicationError;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.function.BiFunction;

@RequiredArgsConstructor
@Slf4j
public class VehicleQuotationServiceImpl implements QuotationService, ReportService {

    private final QuotationCacheRepository quotationCacheRepository;

    private final CarModelConnector carModelConnector;

    private final ModelRepository modelRepository;

    private final QuotationRepository quotationRepository;

    private final CryptoCurrencyConnector cryptoCurrencyConnector;

    @Override
    public Mono<VehicleApiModelConversionResponse> getResponseVehicleQuote(VehicleApiModelConversionRequest vehicleApiModelConversionRequest) {

        return Mono.zip(modelRepository.findByName(vehicleApiModelConversionRequest.getModel()),
                        cryptoCurrencyConnector.getCryptoCurrencyPriceByCurrency(vehicleApiModelConversionRequest.getCryptocurrency(), "USD"))
                .onErrorMap(throwable -> new ApplicationException("400", throwable.getMessage(), HttpStatus.BAD_REQUEST))
                .flatMap(objects -> carModelConnector.getCarModelByIdAndModel(objects.getT1().getIdModel(), objects.getT1().getIdVehicle())
                        .map(carModel -> {
                                    BigDecimal priceCrypto = objects.getT2().getPriceUsd();
                                    VehicleVersion vehicleVersion = new VehicleVersion();
                                    vehicleVersion.setModel(vehicleApiModelConversionRequest.getModel());
                                    vehicleVersion.setVersion(carModel.getVersion());
                                    vehicleVersion.setPriceUsd(carModel.getPriceUsd());
                                    vehicleVersion.setPriceCryptocurrency(carModel.getPriceUsd().setScale(3, RoundingMode.HALF_UP).divide(priceCrypto, RoundingMode.HALF_UP));
                                    vehicleVersion.setCryptocurrency(vehicleApiModelConversionRequest.getCryptocurrency().getName());
                                    log.info(vehicleVersion.toString());
                                    return vehicleVersion;
                                }
                        )
                        .collectList()
                        .flatMap(carModels -> {
                            QuotationCacheModel data = new QuotationCacheModel();
                            data.setVersions(new HashSet<>(carModels));
                            return quotationCacheRepository.saveQuote(data)
                                    .map(s -> {
                                        data.setConvertionId(s);
                                        return data;
                                    });
                        }))
                .map(quoteCacheModel -> Mapper.INSTANCE.mapToResponseQuoteApiModel(quoteCacheModel));
    }

    @Override
    public Mono<VehicleApiModelPurchaseResponse> getResponseVehicleOrder(VehicleApiModelPurchaseRequest vehicleApiModelPurchaseRequest) {
        return this.quotationCacheRepository.findQuote(vehicleApiModelPurchaseRequest.getConvertionId())
                .switchIfEmpty(Mono.error(ApplicationError.QUOTE_NOT_FOUND))
                .map(quote -> quote.getVersions().stream().filter(version -> version.getVersion().equals(vehicleApiModelPurchaseRequest.getVersion())
                                && version.getCryptocurrency().equals(vehicleApiModelPurchaseRequest.getCryptocurrency())
                                && version.getModel().equals(vehicleApiModelPurchaseRequest.getModel()))
                        .findFirst().orElseThrow(() -> ApplicationError.QUOTE_INCORRECT))
                .switchIfEmpty(Mono.error(ApplicationError.QUOTE_INCORRECT))
                .map(Mapper.INSTANCE::mapToQuotePersistenceModel)
                .flatMap(quotePersistenceModel -> {
                    quotePersistenceModel.setCreateDate(new Date());
                    quotePersistenceModel.setCryptocurrency(vehicleApiModelPurchaseRequest.getCryptocurrency());
                    quotePersistenceModel.setFullName(vehicleApiModelPurchaseRequest.getFullName());
                    return this.quotationRepository.saveQuotePersistenceModel(quotePersistenceModel);
                })
                .map(quote -> Mapper.INSTANCE.mapToResponseOrderApiModel(quote));

    }

    @Override
    public Flux<VehicleApiModelReportResponse> getResponseGenerateReportApiModelFlux(VehicleApiModelReportRequest vehicleApiModelReportRequest) {

        BiFunction<SumAccumulator, QuotationModel, SumAccumulator> accumulator = (sum, item) -> {
            sum.addPriceUsd(item.getPriceUsd());
            sum.addPriceCryptocurrency(item.getPriceCryptocurrency());
            return sum;
        };
        log.info("Inicia proceso generación de reporte {}",vehicleApiModelReportRequest.toString() );
        return quotationRepository.findQuotePersistenceModelBy(vehicleApiModelReportRequest.getModel(), vehicleApiModelReportRequest.getCryptocurrency().getName(), vehicleApiModelReportRequest.getDate())
                .groupBy(item -> item.getModel() + "#" + item.getVersion() + "#" + item.getCryptocurrency() )
                .flatMap(group -> group
                        .reduce(new SumAccumulator(), accumulator)
                        .map(sum -> new VehicleApiModelReportResponse(
                                group.key().split("#")[0],
                                group.key().split("#")[1],
                                group.key().split("#")[2],
                                sum.getPriceUsdSum(),
                                sum.getPriceCryptocurrencySum(),
                                vehicleApiModelReportRequest.getDate()
                        ))
                )
                .map(responseGenerateReportApiModel -> responseGenerateReportApiModel);
    }

}
