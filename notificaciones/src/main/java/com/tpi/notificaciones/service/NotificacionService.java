package com.tpi.notificaciones.service;

import com.tpi.notificaciones.models.*;
import com.tpi.notificaciones.repositories.NotificacionPromocionRepository;
import com.tpi.notificaciones.repositories.NotificacionRadioExcedidoRepository;


import com.tpi.notificaciones.repositories.NotificacionZonaPeligrosaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificacionService {

    private final NotificacionPromocionRepository promocionRepository;
    private final NotificacionRadioExcedidoRepository radioExcedidoRepository;
    private final NotificacionZonaPeligrosaRepository zonaPeligrosaRepository;

    @Autowired
    public NotificacionService(
            NotificacionPromocionRepository promocionRepository,
            NotificacionRadioExcedidoRepository radioExcedidoRepository,
            NotificacionZonaPeligrosaRepository zonaPeligrosaRepository) {

        this.promocionRepository = promocionRepository;
        this.radioExcedidoRepository = radioExcedidoRepository;
        this.zonaPeligrosaRepository = zonaPeligrosaRepository;
    }

    // Crear notificación de promoción
    public NotificacionPromocionEntity createPromocion(NotificacionPromocionEntity promocion) {
        return promocionRepository.save(promocion);
    }

    // Crear notificación de radio excedido
    public NotificacionRadioExcedidoEntity createRadioExcedido(NotificacionRadioExcedidoEntity radioExcedido) {
        return radioExcedidoRepository.save(radioExcedido);
    }

    // Crear notificación de zona peligrosa
    public NotificacionZonaPeligrosaEntity createZonaPeligrosa(NotificacionZonaPeligrosaEntity zonaPeligrosa) {
        return zonaPeligrosaRepository.save(zonaPeligrosa);
    }

    // Métodos para obtener todas las notificaciones de cada tipo
    public List<NotificacionPromocionEntity> getAllPromociones() {
        return (List<NotificacionPromocionEntity>) promocionRepository.findAll();
    }

    public List<NotificacionRadioExcedidoEntity> getAllRadiosExcedidos() {
        return (List<NotificacionRadioExcedidoEntity>) radioExcedidoRepository.findAll();
    }

    public List<NotificacionZonaPeligrosaEntity> getAllZonasPeligrosas() {
        return (List<NotificacionZonaPeligrosaEntity>) zonaPeligrosaRepository.findAll();
    }

}
