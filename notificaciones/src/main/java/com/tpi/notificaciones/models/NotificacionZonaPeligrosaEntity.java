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
public class NotificacionZonaPeligrosaEntity extends NotificacionEntity {

    //Atributos
    private String zona;
    private String nivelPeligro;
    private Integer idPrueba;

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


}