package com.tpi.notificaciones.dtos;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public abstract class NotificacionDto {
    private Integer id;
    private LocalDateTime fechaNotificacion;
    private String mensaje;
}
