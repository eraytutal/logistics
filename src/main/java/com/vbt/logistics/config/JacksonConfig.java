package com.vbt.logistics.config;

import com.vbt.logistics.util.BigDecimalScale3Serializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class JacksonConfig {

    @Bean
    Jackson2ObjectMapperBuilderCustomizer decimals() {
        return b -> b.serializerByType(BigDecimal.class, new BigDecimalScale3Serializer());
    }
}

// Simdilik düşünme aşamasında.

