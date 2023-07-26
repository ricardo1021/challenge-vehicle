package com.api.vehicle.apiRestVehicleConversion.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarModel {

    private String version;

    private String idModel;

    private String idVehicle;

    private BigDecimal priceUsd;

}
