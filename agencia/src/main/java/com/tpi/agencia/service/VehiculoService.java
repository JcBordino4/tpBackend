package com.tpi.agencia.service;

import com.tpi.agencia.dtos.PosicionDto;
import com.tpi.agencia.dtos.externos.RestriccionesDto;
import com.tpi.agencia.models.PosicionEntity;
import com.tpi.agencia.models.VehiculoEntity;
import com.tpi.agencia.repositories.PosicionRepository;
import com.tpi.agencia.repositories.PruebaRepository;
import com.tpi.agencia.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VehiculoService {
    private final ExternalApisService externalApisService;
    private final VehiculoRepository vehiculoRepository;
    private final PruebaRepository pruebaRepository;
    private final PosicionRepository posicionRepository;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public VehiculoService(ExternalApisService externalApisService, VehiculoRepository vehiculoRepository, PruebaRepository pruebaRepository, PosicionRepository posicionRepository, KafkaProducer kafkaProducer) {
        this.externalApisService = externalApisService;
        this.vehiculoRepository = vehiculoRepository;
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public PosicionDto procesarPosicion(PosicionDto posicionDto){
        RestriccionesDto restricciones = externalApisService.getRestricciones();

        if (restricciones == null) {
            throw new IllegalStateException("No se pudieron obtener las restricciones."); // Manejo de error adecuado
        }

        PosicionEntity posicionGuardada = guardarPosicion(posicionDto);
        PosicionDto posicionRespuesta = construirPosicionRespuesta(posicionDto, posicionGuardada);


        /* Se verifica el radio antes que las zonas restringidas ya que si se encuentra alejado no seria necesario
            verificar las zonas restringidas y verificar el radio es un calculo mas sencillo y eficiente que verificar
            todas las zonas restringidas que se deben reccorer con un loop.
         */

        if (estaPosicionFueraRadioAdmitido(posicionRespuesta, restricciones)){
            posicionRespuesta.setMensaje("La posicion actual del vehiculo se encuentra por fuera del radio permitido por la agencia.");
            // trigger notification.
            kafkaProducer.enviarMensajeRadioExcedido(posicionRespuesta);
            return posicionRespuesta;
        }

        if (estaEnZonaRestringida(posicionRespuesta, restricciones)){
            posicionRespuesta.setMensaje("La posicion actual del vehiculo se encuentra dentro de un area restringida.");
            // trigger notification.
            kafkaProducer.enviarMensajeZonaPeligrosa(posicionRespuesta);
            return posicionRespuesta;
        }

        // Posicion autorizada
        posicionRespuesta.setMensaje("La posicion actual del vehiculo fue registrada.");
        return posicionRespuesta;
    }

    private PosicionDto construirPosicionRespuesta(PosicionDto posicionDto, PosicionEntity posicionGuardada) {
        posicionDto.setId(posicionGuardada.getId());
        posicionDto.getVehiculo().setPatente(posicionGuardada.getVehiculo().getPatente());
        posicionDto.getVehiculo().setAnio(posicionGuardada.getVehiculo().getAnio());
        posicionDto.getVehiculo().setIdModelo(posicionGuardada.getVehiculo().getModelo().getId());
        return posicionDto;
    }

    // Este metodo valida que el vehiculo exista y se encuentre en una prueba activa.
    private VehiculoEntity validarVehhiculoEnPrueba(Integer idVehiculo){
        VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        // todos los vehiculos se asumen patentados por lo que no es necesario validar la patente
        if (pruebaRepository.existsByVehiculoIdAndFechaHoraFinIsNull(idVehiculo)) {
            return vehiculo;
        }
        throw new IllegalArgumentException("El vehículo no esta siendo probado en este momento. Inicia una prueba para registrar la posicion.");
    }

    private PosicionEntity buildPosicionFromDto(PosicionDto posicionDto){
        VehiculoEntity vehiculo = validarVehhiculoEnPrueba(posicionDto.getVehiculo().getId());

        PosicionEntity posicion = new PosicionEntity();
        posicion.setVehiculo(vehiculo);
        posicion.setLatitud(posicionDto.getCoordenadas().getLat());
        posicion.setLongitud(posicionDto.getCoordenadas().getLon());
        posicion.setFechaHora(new Date());

        return posicion;
    }

    private PosicionEntity guardarPosicion(PosicionDto posicionDto) {
        PosicionEntity nuevaPosicion = this.buildPosicionFromDto(posicionDto);
        return posicionRepository.save(nuevaPosicion);
    }

    private boolean estaPosicionFueraRadioAdmitido(PosicionDto posicion, RestriccionesDto restricciones){
        double distance = Math.sqrt(Math.pow(posicion.getCoordenadas().getLat() - restricciones.getCoordenadasAgencia().getLat(), 2)
                + Math.pow(posicion.getCoordenadas().getLon() - restricciones.getCoordenadasAgencia().getLon(), 2));

        return distance > restricciones.getRadioAdmitidoKm();
    }

    private boolean estaEnZonaRestringida(PosicionDto posicion, RestriccionesDto restricciones) {
        return restricciones.getZonasRestringidas().stream().anyMatch(zona -> {
            double latVehiculo = posicion.getCoordenadas().getLat();
            double lonVehiculo = posicion.getCoordenadas().getLon();
            double latNoroeste = zona.getNoroeste().getLat();
            double lonNoroeste = zona.getNoroeste().getLon();
            double latSureste = zona.getSureste().getLat();
            double lonSureste = zona.getSureste().getLon();

            return latVehiculo <= latNoroeste && latVehiculo >= latSureste
                    && lonVehiculo >= lonNoroeste && lonVehiculo <= lonSureste;
        });
    }

}
