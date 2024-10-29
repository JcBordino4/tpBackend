package com.tpi.agencia.service;

import com.tpi.agencia.models.PruebaEntity;
import com.tpi.agencia.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PruebaService {
    private final PruebaRepository repository;

    @Autowired
    public PruebaService(PruebaRepository repository) {
        this.repository = repository;
    }

    public PruebaEntity create(PruebaEntity prueba) {
        return repository.save(prueba);
    }
}
