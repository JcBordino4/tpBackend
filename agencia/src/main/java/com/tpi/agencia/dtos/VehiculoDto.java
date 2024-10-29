package com.tpi.agencia.dtos;

import com.tpi.agencia.models.ModeloEntity;
import lombok.Data;

@Data
public class VehiculoDto {
    private Integer id;
    private String patente;
    private Integer IdModelo;
    private Integer anio;
}
