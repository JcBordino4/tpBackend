package com.tpi.agencia.dtos;

import com.tpi.agencia.models.VehiculoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiculoDto {
    private Integer id;
    private String patente;
    private Integer IdModelo;
    private Integer anio;

    public VehiculoDto(VehiculoEntity vehiculo) {
        this.id = vehiculo.getId();
        this.patente = vehiculo.getPatente();
        this.IdModelo = vehiculo.getModelo().getId();
        this.anio = vehiculo.getAnio();
    }
}
