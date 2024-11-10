package com.tpi.notificaciones.dtos;

import com.tpi.notificaciones.models.NotificacionPromocionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionPromocionDto extends NotificacionDto {
    private String codigoPromocion;
    private LocalDate fechaExpiracion;

    public NotificacionPromocionDto() {
        super(); // Esto llamará al constructor sin argumentos de NotificacionDto
    }

    public NotificacionPromocionDto(NotificacionPromocionEntity notificacion) {
        super(notificacion.getId(), notificacion.getMensaje());
        this.codigoPromocion = notificacion.getCodigoPromocion();
        this.fechaExpiracion = notificacion.getFechaExpiracion();
    }
}
