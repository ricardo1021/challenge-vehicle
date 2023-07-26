package com.api.vehicle.apiRestVehicleConversion.domain.model.api;

import com.api.vehicle.apiRestVehicleConversion.domain.model.VehicleVersion;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class VehicleApiModelConversionResponse implements Serializable {

    private String convertionId;
    private String conversionTimelife;
    private Set<VehicleVersion> versions;

}
