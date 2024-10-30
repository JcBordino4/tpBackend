package com.tpi.agencia.service;

import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.models.EmpleadoEntity;
import com.tpi.agencia.models.InteresadoEntity;
import com.tpi.agencia.models.PruebaEntity;
import com.tpi.agencia.models.VehiculoEntity;
import com.tpi.agencia.repositories.EmpleadoRepository;
import com.tpi.agencia.repositories.InteresadoRepository;
import com.tpi.agencia.repositories.PruebaRepository;
import com.tpi.agencia.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PruebaService {
    private final PruebaRepository repository;
    private final EmpleadoRepository empleadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final InteresadoRepository interesadoRepository;

    @Autowired
    public PruebaService(PruebaRepository repository, EmpleadoRepository empleadoRepository, VehiculoRepository vehiculoRepository, InteresadoRepository interesadoRepository) {
        this.repository = repository;
        this.empleadoRepository = empleadoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.interesadoRepository = interesadoRepository;
    }

    public PruebaEntity create(PruebaDto prueba) {
        PruebaEntity nuevaPrueba = buildPruebaFromDto(prueba);
        return repository.save(nuevaPrueba);
    }

    public PruebaEntity findById(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() ->
            new ServiceException("Prueba no encontrada")
        );
    }

    public Iterable<PruebaEntity> findAll() {
        return repository.findAll();
    }

    public PruebaEntity updatePrueba(Integer id, PruebaDto pruebaDto) {
        PruebaEntity existingPrueba = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));

        // Actualizar campos básicos
        existingPrueba.setFechaHoraInicio(pruebaDto.getFechaHoraInicio());
        existingPrueba.setFechaHoraFin(pruebaDto.getFechaHoraFin());
        existingPrueba.setComentarios(pruebaDto.getComentarios());

        // Actualizar relaciones con otras entidades
        VehiculoEntity vehiculo = vehiculoRepository.findById(pruebaDto.getIdVehiculo())
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        EmpleadoEntity empleado = empleadoRepository.findById(pruebaDto.getIdEmpleado())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        InteresadoEntity interesado = interesadoRepository.findById(pruebaDto.getIdInteresado())
                .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));

        existingPrueba.setVehiculo(vehiculo);
        existingPrueba.setEmpleado(empleado);
        existingPrueba.setInteresado(interesado);

        return repository.save(existingPrueba);
    }

    private PruebaEntity buildPruebaFromDto(PruebaDto pruebaDto) {
        VehiculoEntity vehiculo = vehiculoRepository.findById(pruebaDto.getIdVehiculo())
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        EmpleadoEntity empleado = empleadoRepository.findById(pruebaDto.getIdEmpleado())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        InteresadoEntity interesado = interesadoRepository.findById(pruebaDto.getIdInteresado())
                .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));

        PruebaEntity prueba = new PruebaEntity();
        prueba.setVehiculo(vehiculo);
        prueba.setEmpleado(empleado);
        prueba.setInteresado(interesado);
        prueba.setFechaHoraInicio(pruebaDto.getFechaHoraInicio());
        prueba.setFechaHoraFin(pruebaDto.getFechaHoraFin());
        prueba.setComentarios(pruebaDto.getComentarios());

        return prueba;
    }

    public void deletePrueba(Integer id) {
        PruebaEntity existingPrueba = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));
        repository.delete(existingPrueba);
    }

}
