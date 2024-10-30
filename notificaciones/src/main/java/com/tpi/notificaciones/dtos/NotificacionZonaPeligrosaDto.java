package com.tpi.notificaciones.dtos;


import lombok.Data;

@Data
public class NotificacionZonaPeligrosaDto {
    private String zona;
    private String nivelPeligro;
}
