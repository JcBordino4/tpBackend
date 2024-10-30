package com.tpi.notificaciones.service;

import com.tpi.notificaciones.dtos.NotificacionPromocionDto;
import com.tpi.notificaciones.dtos.NotificacionRadioExcedidoDto;
import com.tpi.notificaciones.dtos.NotificacionZonaPeligrosaDto;
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
    public NotificacionPromocionEntity createPromocion(NotificacionPromocionDto promocion) {
        NotificacionPromocionEntity nuevaPromocion = buildNotificacionPromocionFromDto(promocion);
        return promocionRepository.save(nuevaPromocion);
    }

    // Crear notificación de radio excedido
    public NotificacionRadioExcedidoEntity createRadioExcedido(NotificacionRadioExcedidoDto radioExcedido) {
        NotificacionRadioExcedidoEntity nuevoRadioExcedido = buildNotificacionRadioExcedidoFromDto(radioExcedido);
        return radioExcedidoRepository.save(nuevoRadioExcedido);
    }

    // Crear notificación de zona peligrosa
    public NotificacionZonaPeligrosaEntity createZonaPeligrosa(NotificacionZonaPeligrosaDto zonaPeligrosa) {
        NotificacionZonaPeligrosaEntity nuevaZonaPeligrosa = buildNotificacionZonaPeligrosaFromDto(zonaPeligrosa);
        return zonaPeligrosaRepository.save(nuevaZonaPeligrosa);
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

    private NotificacionPromocionEntity buildNotificacionPromocionFromDto(NotificacionPromocionDto promocionDto) {
        NotificacionPromocionEntity promocion = new NotificacionPromocionEntity();
        promocion.setId(promocionDto.getId());
        promocion.setCodigoPromocion(promocionDto.getCodigoPromocion());
        promocion.setFechaNotificacion(promocionDto.getFechaNotificacion());
        promocion.setMensaje(promocionDto.getMensaje());
        promocion.setFechaExpiracion(promocionDto.getFechaExpiracion());
        return promocion;
    }

    private NotificacionRadioExcedidoEntity buildNotificacionRadioExcedidoFromDto(NotificacionRadioExcedidoDto radioDto) {
        NotificacionRadioExcedidoEntity radioExcedido = new NotificacionRadioExcedidoEntity();
        radioExcedido.setId(radioDto.getId());
        radioExcedido.setMensaje(radioDto.getMensaje());
        radioExcedido.setFechaNotificacion(radioDto.getFechaNotificacion());
        radioExcedido.setRadioMaximo(radioDto.getRadioMaximo());
        radioExcedido.setUbicacionActual(radioDto.getUbicacionActual());
        return radioExcedido;
    }

    private NotificacionZonaPeligrosaEntity buildNotificacionZonaPeligrosaFromDto(NotificacionZonaPeligrosaDto zonaDto) {
        NotificacionZonaPeligrosaEntity zonaPeligrosa = new NotificacionZonaPeligrosaEntity();
        zonaPeligrosa.setId(zonaDto.getId());
        zonaPeligrosa.setMensaje(zonaDto.getMensaje());
        zonaPeligrosa.setFechaNotificacion(zonaDto.getFechaNotificacion());
        zonaPeligrosa.setZona(zonaDto.getZona());
        zonaPeligrosa.setNivelPeligro(zonaDto.getNivelPeligro());
        return zonaPeligrosa;
    }
}
