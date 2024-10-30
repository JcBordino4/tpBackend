package com.tpi.notificaciones.dtos;


import lombok.Data;

@Data
public class NotificacionRadioExcedidoDto {
    private double radioMaximo;
    private String ubicacionActual;
}
