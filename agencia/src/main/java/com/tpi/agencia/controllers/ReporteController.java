package com.tpi.agencia.controllers;

import com.tpi.agencia.service.ReporteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final ReporteService service;
    private final ReporteService reporteService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    public ReporteController(ReporteService service, ReporteService reporteService) { this.service = service;
        this.reporteService = reporteService;
    }

    @GetMapping("/kilometros-vehiculo/{idVehiculo}")
    public ResponseEntity<Object> getKilometrosVehiculo(
            @PathVariable Integer idVehiculo,
            @RequestParam("fechaDesde") String fechaDesde,
            @RequestParam("fechaHasta") String fechaHasta) {
        try {
            Date desde = dateFormat.parse(fechaDesde);
            Date hasta = dateFormat.parse(fechaHasta);

            Double distancia = reporteService.calcularDistanciaRecorrida(idVehiculo, desde, hasta);
            return ResponseEntity.ok(distancia);
        } catch (ParseException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Bad Request", "message", "Error al parsear la fecha: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Bad Request", "message", "Error al calcular la distancia: " + e.getMessage()));
        }
    }

}
