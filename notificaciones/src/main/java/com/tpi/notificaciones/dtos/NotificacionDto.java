package com.tpi.notificaciones.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public abstract class NotificacionDto {
    private Integer id;
    private List<String> reciverEmails;
    private LocalDateTime fechaNotificacion;
    private String mensaje;
}
