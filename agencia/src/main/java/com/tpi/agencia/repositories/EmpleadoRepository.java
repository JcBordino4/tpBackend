package com.tpi.agencia.repositories;

import com.tpi.agencia.models.EmpleadoEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<EmpleadoEntity, Integer> {
}
