package com.tpi.notificaciones.dtos;


import com.tpi.notificaciones.models.NotificacionRadioExcedidoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionRadioExcedidoDto extends NotificacionDto{
    private double radioMaximo;
    private String ubicacionActual;
    private Integer idPrueba;

    public NotificacionRadioExcedidoDto(NotificacionRadioExcedidoEntity notificacion) {
        super(notificacion.getId(), notificacion.getFechaNotificacion(), notificacion.getMensaje());
        this.radioMaximo = notificacion.getRadioMaximo();
        this.ubicacionActual = notificacion.getUbicacionActual();
        this.idPrueba = notificacion.getIdPrueba();
    }
}
