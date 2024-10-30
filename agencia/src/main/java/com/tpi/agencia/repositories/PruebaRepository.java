package com.tpi.agencia.repositories;

import com.tpi.agencia.models.PruebaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PruebaRepository extends CrudRepository<PruebaEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM PruebaEntity p WHERE p.vehiculo.id = ?1 AND p.fechaHoraFin IS NULL")
    boolean existsByVehiculoIdAndFechaHoraFinIsNull(Integer idVehiculo);
    List<PruebaEntity> findByFechaHoraFinIsNull();
}
