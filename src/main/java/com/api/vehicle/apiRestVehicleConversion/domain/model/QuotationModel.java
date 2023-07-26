package com.api.vehicle.apiRestVehicleConversion.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuotationModel {

    private Integer id;

    private String fullName;

    private Date createDate;

    private String model;

    private String version;

    private String cryptocurrency;

    private BigDecimal priceUsd;

    private BigDecimal priceCryptocurrency;

    private String purchaseId;
}
