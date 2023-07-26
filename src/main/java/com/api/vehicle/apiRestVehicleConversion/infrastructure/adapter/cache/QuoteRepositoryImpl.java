package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.cache;

import com.api.vehicle.apiRestVehicleConversion.domain.model.cache.QuotationCacheModel;
import com.api.vehicle.apiRestVehicleConversion.domain.port.cache.QuotationCacheRepository;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.cache.entity.QuoteRedis;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Component
@Import({RedisConfiguration.class})
@Slf4j
public class QuoteRepositoryImpl implements QuotationCacheRepository {

    @Autowired
    private CacheMapper quoteMapper;

    @Value("${entity.cache.ttl}")
    private Integer ttl;

    private final ReactiveRedisTemplate<String, QuoteRedis> redisTemplate;
    private final ReactiveHashOperations<String, String, String> hashOperations;

    private final String KEY = "_QUOTE_";

    public QuoteRepositoryImpl(ReactiveRedisTemplate<String, QuoteRedis> template) {

        this.redisTemplate = template;
        this.hashOperations = redisTemplate.opsForHash();
    }

    private int TTL;

    @Override
    public Mono<String> saveQuote(QuotationCacheModel quote) {
        String uuid = UUID.randomUUID().toString();
        QuoteRedis quoteRedis = quoteMapper.mapToQuoteRedis(quote);
        quoteRedis.setConvertionId(uuid);
        log.info(quoteRedis.getConvertionId());
        return this.hashOperations.put(KEY + uuid, uuid, new Gson().toJson(quoteRedis))
                .flatMap(aBoolean ->
                {
                    quote.setConversionTimelife(String.valueOf(ttl).concat(" seconds"));
                    return
                            redisTemplate.expire(KEY + uuid, Duration.ofSeconds(ttl))
                                    .map(aBoolean1 -> uuid);
                });
    }

    @Override
    public Mono<QuotationCacheModel> findQuote(String convertionId) {
        return this.hashOperations.get(KEY + convertionId, convertionId)
                .flatMap(s -> {
                    QuoteRedis quoteRedis = new Gson().fromJson(s, QuoteRedis.class);
                    QuotationCacheModel quote = quoteMapper.mapToQuoteCacheModel(quoteRedis);
                    return redisTemplate.getExpire(KEY + convertionId)
                            .map(duration -> {
                                quote.setConversionTimelife(String.valueOf(duration.getSeconds()).concat(" seconds"));
                                log.info(quote.toString());
                                return quote;
                            });
                });
    }

}
