package com.api.vehicle.apiRestVehicleConversion.domain.model.api;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class VehicleApiModelReportResponse implements Serializable {

    private String model;
    private String version;
    private String cryptocurrency;
    private BigDecimal usdAmount;
    private BigDecimal cryptocurrencyAmount;
    private LocalDate date;

}
