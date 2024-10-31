package com.tpi.notificaciones.dtos;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionRadioExcedidoDto extends NotificacionDto{
    private double radioMaximo;
    private String ubicacionActual;
    private Integer idPrueba;
}
