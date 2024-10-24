package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "Modelos")
public class ModeloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MARCA")
    private MarcaEntity marca;

    private String descripcion;

    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL)
    private Set<VehiculoEntity> vehiculos;

    public ModeloEntity(Integer id, MarcaEntity marca, String descripcion) {
        this.id = id;
        this.marca = marca;
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModeloEntity that = (ModeloEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
