package com.tpi.notificaciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpi.notificaciones.dtos.PosicionDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PosicionDtoDeserializer implements Deserializer<PosicionDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuración adicional si es necesaria
    }

    @Override
    public PosicionDto deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, PosicionDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize data", e);
        }
    }

    @Override
    public void close() {
        // Limpiar recursos si es necesario
    }
}
