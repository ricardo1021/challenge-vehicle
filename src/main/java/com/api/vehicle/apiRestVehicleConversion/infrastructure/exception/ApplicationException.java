package com.api.vehicle.apiRestVehicleConversion.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApplicationException extends RuntimeException{

    private final String code;
    private final String message;
    private final HttpStatus status;

}
