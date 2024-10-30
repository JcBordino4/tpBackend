package com.tpi.agencia.controllers;

import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.models.PruebaEntity;
import com.tpi.agencia.service.PruebaService;
import com.tpi.agencia.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {
    // inyectar repositorios de las entidades relacionadas
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

    // crear una prueba
    @PostMapping
    public ResponseEntity<PruebaEntity> create(@RequestBody PruebaDto prueba) {
        PruebaEntity savedPrueba = service.create(prueba);
        return ResponseEntity.ok(savedPrueba);
    }

    // modificar una prueba
    @PutMapping("/{id}")
    public ResponseEntity<PruebaEntity> updatePrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto) {
        PruebaEntity updatedPrueba = service.updatePrueba(id, pruebaDto);
        return ResponseEntity.ok(updatedPrueba);
    }

    // borrar una prueba
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrueba(@PathVariable Integer id) {
        service.deletePrueba(id);
        return ResponseEntity.noContent().build();
    }

}
