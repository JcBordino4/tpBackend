package com.tpi.agencia.dtos.report.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class ReportResponse {
    private String nombreReporte;
    private String descripcionReporte;
    private Date fechaCreacionReporte;

    public ReportResponse(String nombreReporte, String descripcionReporte){
        this.nombreReporte = nombreReporte;
        this.descripcionReporte = descripcionReporte;
        this.fechaCreacionReporte = new Date();
    }
}
