package com.tpi.agencia.repositories;

import com.tpi.agencia.models.PruebaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PruebaRepository extends CrudRepository<PruebaEntity, Integer> {
    boolean existsByVehiculoIdAndFechaHoraFinIsNull(Integer idVehiculo);
    List<PruebaEntity> findByFechaHoraFinIsNull();
}
