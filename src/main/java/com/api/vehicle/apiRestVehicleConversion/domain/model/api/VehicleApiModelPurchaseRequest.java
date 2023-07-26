package com.api.vehicle.apiRestVehicleConversion.domain.model.api;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class VehicleApiModelPurchaseRequest implements Serializable {

    private String convertionId;
    private String fullName;
    private String version;
    private String model;
    private String cryptocurrency;

}
