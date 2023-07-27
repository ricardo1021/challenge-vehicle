package com.api.vehicle.apiRestVehicleConversion.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("public.catalog_service_model")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CatalogEntity {

    @Id
    private Long id;

    private String name;

    private String idModel;

    private String idVehicle;
}
