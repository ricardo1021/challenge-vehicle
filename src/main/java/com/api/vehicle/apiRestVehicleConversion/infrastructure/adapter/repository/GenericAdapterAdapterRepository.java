package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.repository;

import com.api.vehicle.apiRestVehicleConversion.domain.model.CarModel;
import com.api.vehicle.apiRestVehicleConversion.domain.model.QuotationModel;
import com.api.vehicle.apiRestVehicleConversion.domain.port.repository.ModelRepository;
import com.api.vehicle.apiRestVehicleConversion.domain.port.repository.QuotationRepository;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.MapperRepository;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.exception.ApplicationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenericAdapterAdapterRepository implements QuotationRepository, ModelRepository {

    private final ModelAdapterRepository modelAdapterRepository;

    private final QuoteAdapterRepository quoteAdapterRepository;

    @Override
    public Mono<CarModel> findByName(String name) {
        return modelAdapterRepository.findByName(name)
                .map(MapperRepository.INSTANCE::mapToCarModel)
                .switchIfEmpty(Mono.error(ApplicationError.NOT_FOUND_REGISTER))
                .doOnSuccess(carPersistenceModel -> log.info("Consulta realizada con éxito"))
                .doOnError(throwable -> throwable.printStackTrace());
    }

    @Override
    public Mono<QuotationModel> saveQuotePersistenceModel(QuotationModel quoteModel) {
        return quoteAdapterRepository.save(MapperRepository.INSTANCE.mapToQuote(quoteModel))
                .map(quote -> {
                    quoteModel.setPurchaseId(quote.getPurchaseId());
                    return quoteModel;
                });
    }

    @Override
    public Flux<QuotationModel> findQuotePersistenceModelBy(String model, String cryptocurrency, LocalDate date) {
        return quoteAdapterRepository.findByModelVersionAndDate(model, cryptocurrency, date)
                .map(quoteEntity -> MapperRepository.INSTANCE.mapToQuotePersistenceModel(quoteEntity))
                .map(quotePersistenceModel -> {
                    log.info(quotePersistenceModel.toString());
                    return quotePersistenceModel;
                })
                ;
    }
}
