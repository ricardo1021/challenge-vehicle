package com.api.vehicle.apiRestVehicleConversion.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CryptoCurrencyEnum {

    BTC("BTC", "90"),
    ETH("ETH", "80");

    private final String name;
    private final String value;



    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static CryptoCurrencyEnum fromName(String name) {
        for (CryptoCurrencyEnum currency : CryptoCurrencyEnum.values()) {
            if (currency.name.equalsIgnoreCase(name)) {
                return currency;
            }
        }
//        throw "CRYPTOCURRENCY_NOT_FOUND";
        throw new RuntimeException();
    }

}
