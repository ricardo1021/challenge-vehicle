package com.api.vehicle.apiRestVehicleConversion.util;

import com.api.vehicle.apiRestVehicleConversion.domain.model.CarModel;
import com.api.vehicle.apiRestVehicleConversion.domain.model.QuotationModel;
import com.api.vehicle.apiRestVehicleConversion.domain.model.VehicleVersion;
import com.api.vehicle.apiRestVehicleConversion.domain.model.api.*;
import com.api.vehicle.apiRestVehicleConversion.domain.model.cache.QuotationCacheModel;
import com.api.vehicle.apiRestVehicleConversion.domain.model.enums.CryptoCurrencyEnum;
import com.api.vehicle.apiRestVehicleConversion.domain.model.external.CarExternalModel;
import com.api.vehicle.apiRestVehicleConversion.domain.model.external.CryptocurrencyExternalModel;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.cache.entity.VersionRedis;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class MockData {

    public static CarModel getCarPersistenceModel() {
        CarModel carPersistenceModel = new CarModel();
        carPersistenceModel.setIdModel(Constants.ACCENT_MODEL);
        carPersistenceModel.setIdVehicle(Constants.ID_VERSION);
        carPersistenceModel.setVersion(Constants.VERSION_ACCENT_1);
        carPersistenceModel.setPriceUsd(new BigDecimal("100000"));
        return carPersistenceModel;

    }

    public static CarExternalModel getCarModelExternalModel1() {
        CarExternalModel carExternalModel = new CarExternalModel();
        carExternalModel.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1);
        carExternalModel.setVersion(Constants.VERSION_ACCENT_1);

        return carExternalModel;
    }

    public static CarExternalModel getCarModelExternalModel2() {
        CarExternalModel carExternalModel2 = new CarExternalModel();
        carExternalModel2.setPriceUsd(Constants.PRICE_VERSION_ACCENT_2);
        carExternalModel2.setVersion(Constants.VERSION_ACCENT_2);

        return carExternalModel2;
    }

    public static List<CarExternalModel> getCarModelExternalModel() {
        return Arrays.asList(getCarModelExternalModel1(), getCarModelExternalModel2());
    }

    public static VehicleApiModelConversionRequest getRequestGenerateQuoteApiModel() {
        VehicleApiModelConversionRequest request = new VehicleApiModelConversionRequest();
        request.setModel(Constants.ACCENT_MODEL);
        request.setCryptocurrency(CryptoCurrencyEnum.BTC);
        return request;
    }

    public static CryptocurrencyExternalModel getCryptocurrencyExternalModel() {
        CryptocurrencyExternalModel cryptocurrencyExternalModel = new CryptocurrencyExternalModel();
        cryptocurrencyExternalModel.setCode(Constants.CRYPTOCURRENCY_BTC);
        cryptocurrencyExternalModel.setPriceUsd(Constants.PRICE_CRYPTOCURRENCY);
        return cryptocurrencyExternalModel;
    }

    public static QuotationCacheModel getQuoteCacheModel() {
        QuotationCacheModel quoteCacheModel = new QuotationCacheModel();
        quoteCacheModel.setConvertionId(Constants.CONVERTION_ID);
        quoteCacheModel.setConversionTimelife(Constants.CONVERTION_TTL);
        quoteCacheModel.setVersions(getVersions());
        return quoteCacheModel;
    }

    private static Set<VehicleVersion> getVersions() {
        VehicleVersion version = new VehicleVersion();
        version.setVersion(Constants.VERSION_ACCENT_1);
        version.setModel(Constants.ACCENT_MODEL);
        version.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        version.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        version.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1);
        VehicleVersion version2 = new VehicleVersion();
        version2.setVersion(Constants.VERSION_ACCENT_2);
        version2.setModel(Constants.ACCENT_MODEL);
        version2.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        version2.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        version2.setPriceUsd(Constants.PRICE_VERSION_ACCENT_2);
        return Set.of(version, version2);
    }

    private static Set<VersionRedis> getVersionRedis() {
        VersionRedis versionRedis = new VersionRedis();
        versionRedis.setVersion(Constants.VERSION_ACCENT_1);
        versionRedis.setModel(Constants.ACCENT_MODEL);
        versionRedis.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        versionRedis.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        versionRedis.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1);
        VersionRedis version2 = new VersionRedis();
        version2.setVersion(Constants.VERSION_ACCENT_2);
        version2.setModel(Constants.ACCENT_MODEL);
        version2.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        version2.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        version2.setPriceUsd(Constants.PRICE_VERSION_ACCENT_2);
        return Set.of(versionRedis, version2);
    }

    public static QuotationModel getQuotePersistenceModel() {
        QuotationModel quotePersistenceModel = new QuotationModel();
        quotePersistenceModel.setModel(Constants.ACCENT_MODEL);
        quotePersistenceModel.setFullName(Constants.FULL_NAME);
        quotePersistenceModel.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        quotePersistenceModel.setCreateDate(new Date());
        quotePersistenceModel.setId(1);
        quotePersistenceModel.setPurchaseId(UUID.randomUUID().toString());
        quotePersistenceModel.setVersion(Constants.VERSION_ACCENT_1);
        quotePersistenceModel.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        quotePersistenceModel.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1);
        return quotePersistenceModel;
    }

    public static QuotationModel getQuotePersistenceModel(String model, String version, BigDecimal priceCryptocurrency, BigDecimal priceUsd) {
        QuotationModel quote = new QuotationModel();
        quote.setModel(model);
        quote.setVersion(version);
        quote.setFullName(Constants.FULL_NAME);
        quote.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        quote.setCreateDate(new Date());
        quote.setId(1);
        quote.setPurchaseId(UUID.randomUUID().toString());
        quote.setPriceCryptocurrency(priceCryptocurrency);
        quote.setPriceUsd(priceUsd);
        return quote;
    }

    public static VehicleApiModelPurchaseRequest getRequestSaveQuoteApiModel() {
        VehicleApiModelPurchaseRequest requestSaveQuoteApiModel = new VehicleApiModelPurchaseRequest();
        requestSaveQuoteApiModel.setModel(Constants.ACCENT_MODEL);
        requestSaveQuoteApiModel.setVersion(Constants.VERSION_ACCENT_1);
        requestSaveQuoteApiModel.setCryptocurrency(Constants.CRYPTOCURRENCY_BTC);
        requestSaveQuoteApiModel.setConvertionId(UUID.randomUUID().toString());
        requestSaveQuoteApiModel.setFullName(Constants.FULL_NAME);
        return requestSaveQuoteApiModel;
    }

    public static VehicleApiModelReportRequest getRequestGenerateReportApiModel() {
        VehicleApiModelReportRequest requestGenerateReportApiModel = new VehicleApiModelReportRequest();
        requestGenerateReportApiModel.setCryptocurrency(CryptoCurrencyEnum.BTC);
        requestGenerateReportApiModel.setModel(Constants.ACCENT_MODEL);
        requestGenerateReportApiModel.setDate(LocalDate.now());
        return requestGenerateReportApiModel;
    }


    public static VehicleApiModelConversionResponse getResponseGenerateQuoteApiModel() {
        VehicleApiModelConversionResponse responseGenerateQuoteApiModel = new VehicleApiModelConversionResponse();
        responseGenerateQuoteApiModel.setConversionTimelife(Constants.CONVERTION_TTL);
        responseGenerateQuoteApiModel.setVersions(getVersions());
        responseGenerateQuoteApiModel.setConvertionId(UUID.randomUUID().toString());
        return responseGenerateQuoteApiModel;
    }

    public static VehicleApiModelPurchaseResponse getResponseSaveQuoteApiModel() {
        VehicleApiModelPurchaseResponse responseSaveQuoteApiModel = new VehicleApiModelPurchaseResponse();
        responseSaveQuoteApiModel.setModel(Constants.ACCENT_MODEL);
        responseSaveQuoteApiModel.setCryptocurrency(CryptoCurrencyEnum.BTC);
        responseSaveQuoteApiModel.setDate("2023-12-05");
        responseSaveQuoteApiModel.setVersion(Constants.VERSION_ACCENT_1);
        responseSaveQuoteApiModel.setFullName(Constants.FULL_NAME);
        responseSaveQuoteApiModel.setPriceCryptocurrency(Constants.PRICE_CRYPTOCURRENCY);
        responseSaveQuoteApiModel.setPriceUsd(Constants.PRICE_VERSION_ACCENT_1.toString());
        responseSaveQuoteApiModel.setPurchaseId(UUID.randomUUID().toString());
        return responseSaveQuoteApiModel;
    }

}
