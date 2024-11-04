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
@Table(name = "NOTIFICACION_RADIO_EXCEDIDO")
public class NotificacionRadioExcedidoEntity extends NotificacionEntity {

    //Atributos
    private double latActual;
    private double lonActual;
    private Integer idVehiculo;

    //Constructor
    public NotificacionRadioExcedidoEntity(PosicionDto posicion, Integer idVehiculo) {
        this.latActual = posicion.getCoordenadas().getLat();
        this.lonActual = posicion.getCoordenadas().getLon();
        this.idVehiculo = idVehiculo;
    }

    public NotificacionRadioExcedidoEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, PosicionDto posicion, Integer idVehiculo) {
        super(id, fechaNotificacion, mensaje);
        this.latActual = posicion.getCoordenadas().getLat();
        this.lonActual = posicion.getCoordenadas().getLon();
        this.idVehiculo = idVehiculo;
    }
}
