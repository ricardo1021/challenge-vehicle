package com.api.vehicle.apiRestVehicleConversion.domain.model.api;

import com.api.vehicle.apiRestVehicleConversion.domain.model.enums.CryptoCurrencyEnum;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class VehicleApiModelConversionRequest implements Serializable {

    private String model;
    private CryptoCurrencyEnum cryptocurrency;

}
