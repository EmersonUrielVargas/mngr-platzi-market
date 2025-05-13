package com.platzi.market.persistance.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    private String descripcion;

    private boolean estado;

    @OneToMany(mappedBy = "categoria")

    private List<Producto> productos;
}
