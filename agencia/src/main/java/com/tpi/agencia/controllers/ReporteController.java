package com.tpi.agencia.controllers;

import com.tpi.agencia.dtos.ErrorResponse;
import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.dtos.report.responses.DistanciaVehiculoResponse;
import com.tpi.agencia.dtos.report.responses.PruebasResponse;
import com.tpi.agencia.service.ReporteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @GetMapping(value = "/kilometros-vehiculo/{idVehiculo}")
    public ResponseEntity<?> getKilometrosVehiculo(
            @PathVariable Integer idVehiculo,
            @RequestParam("fechaDesde") String fechaDesde,
            @RequestParam("fechaHasta") String fechaHasta) {
        try {
            Date desde = dateFormat.parse(fechaDesde);
            Date hasta = dateFormat.parse(fechaHasta);

            DistanciaVehiculoResponse response = reporteService.calcularDistanciaRecorrida(idVehiculo, desde, hasta);
            return ResponseEntity.ok(response);
        } catch (ParseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al parsear la fecha: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al calcular la distancia: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/detalle-pruebas/{idVehiculo}")
    public ResponseEntity<?> obtenerPruebasVehiculo(@PathVariable Integer idVehiculo) {
        try {
            List<PruebaDto> pruebas = reporteService.obtenerPruebasVehiculo(idVehiculo);
            PruebasResponse response = new PruebasResponse(pruebas);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
