package com.api.vehicle.apiRestVehicleConversion.domain.model.external;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarExternalModel implements Serializable {

    private String version;
    private BigDecimal priceUsd;

}
