package com.tpi.agencia.dtos.externos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
public abstract class NotificacionDto {
    private Integer id;
    private List<String> reciverEmails;
    private LocalDateTime fechaNotificacion;
    private String mensaje;

    public NotificacionDto(Integer id, LocalDateTime fechaNotificacion, String mensaje) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.mensaje = mensaje;
    }
}
