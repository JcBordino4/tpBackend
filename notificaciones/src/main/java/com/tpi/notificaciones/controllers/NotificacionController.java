package com.tpi.notificaciones.controllers;


import com.tpi.notificaciones.models.NotificacionEntity;
import com.tpi.notificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    @Autowired
    public NotificacionController(NotificacionService service) {this.service = service;}

    // Notificar por radio excedido
    @PostMapping("/seguridad/radio-excedido")
    public ResponseEntity<NotificacionEntity> notificaRadioExcedido(
            @RequestBody NotificacionEntity notificacion) {
        return ResponseEntity.ok(service.notificar(notificacion));
    }

    // Notificar promocion
    @PostMapping("/promocion")
    public ResponseEntity<NotificacionEntity> notificarPromocion(
            @RequestBody NotificacionEntity notificacion) {
        return ResponseEntity.ok(service.notificar(notificacion));
    }

    // Notificar por zona peligrosa
    @PostMapping("/seguridad/zona-peligrosa")
    public ResponseEntity<NotificacionEntity> notificarZonaPeligrosa(
            @RequestBody NotificacionEntity notificacion) {
        return ResponseEntity.ok(service.notificar(notificacion));
    }
}
