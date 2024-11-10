package com.tpi.notificaciones.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
public abstract class NotificacionDto {
    private Integer id;
    private LocalDateTime fechaNotificacion;;
    private String mensaje;

    public NotificacionDto(Integer id, String mensaje) {
        this.id = id;
        this.fechaNotificacion = LocalDateTime.now();
        this.mensaje = mensaje;
    }
}
