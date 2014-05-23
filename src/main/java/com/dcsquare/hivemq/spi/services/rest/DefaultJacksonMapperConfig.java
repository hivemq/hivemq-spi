package com.dcsquare.hivemq.spi.services.rest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.ws.rs.ext.ContextResolver;

public class DefaultJacksonMapperConfig implements ContextResolver<ObjectMapper> {
    private final ObjectMapper objectMapper;

    DefaultJacksonMapperConfig() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }

    @Override
    public ObjectMapper getContext(final Class<?> objectType) {
        return objectMapper;
    }
}