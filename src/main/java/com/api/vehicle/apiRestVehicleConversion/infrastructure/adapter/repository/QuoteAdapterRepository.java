package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.repository;

import com.api.vehicle.apiRestVehicleConversion.infrastructure.entity.QuotationEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface QuoteAdapterRepository extends ReactiveCrudRepository<QuotationEntity, Long> {

    @Query("SELECT * FROM public.quote WHERE " +
            "(model = :param1) " +
            "AND (cryptocurrency = :param2) " +
            "AND DATE_TRUNC('day', create_date) = DATE_TRUNC('day',:param3)")
    Flux<QuotationEntity> findByModelVersionAndDate(@Param("param1") String model, @Param("param2") String cryptocurrency, @Param("param3")LocalDate localDate);


}
