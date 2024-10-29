package com.tpi.agencia.service;

import com.tpi.agencia.models.PosicionEntity;
import com.tpi.agencia.models.PruebaEntity;
import com.tpi.agencia.repositories.PosicionesRepository;
import com.tpi.agencia.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteService {
    private final PruebaRepository pruebaRepository;
    private final PosicionesRepository posicionesRepository;

    @Autowired
    public ReporteService(PruebaRepository pruebaRepository, PosicionesRepository posicionesRepository) {
        this.pruebaRepository = pruebaRepository;
        this.posicionesRepository = posicionesRepository;
    }

    public Double calcularDistanciaRecorrida(Integer idVehiculo, Date inicio, Date fin) {
        // Obtener posiciones del vehiculo en el periodo dado
        List<PosicionEntity> posiciones = posicionesRepository.findByIdVehiculoAndFechaHoraBetween(idVehiculo, inicio, fin);

        Double distanciaTotal = 0.0;

        if (posiciones.isEmpty()) {
            return 0.0;
        }

        for (int i = 0; i < posiciones.size() - 1; i++) {
            PosicionEntity pos1 = posiciones.get(i);
            PosicionEntity pos2 = posiciones.get(i + 1);

            // Calcular Distancia entre pos1 y pos2 usando distancia eclidea
            distanciaTotal += calcularDistanciaEuclidea(pos1.getLatitud(), pos1.getLongitud(), pos2.getLatitud(), pos2.getLongitud());
        }
        return distanciaTotal;
    }

    private Double calcularDistanciaEuclidea(Double lat1, Double lon1, Double lat2, Double lon2) {
        Double dX = lat2 - lat1;
        Double dY = lon2 - lon2;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public Iterable<PruebaEntity> getAll() { return pruebaRepository.findAll(); }



}