package com.tpi.notificaciones.dtos;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionZonaPeligrosaDto extends NotificacionDto{
    private String zona;
    private String nivelPeligro;
}
