package com.tpi.agencia.controllers;

import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.models.PruebaEntity;
import com.tpi.agencia.service.PruebaService;
import com.tpi.agencia.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {
    private final PruebaService service;

    @Autowired
    public PruebaController(PruebaService service) {
        this.service = service;
    }

    // Obtener todas las pruebas
    @GetMapping
    public ResponseEntity<Iterable<PruebaEntity>> getAllPruebas() {
        return ResponseEntity.ok(service.findAll());
    }

    // Obtener prueba por id
    @GetMapping("/{id}")
    public ResponseEntity<PruebaEntity> getPruebaById(@PathVariable Integer id) {
        try {
            PruebaEntity found = service.findById(id);
            return ResponseEntity.ok(found);
        } catch (ServiceException e) {
            return ResponseEntity.notFound()
                    .header("Error-Message", e.getMessage())
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<PruebaEntity> create(@RequestBody PruebaDto prueba) {
        PruebaEntity nueva = new PruebaEntity();
    }

}
