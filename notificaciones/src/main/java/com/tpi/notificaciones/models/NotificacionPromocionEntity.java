package com.tpi.notificaciones.models;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class NotificacionPromocionEntity extends NotificacionEntity {

    // Atributos
    private String codigoPromocion;
    private LocalDate fechaExpiracion;
    private Integer idPrueba;

    // Constructor sin ID (para casos en los que no necesitas pasar el ID)
    public NotificacionPromocionEntity(String codigoPromocion, LocalDate fechaExpiracion) {
        this.codigoPromocion = codigoPromocion;
        this.fechaExpiracion = fechaExpiracion;
    }

    // Constructor completo (incluyendo ID y atributos de la superclase)
    public NotificacionPromocionEntity(Integer id, LocalDateTime fechaNotificacion, String mensaje, String codigoPromocion, LocalDate fechaExpiracion) {
        super(id, fechaNotificacion, mensaje);
        this.codigoPromocion = codigoPromocion;
        this.fechaExpiracion = fechaExpiracion;
    }

    // Constructor sin argumentos (necesario para JPA)
    public NotificacionPromocionEntity() {
    }
}
