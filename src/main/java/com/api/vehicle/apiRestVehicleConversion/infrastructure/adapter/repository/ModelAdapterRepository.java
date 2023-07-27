package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.repository;

import com.api.vehicle.apiRestVehicleConversion.infrastructure.entity.CatalogEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ModelAdapterRepository extends ReactiveCrudRepository<CatalogEntity, Long> {

    Mono<CatalogEntity> findByName(String name);

}
