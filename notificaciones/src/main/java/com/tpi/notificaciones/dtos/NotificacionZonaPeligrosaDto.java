package com.tpi.notificaciones.dtos;


import com.tpi.notificaciones.models.NotificacionZonaPeligrosaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionZonaPeligrosaDto extends NotificacionDto{
    private String zona;
    private String nivelPeligro;
    private Integer idPrueba;

    public NotificacionZonaPeligrosaDto(NotificacionZonaPeligrosaEntity notificacion) {
        super(notificacion.getId(), notificacion.getFechaNotificacion(), notificacion.getMensaje());
        this.zona = notificacion.getZona();
        this.nivelPeligro = notificacion.getNivelPeligro();
        this.idPrueba = notificacion.getIdPrueba();
    }
}
