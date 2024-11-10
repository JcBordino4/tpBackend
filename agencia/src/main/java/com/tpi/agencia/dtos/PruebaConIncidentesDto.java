package com.tpi.agencia.dtos;

import lombok.Data;

@Data
public class PruebaConIncidentesDto {
    private PruebaDto prueba;
    private Integer cantidadIncidentes;

    public PruebaConIncidentesDto(PruebaDto prueba, Integer cantidadIncidentes) {
        this.prueba = prueba;
        this.cantidadIncidentes = cantidadIncidentes;
    }
}
