package com.tpi.agencia.service;

import com.tpi.agencia.dtos.PosicionDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, PosicionDto> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, PosicionDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensajeRadioExcedido(PosicionDto mensaje) {
        kafkaTemplate.send("agencia-radio-excedido-topic", mensaje);
    }

    public void enviarMensajeZonaPeligrosa(PosicionDto mensaje) {
        kafkaTemplate.send("agencia-zona-peligrosa-topic", mensaje);
    }
}
