package com.tpi.notificaciones.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "NOTIFICACION_RADIO_EXCEDIDO")
public class NotificacionRadioExcedidoEntity extends NotificacionEntity {

    //Atributos
    private double radioMaximo;
    private String ubicacionActual;
    private Integer idPrueba;

    //Constructor
    public NotificacionRadioExcedidoEntity(double radioMaximo, String ubicacionActual, Integer idPrueba) {
        this.radioMaximo = radioMaximo;
        this.ubicacionActual = ubicacionActual;
        this.idPrueba = idPrueba;
    }

    public NotificacionRadioExcedidoEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, double radioMaximo, String ubicacionActual, Integer idPrueba) {
        super(id, fechaNotificacion, mensaje);
        this.radioMaximo = radioMaximo;
        this.ubicacionActual = ubicacionActual;
        this.idPrueba = idPrueba;
    }

    public NotificacionRadioExcedidoEntity() {
    }

}
