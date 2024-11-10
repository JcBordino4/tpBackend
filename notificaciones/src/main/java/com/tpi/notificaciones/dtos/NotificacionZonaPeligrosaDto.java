package com.tpi.notificaciones.dtos;


import com.tpi.notificaciones.models.NotificacionZonaPeligrosaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionZonaPeligrosaDto extends NotificacionDto{
    private double latActual;
    private double lonActual;
    private String nivelPeligro;
    private Integer idVehiculo;


    public NotificacionZonaPeligrosaDto(NotificacionZonaPeligrosaEntity notificacion) {
        super(notificacion.getId(), notificacion.getMensaje());
        this.latActual = notificacion.getLatActual();
        this.lonActual = notificacion.getLonActual();
        this.nivelPeligro = notificacion.getNivelPeligro();
        this.idVehiculo = notificacion.getIdVehiculo();
    }
}
