package com.tpi.notificaciones.models;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

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

    // Getters and Setters
    public double getRadioMaximo() {
        return radioMaximo;
    }

    public void setRadioMaximo(double radioMaximo) {
        this.radioMaximo = radioMaximo;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }
}
