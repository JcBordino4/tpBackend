package com.tpi.agencia.service;

import com.tpi.agencia.models.PruebaEntity;
import com.tpi.agencia.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<PruebaEntity> findById(Integer id) {
        return repository.findById(id);
    }

    public Iterable<PruebaEntity> findAll() {
        return repository.findAll();
    }

    public PruebaEntity update(Integer id, PruebaEntity prueba) {
        if (repository.existsById(id)) {
            return repository.save(prueba);
        }
        throw new IllegalArgumentException("No se encontro la prueba con ID: " + id);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
