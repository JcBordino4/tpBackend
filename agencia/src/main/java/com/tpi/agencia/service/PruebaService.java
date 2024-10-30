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
import java.util.stream.StreamSupport;

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

    public PruebaDto create(PruebaDto prueba) {
        PruebaEntity nuevaPrueba = buildPruebaFromDto(prueba);
        PruebaEntity savedPrueba = repository.save(nuevaPrueba);
        return new PruebaDto(savedPrueba);
    }

    public PruebaDto findById(Integer id) throws ServiceException {
        return repository.findById(id).map(PruebaDto::new).orElseThrow(() ->
            new ServiceException("Prueba no encontrada")
        );
    }

    public Iterable<PruebaDto> findAll() {
        Iterable<PruebaEntity> pruebas = repository.findAll();
        return StreamSupport.stream(pruebas.spliterator(), false).map(PruebaDto::new).toList();
    }

    public List<PruebaDto> getPruebasEnCurso() {
        return repository.findByFechaHoraFinIsNull().stream().map(PruebaDto::new).toList();
    }

    public PruebaDto updatePrueba(Integer id, PruebaDto pruebaDto) {
        PruebaEntity existingPrueba = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));

        existingPrueba.setId(id);
        existingPrueba.setFechaHoraInicio(pruebaDto.getFechaHoraInicio());

        // actualizar relaciones
        VehiculoEntity vehiculo = vehiculoRepository.findById(pruebaDto.getVehiculo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        existingPrueba.setVehiculo(vehiculo);

        EmpleadoEntity empleado = empleadoRepository.findById(pruebaDto.getEmpleado().getLegajo())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        existingPrueba.setEmpleado(empleado);

        InteresadoEntity interesado = interesadoRepository.findById(pruebaDto.getInteresado().getId())
                .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));
        existingPrueba.setInteresado(interesado);

        PruebaEntity updatedPrueba = repository.save(existingPrueba);

        return new PruebaDto(updatedPrueba);
    }

    public PruebaEntity finalizarPrueba(Integer id, String comentario) {
        PruebaEntity pruebaEnCurso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));

        if (pruebaEnCurso.getFechaHoraFin() != null) {
            throw new IllegalStateException("La prueba ya ha sido finalizada.");
        }

        pruebaEnCurso.setFechaHoraFin(new Date());
        pruebaEnCurso.setComentarios(comentario);

        return repository.save(pruebaEnCurso);
    }

    private VehiculoEntity validarVehiculoDisponible(Integer idVehiculo) {
        VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        // todos los vehiculos se asumen patentados por lo que no es necesario validar la patente
        if (repository.existsByVehiculoIdAndFechaHoraFinIsNull(idVehiculo)) {
            throw new IllegalArgumentException("El vehículo está siendo probado.");
        }
        return vehiculo;
    }

    private InteresadoEntity validarInteresado(Integer idInteresado) {
        InteresadoEntity interesado = interesadoRepository.findById(idInteresado)
                .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));
        if (interesado.getFechaVtoLicencia().before(new Date())) {
            throw new IllegalArgumentException("La licencia del interesado está vencida.");
        }
        if (interesado.getRestringido()) {
            throw new IllegalArgumentException("El interesado está restringido para probar vehículos.");
        }
        return interesado;
    }


    private PruebaEntity buildPruebaFromDto(PruebaDto pruebaDto) {
        VehiculoEntity vehiculo = validarVehiculoDisponible(pruebaDto.getVehiculo().getId());
        InteresadoEntity interesado = validarInteresado(pruebaDto.getInteresado().getId());

        EmpleadoEntity empleado = empleadoRepository.findById(pruebaDto.getEmpleado().getLegajo())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

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
