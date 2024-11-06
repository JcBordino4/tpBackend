package com.tpi.agencia.dtos.externos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class RestriccionesDto {
    private Coordenadas coordenadasAgencia;
    private double radioAdmitidoKm;
    private List<ZonaRestringida> zonasRestringidas;

    @Data
    public static class Coordenadas {
        private double lat;
        private double lon;
    }

    @Data
    public static class ZonaRestringida {
        private Coordenadas noroeste;
        private Coordenadas sureste;
    }
}
