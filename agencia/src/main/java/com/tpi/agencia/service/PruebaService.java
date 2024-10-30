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

import java.util.Date;
import java.util.List;

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

    public List<PruebaEntity> getPruebasEnCurso() {
        return repository.findByFechaHoraFinIsNull();
    }

    public PruebaEntity updatePrueba(Integer id, PruebaDto pruebaDto) {
        PruebaEntity existingPrueba = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));

        PruebaEntity updatedPrueba = buildPruebaFromDto(pruebaDto);
        updatedPrueba.setId(id);

        return repository.save(updatedPrueba);
    }

    private PruebaEntity buildPruebaFromDto(PruebaDto pruebaDto) {
        // todos los vehiculos se asumen patentados por lo que no es necesario validar la patente
        VehiculoEntity vehiculo = vehiculoRepository.findById(pruebaDto.getIdVehiculo())
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        //if (repository.existsByVehiculoIdAndFechaHoraFinIsNull(vehiculo.getId())) {
        //    throw new IllegalArgumentException("El vehículo está siendo probado.");
        //}
        EmpleadoEntity empleado = empleadoRepository.findById(pruebaDto.getIdEmpleado())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        // validar el interesado
        InteresadoEntity interesado = interesadoRepository.findById(pruebaDto.getIdInteresado())
                .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));
        if (interesado.getFechaVtoLicencia().before(new Date())) {
            throw new IllegalArgumentException("La licencia del interesado está vencida.");
        }
        if (interesado.getRestringido()) {
            throw new IllegalArgumentException("El interesado está restringido para probar vehículos.");
        }

        PruebaEntity prueba = new PruebaEntity();
        prueba.setVehiculo(vehiculo);
        prueba.setEmpleado(empleado);
        prueba.setInteresado(interesado);
        prueba.setFechaHoraInicio(new Date());

        return prueba;
    }

    public void deletePrueba(Integer id) {
        PruebaEntity existingPrueba = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));
        repository.delete(existingPrueba);
    }

}
