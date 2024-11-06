package com.tpi.agencia.dtos.externos;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionRadioExcedidoDto extends NotificacionDto{
    private double latActual;
    private double lonActual;
    private Integer idVehiculo;
}
