package com.tpi.agencia.dtos.report.responses;

import com.tpi.agencia.dtos.VehiculoDto;
import com.tpi.agencia.models.VehiculoEntity;

import java.util.Date;

public class DistanciaVehiculoResponse {
    private VehiculoDto vehiculo;
    private Date fechaDesde;
    private Date fechaHasta;
    private Double distanciaTotal;

    public DistanciaVehiculoResponse(VehiculoEntity vehiculo, Date fechaDesde, Date fechaHasta, Double distanciaTotal) {
        this.vehiculo = new VehiculoDto(vehiculo);
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.distanciaTotal = distanciaTotal;
    }
}

