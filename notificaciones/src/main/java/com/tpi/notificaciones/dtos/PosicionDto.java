package com.tpi.notificaciones.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosicionDto {
    private int id;
    private VehiculoDto vehiculo;
    private Coordenadas coordenadas;
    private String mensaje;

    @JsonCreator
    public PosicionDto(
            @JsonProperty("id") int id,
            @JsonProperty("vehiculo") VehiculoDto vehiculo,
            @JsonProperty("coordenadas") Coordenadas coordenadas,
            @JsonProperty("mensaje") String mensaje) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.coordenadas = coordenadas;
        this.mensaje = mensaje;
    }

    @Data
    @NoArgsConstructor
    public static class Coordenadas {
        private double lat;
        private double lon;

        @JsonCreator
        public Coordenadas(
                @JsonProperty("lat") double lat,
                @JsonProperty("lon") double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    }
}
