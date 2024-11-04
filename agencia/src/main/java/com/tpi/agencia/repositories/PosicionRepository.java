package com.tpi.agencia.repositories;

import com.tpi.agencia.models.PosicionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PosicionRepository extends CrudRepository<PosicionEntity, Integer> {
    @Query("SELECT p FROM PosicionEntity p WHERE p.vehiculo.id = :idVehiculo AND p.fechaHora BETWEEN :inicio AND :fin")
    List<PosicionEntity> findByIdVehiculoAndFechaHoraBetween(@Param("idVehiculo") Integer idVehiculo,
                                                             @Param("inicio") Date inicio,
                                                             @Param("fin") Date fin);

}
