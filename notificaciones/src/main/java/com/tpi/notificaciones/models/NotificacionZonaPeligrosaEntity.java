package com.tpi.notificaciones.models;

import com.tpi.notificaciones.dtos.PosicionDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor

@Entity
@Table(name = "NOTIFICACION_ZONA_PELIGROSA")
public class NotificacionZonaPeligrosaEntity extends NotificacionEntity {

    //Atributos
    private double latActual;
    private double lonActual;
    private String nivelPeligro;
    private Integer idVehiculo;

    //Constructor
    public NotificacionZonaPeligrosaEntity(PosicionDto posicion, String nivelPeligro, Integer idVehiculo) {
        this.latActual = posicion.getCoordenadas().getLat();
        this.lonActual = posicion.getCoordenadas().getLon();
        this.nivelPeligro = nivelPeligro;
        this.idVehiculo = idVehiculo;
    }

    public NotificacionZonaPeligrosaEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, PosicionDto posicion, String nivelPeligro, Integer idVehiculo) {
        super(id, fechaNotificacion, mensaje);
        this.latActual = posicion.getCoordenadas().getLat();
        this.lonActual = posicion.getCoordenadas().getLon();
        this.nivelPeligro = nivelPeligro;
        this.idVehiculo = idVehiculo;
    }
}