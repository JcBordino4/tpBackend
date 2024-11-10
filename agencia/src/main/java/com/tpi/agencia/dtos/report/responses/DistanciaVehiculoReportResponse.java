package com.tpi.agencia.dtos.report.responses;

import com.tpi.agencia.dtos.VehiculoDto;
import com.tpi.agencia.models.VehiculoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

// Este DTO se utiliza para el tipo especifico de reporte que contiene la cantidad de KM recorridos por un vehiculo.
@Data
@EqualsAndHashCode(callSuper = true)
public class DistanciaVehiculoReportResponse extends ReportResponse{
    private VehiculoDto vehiculo;
    private Date fechaDesde;
    private Date fechaHasta;
    private Double distanciaTotal;

    public DistanciaVehiculoReportResponse(VehiculoEntity vehiculo, Date fechaDesde, Date fechaHasta, Double distanciaTotal) {
        super("Reporte de Distancia Recorrida por Vehiculo", "Reporte de la cantidad de kilometros de prueba que recorrio un vehiculo en un periodo determinado de tiempo.");
        this.vehiculo = new VehiculoDto(vehiculo);
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.distanciaTotal = distanciaTotal;
    }


}

