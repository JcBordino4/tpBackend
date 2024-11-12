package com.tpi.agencia.controllers;

import com.tpi.agencia.dtos.ErrorResponse;
import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.dtos.report.responses.DistanciaVehiculoReportResponse;
import com.tpi.agencia.dtos.report.responses.IncidentesResponse;
import com.tpi.agencia.dtos.report.responses.IncidentesXEmpleado;
import com.tpi.agencia.dtos.report.responses.PruebasResponse;
import com.tpi.agencia.service.ExternalApisService;
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

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final ReporteService reporteService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public ReporteController(ReporteService reporteService, ExternalApisService externalApisService) {
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

            DistanciaVehiculoReportResponse response = reporteService.calcularDistanciaRecorrida(idVehiculo, desde, hasta);
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
            PruebasResponse response = new PruebasResponse("Reporte de Pruebas por Vehiculo", "Reporte de pruebas para un vehiculo en especifico.", pruebas);
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

    @GetMapping("/incidentes")
    public ResponseEntity<?> obtenerIncidentes() {
        try {
            List<PruebaDto> pruebas = reporteService.obtenerIncidentes();
            IncidentesResponse response = new IncidentesResponse(pruebas);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al obtener el reporte de incidentes: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/incidentes/{idEmpleado}")
    public ResponseEntity<?> obtenerIncidentesEmpleado(@PathVariable Integer idEmpleado) {
        try {
            List<PruebaDto> pruebas = reporteService.obtenerIncidentesEmpleado(idEmpleado);
            IncidentesXEmpleado response = new IncidentesXEmpleado(idEmpleado, pruebas);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al obtener el reporte de incidentes del empleado con el ID: " + idEmpleado + " " +  e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
