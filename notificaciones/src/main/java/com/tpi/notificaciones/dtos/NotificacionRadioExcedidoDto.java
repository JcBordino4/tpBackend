package com.tpi.notificaciones.dtos;


import com.tpi.notificaciones.models.NotificacionRadioExcedidoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionRadioExcedidoDto extends NotificacionDto{
    private double latActual;
    private double lonActual;
    private Integer idVehiculo;

    public NotificacionRadioExcedidoDto(NotificacionRadioExcedidoEntity notificacion) {
        super(notificacion.getId(), notificacion.getMensaje());
        this.latActual = notificacion.getLatActual();
        this.lonActual = notificacion.getLonActual();
        this.idVehiculo = notificacion.getIdVehiculo();
    }
}
