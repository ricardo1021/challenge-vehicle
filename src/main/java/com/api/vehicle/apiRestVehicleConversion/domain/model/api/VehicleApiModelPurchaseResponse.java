package com.api.vehicle.apiRestVehicleConversion.domain.model.api;

import com.api.vehicle.apiRestVehicleConversion.domain.model.enums.CryptoCurrencyEnum;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class VehicleApiModelPurchaseResponse implements Serializable {

    private String fullName;
    private String version;
    private String model;
    private CryptoCurrencyEnum cryptocurrency;
    private String priceUsd;
    private BigDecimal priceCryptocurrency;
    private String date;
    private String purchaseId;

}
