package com.tpi.agencia.controllers;

import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.models.PruebaEntity;
import com.tpi.agencia.service.PruebaService;
import com.tpi.agencia.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getPruebaById(@PathVariable Integer id) {
        try {
            PruebaEntity found = service.findById(id);
            return ResponseEntity.ok(found);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Obtener pruebas en curso
    @GetMapping("/en-curso")
    public ResponseEntity<List<PruebaEntity>> getPruebasEnCurso() {
        List<PruebaEntity> pruebas = service.getPruebasEnCurso();

        if (pruebas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pruebas);
    }

    // crear una prueba
    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody PruebaDto prueba) {
        try {
            PruebaEntity nuevaPrueba = service.create(prueba);
            return ResponseEntity.ok(nuevaPrueba);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // modificar una prueba
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto) {
        try {
            PruebaEntity updatedPrueba = service.updatePrueba(id, pruebaDto);
            return ResponseEntity.ok(updatedPrueba);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizarPrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto) {
        try {
            PruebaEntity updatedPrueba = service.finalizarPrueba(id, pruebaDto.getComentarios());
            return ResponseEntity.ok(updatedPrueba);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // borrar una prueba
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrueba(@PathVariable Integer id) {
        try {
            service.deletePrueba(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
