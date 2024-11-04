package com.tpi.notificaciones.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehiculoDto {
    private Integer id;
    private String patente;
    private Integer IdModelo;
    private Integer anio;

    @JsonCreator
    public VehiculoDto(
            @JsonProperty("id") Integer id,
            @JsonProperty("patente") String patente,
            @JsonProperty("IdModelo") Integer IdModelo,
            @JsonProperty("anio") Integer anio) {
        this.id = id;
        this.patente = patente;
        this.IdModelo = IdModelo;
        this.anio = anio;
    }
}
