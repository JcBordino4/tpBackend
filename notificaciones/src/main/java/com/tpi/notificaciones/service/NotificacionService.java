package com.tpi.notificaciones.service;

import com.tpi.notificaciones.dtos.NotificacionPromocionDto;
import com.tpi.notificaciones.dtos.NotificacionRadioExcedidoDto;
import com.tpi.notificaciones.dtos.NotificacionZonaPeligrosaDto;
import com.tpi.notificaciones.dtos.PosicionDto;
import com.tpi.notificaciones.models.*;
import com.tpi.notificaciones.repositories.NotificacionPromocionRepository;
import com.tpi.notificaciones.repositories.NotificacionRadioExcedidoRepository;


import com.tpi.notificaciones.repositories.NotificacionZonaPeligrosaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.StreamSupport;


@Service
public class NotificacionService {

    private final NotificacionPromocionRepository promocionRepository;
    private final NotificacionRadioExcedidoRepository radioExcedidoRepository;
    private final NotificacionZonaPeligrosaRepository zonaPeligrosaRepository;
    private final TwilioSmsService smsService;

    @Autowired
    public NotificacionService(
            NotificacionPromocionRepository promocionRepository,
            NotificacionRadioExcedidoRepository radioExcedidoRepository,
            NotificacionZonaPeligrosaRepository zonaPeligrosaRepository,
            TwilioSmsService smsService) {
        this.promocionRepository = promocionRepository;
        this.radioExcedidoRepository = radioExcedidoRepository;
        this.zonaPeligrosaRepository = zonaPeligrosaRepository;
        this.smsService = smsService;
    }

    // Crear notificación de promoción
    public NotificacionPromocionEntity createPromocion(NotificacionPromocionDto promocion) {
        NotificacionPromocionEntity nuevaPromocion = buildNotificacionPromocionFromDto(promocion);
        return promocionRepository.save(nuevaPromocion);
    }

    // Crear notificación de radio excedido
    public NotificacionRadioExcedidoEntity createRadioExcedido(PosicionDto posicion) {
        NotificacionRadioExcedidoEntity nuevoRadioExcedido = buildNotificacionRadioExcedidoFromDto(posicion);
        smsService.sendSmsToMultipleRecipients("ALERTA: El vehiculo Matricula: " + posicion.getVehiculo().getPatente().toUpperCase() +
                " excedio el radio maximo autorizado de la agencia durante la prueba actual. La ultima posicion registrada es: " +
                posicion.getCoordenadas().getLat() + " LAT, "
                + posicion.getCoordenadas().getLon() + " LON.");
        return radioExcedidoRepository.save(nuevoRadioExcedido);
    }

    // Crear notificación de zona peligrosa
    public NotificacionZonaPeligrosaEntity createZonaPeligrosa(PosicionDto posicion) {
        NotificacionZonaPeligrosaEntity nuevaZonaPeligrosa = buildNotificacionZonaPeligrosaFromDto(posicion);
        smsService.sendSmsToMultipleRecipients("ALERTA: El vehiculo Matricula: " + posicion.getVehiculo().getPatente().toUpperCase() +
                " ingreso a una zona peligrosa(area restringida) de riesgo ALTO durante la prueba actual. La ultima posicion registrada es: " +
                posicion.getCoordenadas().getLat() + " LAT, "
                + posicion.getCoordenadas().getLon() + " LON.");
        return zonaPeligrosaRepository.save(nuevaZonaPeligrosa);
    }

    // Métodos para obtener todas las notificaciones de cada tipo
    public Iterable<NotificacionPromocionDto> getAllPromociones() {
        Iterable<NotificacionPromocionEntity> promociones =  promocionRepository.findAll();
        return StreamSupport.stream(promociones.spliterator(), false).map(NotificacionPromocionDto::new).toList();
    }

    public Iterable<NotificacionRadioExcedidoDto> getAllRadiosExcedidos() {
        Iterable<NotificacionRadioExcedidoEntity> radios = radioExcedidoRepository.findAll();
        return StreamSupport.stream(radios.spliterator(), false).map(NotificacionRadioExcedidoDto::new).toList();
    }

    public Iterable<NotificacionZonaPeligrosaDto> getAllZonasPeligrosas() {
        Iterable<NotificacionZonaPeligrosaEntity> zonas = zonaPeligrosaRepository.findAll();
        return StreamSupport.stream(zonas.spliterator(), false).map(NotificacionZonaPeligrosaDto::new).toList();
    }

    private NotificacionPromocionEntity buildNotificacionPromocionFromDto(NotificacionPromocionDto promocionDto) {
        NotificacionPromocionEntity promocion = new NotificacionPromocionEntity();
        promocion.setCodigoPromocion(promocionDto.getCodigoPromocion());
        promocion.setFechaNotificacion(promocionDto.getFechaNotificacion());
        promocion.setMensaje(promocionDto.getMensaje());
        promocion.setFechaExpiracion(promocionDto.getFechaExpiracion());
        return promocion;
    }

    private NotificacionRadioExcedidoEntity buildNotificacionRadioExcedidoFromDto(PosicionDto posicion) {
        NotificacionRadioExcedidoEntity radioExcedido = new NotificacionRadioExcedidoEntity();
        radioExcedido.setFechaNotificacion(LocalDateTime.now());
        radioExcedido.setLatActual(posicion.getCoordenadas().getLat());
        radioExcedido.setLonActual(posicion.getCoordenadas().getLon());
        radioExcedido.setIdVehiculo(posicion.getVehiculo().getId());
        radioExcedido.setMensaje(posicion.getMensaje());
        return radioExcedido;
    }

    private NotificacionZonaPeligrosaEntity buildNotificacionZonaPeligrosaFromDto(PosicionDto posicion) {
        NotificacionZonaPeligrosaEntity zonaPeligrosa = new NotificacionZonaPeligrosaEntity();
        zonaPeligrosa.setFechaNotificacion(LocalDateTime.now());
        zonaPeligrosa.setNivelPeligro("ALTO");
        zonaPeligrosa.setLatActual(posicion.getCoordenadas().getLat());
        zonaPeligrosa.setLonActual(posicion.getCoordenadas().getLon());
        zonaPeligrosa.setIdVehiculo(posicion.getVehiculo().getId());
        zonaPeligrosa.setMensaje(posicion.getMensaje());
        return zonaPeligrosa;
    }
}
