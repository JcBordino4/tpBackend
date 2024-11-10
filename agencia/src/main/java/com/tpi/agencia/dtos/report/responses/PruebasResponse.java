package com.tpi.agencia.dtos.report.responses;

import com.tpi.agencia.dtos.PruebaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

// Este DTO se utiliza apra todos los reportes que devuelvan un formato que contenga una lista de pruebas.
@Data
@EqualsAndHashCode(callSuper = true)
public class PruebasResponse extends ReportResponse{
    private Integer totalPruebas;
    private List<PruebaDto> pruebas;

    public PruebasResponse(String nombreReporte, String descripcionReporte, List<PruebaDto> pruebas) {
        super(nombreReporte, descripcionReporte);
        this.pruebas = pruebas;
        this.totalPruebas = pruebas.size();
    }
}
