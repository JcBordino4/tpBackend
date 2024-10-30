package com.tpi.agencia.dtos;

import com.tpi.agencia.models.PruebaEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PruebaDto {
    private VehiculoDto vehiculoDto;
    private EmpleadoDto empleadoDto;
    private InteresadoDto interesadoDto;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String comentarios;

    public PruebaDto(PruebaEntity prueba) {
        this.fechaHoraInicio = prueba.getFechaHoraInicio();
        this.fechaHoraFin = prueba.getFechaHoraFin();
        this.comentarios = prueba.getComentarios();
    }

}
