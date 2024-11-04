package com.tpi.notificaciones.controllers;


import com.tpi.notificaciones.dtos.NotificacionPromocionDto;
import com.tpi.notificaciones.dtos.NotificacionRadioExcedidoDto;
import com.tpi.notificaciones.dtos.NotificacionZonaPeligrosaDto;
import com.tpi.notificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    @Autowired
    public NotificacionController(NotificacionService service) {this.notificacionService = service;}

    // Guardar notificacion por radio excedido
    //    @PostMapping("/seguridad/radio-excedido/new")
    //    public ResponseEntity<?> notificaRadioExcedido(
    //            @RequestBody NotificacionRadioExcedidoDto radioExcedido) {
    //        return ResponseEntity.ok(notificacionService.createRadioExcedido(radioExcedido));
    //    }
    //
    //    // Guardar notificacion por zona peligrosa
    //    @PostMapping("/seguridad/zona-peligrosa/new")
    //    public ResponseEntity<?> notificarZonaPeligrosa(
    //            @RequestBody NotificacionZonaPeligrosaDto zonaPeligrosa) {
    //        return ResponseEntity.ok(notificacionService.createZonaPeligrosa(zonaPeligrosa));
    //    }

    // Guardar notificacion de promocion
    @PostMapping("/promocion/new")
    public ResponseEntity<?> notificarPromocion(
            @RequestBody NotificacionPromocionDto promocion) {
        return ResponseEntity.ok(notificacionService.createPromocion(promocion));
    }

    //Obtener notificacion de promocion
    @GetMapping("/promocion")
    public ResponseEntity<?> getAllPromociones() {
        return ResponseEntity.ok(notificacionService.getAllPromociones());
    }

    //Obtener notificacion de radio excedido
    @GetMapping("/seguridad/radio-excedido")
    public ResponseEntity<?> getAllRadiosExcedidos() {
        return ResponseEntity.ok(notificacionService.getAllRadiosExcedidos());
    }

    //Obtener notificacion de zona peligrosa
    @GetMapping("/seguridad/zona-peligrosa")
    public ResponseEntity<?> getAllZonasPeligrosas() {
        return ResponseEntity.ok(notificacionService.getAllZonasPeligrosas());
    }
}
