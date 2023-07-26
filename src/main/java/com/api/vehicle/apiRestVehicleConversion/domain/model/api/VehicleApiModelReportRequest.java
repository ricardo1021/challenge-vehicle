package com.api.vehicle.apiRestVehicleConversion.domain.model.api;

import com.api.vehicle.apiRestVehicleConversion.domain.model.enums.CryptoCurrencyEnum;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class VehicleApiModelReportRequest implements Serializable {

    private LocalDate date;
    private String model;
    private CryptoCurrencyEnum cryptocurrency;
}
