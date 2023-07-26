package com.api.vehicle.apiRestVehicleConversion.infrastructure.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private String code;
    private String message;
}
