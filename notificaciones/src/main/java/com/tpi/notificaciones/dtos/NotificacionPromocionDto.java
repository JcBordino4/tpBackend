package com.tpi.notificaciones.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotificacionPromocionDto {
    private String codigoPromocion;
    private LocalDate fechaExpiracion;

}
