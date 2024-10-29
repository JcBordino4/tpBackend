package com.tpi.notificaciones.repositories;

import com.tpi.notificaciones.models.NotificacionEntity;
import org.springframework.data.repository.CrudRepository;

public interface NotificacionRepository extends CrudRepository<NotificacionEntity, Integer> {
}
