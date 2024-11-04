package com.tpi.agencia.dtos;

import com.tpi.agencia.models.PruebaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PruebaDto {
    private int id;
    private VehiculoDto vehiculo;
    private EmpleadoDto empleado;
    private InteresadoDto interesado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String comentarios;

    public PruebaDto(PruebaEntity prueba) {
        this.id = prueba.getId();
        this.vehiculo = new VehiculoDto(prueba.getVehiculo());
        this.empleado = new EmpleadoDto(prueba.getEmpleado());
        this.interesado = new InteresadoDto(prueba.getInteresado());
        this.fechaHoraInicio = prueba.getFechaHoraInicio();
        this.fechaHoraFin = prueba.getFechaHoraFin();
        this.comentarios = prueba.getComentarios();
    }

}
