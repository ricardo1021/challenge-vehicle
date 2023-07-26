package com.api.vehicle.apiRestVehicleConversion.domain.model.external;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CryptocurrencyExternalModel implements Serializable {

    private String code;
    private BigDecimal priceUsd;
}
