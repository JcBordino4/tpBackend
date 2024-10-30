package com.tpi.notificaciones.models;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
public class NotificacionRadioExcedidoEntity extends NotificacionEntity {

    //Atributos
    private double radioMaximo;
    private String ubicacionActual;


    //Constructor
    public NotificacionRadioExcedidoEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, double radioMaximo, String ubicacionActual) {
        super(id, fechaNotificacion, mensaje);
        this.radioMaximo = radioMaximo;
        this.ubicacionActual = ubicacionActual;
    }

    public NotificacionRadioExcedidoEntity(double radioMaximo, String ubicacionActual) {
        this.radioMaximo = radioMaximo;
        this.ubicacionActual = ubicacionActual;
    }

    public NotificacionRadioExcedidoEntity() {
    }

}
