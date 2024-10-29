package com.tpi.agencia.controllers;

import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.models.EmpleadoEntity;
import com.tpi.agencia.models.InteresadoEntity;
import com.tpi.agencia.models.PruebaEntity;
import com.tpi.agencia.models.VehiculoEntity;
import com.tpi.agencia.repositories.EmpleadoRepository;
import com.tpi.agencia.repositories.InteresadoRepository;
import com.tpi.agencia.repositories.VehiculoRepository;
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
    private final EmpleadoRepository empleadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final InteresadoRepository interesadoRepository;

    @Autowired
    public PruebaController(PruebaService service, EmpleadoRepository empleadoRepository, VehiculoRepository vehiculoRepository, InteresadoRepository interesadoRepository) {
        this.service = service;
        this.empleadoRepository = empleadoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.interesadoRepository = interesadoRepository;
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
        PruebaEntity nueva = new PruebaEntity();

        // Busqueda de entidades relacionadas con prueba por sus IDs
        VehiculoEntity vehiculo = vehiculoRepository.findById(prueba.getIdVehiculo())
                        .orElseThrow(() -> new IllegalArgumentException("Vehiculo no encontrado"));
        EmpleadoEntity empleado = empleadoRepository.findById(prueba.getIdEmpleado())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        InteresadoEntity interesado = interesadoRepository.findById(prueba.getIdInteresado())
                .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));

        // seteo los datos obtenidos en la entidad
        nueva.setFechaHoraInicio(prueba.getFechaHoraInicio());
        nueva.setFechaHoraFin(prueba.getFechaHoraFin());
        nueva.setComentarios(prueba.getComentarios());
        nueva.setVehiculo(vehiculo);
        nueva.setEmpleado(empleado);
        nueva.setInteresado(interesado);

        // guardo la prueba en la BD
        PruebaEntity savedPrueba = service.create(nueva);
        return ResponseEntity.ok(service.create(savedPrueba));
    }

    // modificar una prueba
    @PutMapping("/{id}")
    public ResponseEntity<PruebaEntity> updatePrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto) {
        try {
            PruebaEntity existingPrueba = service.findById(id);
            // actualizar los campos con la informacion del DTO
            existingPrueba.setFechaHoraFin(pruebaDto.getFechaHoraFin());
            existingPrueba.setFechaHoraInicio(pruebaDto.getFechaHoraInicio());
            existingPrueba.setComentarios(pruebaDto.getComentarios());

            // actualizar las relaciones
            VehiculoEntity vehiculo = vehiculoRepository.findById(pruebaDto.getIdVehiculo())
                    .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
            EmpleadoEntity empleado = empleadoRepository.findById(pruebaDto.getIdEmpleado())
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
            InteresadoEntity interesado = interesadoRepository.findById(pruebaDto.getIdInteresado())
                    .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));

            existingPrueba.setVehiculo(vehiculo);
            existingPrueba.setEmpleado(empleado);
            existingPrueba.setInteresado(interesado);

            // actualizo la prueba y la guardo en la BD
            PruebaEntity updatedPrueba = service.update(id, existingPrueba);
            return ResponseEntity.ok(updatedPrueba);

        } catch (ServiceException e) {
            return ResponseEntity.notFound()
                    .header("Error-Message", e.getMessage())
                    .build();
        }
    }

    // borrar una prueba
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrueba(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
