package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "Interesados")
public class InteresadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    private String documento;

    private String nombre;

    private String apellido;

    private Boolean restringido;

    @Column(name = "NRO_LICENCIA")
    private Integer nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private Date fechaVtoLicencia;

    @OneToMany(mappedBy = "interesado", cascade = CascadeType.ALL)
    private Set<PruebaEntity> pruebas = new HashSet<>();

    public InteresadoEntity(Integer id, String tipoDocumento, String documento, String nombre, String apellido, Boolean restringido, Integer nroLicencia, Date fechaVtoLicencia) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.restringido = restringido;
        this.nroLicencia = nroLicencia;
        this.fechaVtoLicencia = fechaVtoLicencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InteresadoEntity that = (InteresadoEntity) o;
        return Objects.equals(tipoDocumento, that.tipoDocumento) && Objects.equals(documento, that.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoDocumento, documento);
    }
}
