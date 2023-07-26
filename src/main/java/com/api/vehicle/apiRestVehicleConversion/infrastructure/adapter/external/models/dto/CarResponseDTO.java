package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarResponseDTO implements Serializable {

    @JsonProperty(value = "CODIGO")
    private Integer code;

    @JsonProperty("NOMBRE")
    private String name;

    @JsonProperty("ANIO")
    private Integer anio;

    @JsonProperty("PRECIO_PVP")
    private BigDecimal pricePvp;

    @JsonProperty("BONO")
    private BigDecimal bono;

    @JsonProperty("PRECIO_FINAL")
    private BigDecimal priceUsd;

    @JsonProperty("DISCAPACIDAD_100")
    private Integer disabilityPercentage;

    @JsonProperty("COD_SGC")
    private String codeSgc;
}
