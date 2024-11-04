package com.tpi.notificaciones.service;

import com.tpi.notificaciones.dtos.PosicionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private final NotificacionService notificacionService;

    @Autowired
    public KafkaConsumer(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @KafkaListener(topics = "agencia-radio-excedido-topic", groupId = "notificaciones-consumer-group")
    public void manejarNotificacionRadioExcedido(PosicionDto posicion) {
        System.out.println("Procesando notificación de radio excedido: " + posicion);
        notificacionService.createRadioExcedido(posicion);
    }

    @KafkaListener(topics = "agencia-zona-peligrosa-topic", groupId = "notificaciones-consumer-group")
    public void manejarNotificacionZonaPeligrosa(PosicionDto posicion) {
        System.out.println("Procesando notificación de zona peligrosa: " + posicion);
        // Lógica específica para notificación de zona peligrosa
        notificacionService.createZonaPeligrosa(posicion);
    }
}

