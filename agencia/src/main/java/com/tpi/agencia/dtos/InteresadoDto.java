package com.tpi.agencia.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class InteresadoDto {
    private Integer id;
    private String tipoDocumento;
    private String documento;
    private String nombre;
    private String apellido;
    private Boolean restringido;
    private Integer nroLicencia;
    private Date fechaVtoLicencia;
}
