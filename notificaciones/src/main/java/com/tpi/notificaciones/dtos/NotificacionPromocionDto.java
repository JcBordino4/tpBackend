package com.tpi.notificaciones.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionPromocionDto extends NotificacionDto {
    private String codigoPromocion;
    private LocalDate fechaExpiracion;

}
