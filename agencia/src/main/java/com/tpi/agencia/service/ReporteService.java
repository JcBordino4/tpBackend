package com.tpi.agencia.service;

import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.dtos.externos.NotificacionDto;
import com.tpi.agencia.dtos.externos.NotificacionRadioExcedidoDto;
import com.tpi.agencia.dtos.report.responses.DistanciaVehiculoResponse;
import com.tpi.agencia.models.PosicionEntity;
import com.tpi.agencia.models.PruebaEntity;
import com.tpi.agencia.models.VehiculoEntity;
import com.tpi.agencia.repositories.PosicionRepository;
import com.tpi.agencia.repositories.PruebaRepository;
import com.tpi.agencia.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    private final PruebaRepository pruebaRepository;
    private final PosicionRepository posicionRepository;
    private final VehiculoRepository vehiculoRepository;
    private final ExternalApisService externalApisService;

    @Autowired
    public ReporteService(PruebaRepository pruebaRepository, PosicionRepository posicionRepository, VehiculoRepository vehiculoRepository, ExternalApisService externalApisService) {
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.externalApisService = externalApisService;
    }

    public DistanciaVehiculoResponse calcularDistanciaRecorrida(Integer idVehiculo, Date inicio, Date fin) {
        // Obtener posiciones del vehiculo en el periodo dado
        List<PosicionEntity> posiciones = posicionRepository.findByIdVehiculoAndFechaHoraBetween(idVehiculo, inicio, fin);
        VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con ID: " + idVehiculo));
        Double distanciaTotal = 0.0;

        if (posiciones.isEmpty()) {
            DistanciaVehiculoResponse response = new DistanciaVehiculoResponse(vehiculo, inicio, fin, distanciaTotal);
        }

        for (int i = 0; i < posiciones.size() - 1; i++) {
            PosicionEntity pos1 = posiciones.get(i);
            PosicionEntity pos2 = posiciones.get(i + 1);

            // Calcular Distancia entre pos1 y pos2 usando distancia eclidea
            distanciaTotal += calcularDistanciaEuclidea(pos1.getLatitud(), pos1.getLongitud(), pos2.getLatitud(), pos2.getLongitud());
        }
        DistanciaVehiculoResponse response = new DistanciaVehiculoResponse(vehiculo, inicio, fin, distanciaTotal);
        return response;
    }

    public List<PruebaDto> obtenerPruebasVehiculo(Integer idVehiculo) {
        VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con ID: " + idVehiculo));
        return vehiculo.getPruebas().stream().map(pruebaEntity -> new PruebaDto(pruebaEntity)).toList();
    }


    private Double calcularDistanciaEuclidea(Double lat1, Double lon1, Double lat2, Double lon2) {
        Double dX = lat2 - lat1;
        Double dY = lon2 - lon2;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public List<PruebaDto> obtenerIncidentes() {
        List<NotificacionRadioExcedidoDto> notificaciones = externalApisService.getNotificacionesRadioExcedido();

        return notificaciones.stream()
                .map(this::buscarPruebaDeNotificacion)
                .collect(Collectors.toList());
    }

    private PruebaDto buscarPruebaDeNotificacion(NotificacionRadioExcedidoDto notificacion) {
        System.out.println(notificacion.getIdVehiculo());
        System.out.println(notificacion.getFechaNotificacion());
        PruebaEntity prueba = pruebaRepository.findPruebaByVehiculoIdAndFechaNotificacionBetween(notificacion.getIdVehiculo(), notificacion.getFechaNotificacion());
        System.out.println(prueba);
        return new PruebaDto(prueba);
    }




    public Iterable<PruebaEntity> getAll() { return pruebaRepository.findAll(); }



}