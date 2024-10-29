package com.tpi.agencia.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class PruebaDto {
    private Integer idVehiculo;
    private Integer idEmpleado;
    private Integer IdInteresado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String comentarios;
}
