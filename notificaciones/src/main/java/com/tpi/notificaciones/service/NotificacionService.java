package com.tpi.notificaciones.service;


import com.tpi.notificaciones.models.NotificacionEntity;
import com.tpi.notificaciones.repositories.NotificacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificacionService {
    private final NotificacionRepository repository;

    @Autowired
    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public NotificacionEntity notificar(NotificacionEntity notificacion) {
        return repository.save(notificacion);
    }
}
