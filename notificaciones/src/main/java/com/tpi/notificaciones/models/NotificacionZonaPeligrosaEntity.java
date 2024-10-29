package com.tpi.notificaciones.models;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class NotificacionZonaPeligrosaEntity extends NotificacionEntity {

    //Atributos
    private String zona;
    private String nivelPeligro;


    //Constructor
    public NotificacionZonaPeligrosaEntity(String zona, String nivelPeligro) {
        this.zona = zona;
        this.nivelPeligro = nivelPeligro;
    }

    public NotificacionZonaPeligrosaEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, String zona, String nivelPeligro) {
        super(id, fechaNotificacion, mensaje);
        this.zona = zona;
        this.nivelPeligro = nivelPeligro;
    }

    public NotificacionZonaPeligrosaEntity() {
    }

    // Getters and Setters
    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(String nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }
}