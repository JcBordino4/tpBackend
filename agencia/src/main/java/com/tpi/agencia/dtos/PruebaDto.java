package com.tpi.agencia.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class PruebaDto {
    private VehiculoDto vehiculo;
    private EmpleadoDto empleado;
    private InteresadoDto interesado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String comentarios;
}
