package com.api.vehicle.apiRestVehicleConversion.domain.model.cache;

import com.api.vehicle.apiRestVehicleConversion.domain.model.VehicleVersion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuotationCacheModel {

    private String convertionId;
    private String conversionTimelife;
    private Set<VehicleVersion> versions;

}
