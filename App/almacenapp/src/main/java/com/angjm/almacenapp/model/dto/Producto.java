package com.angjm.almacenapp.model.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_menu", nullable = false)
    private int id;

    @Column(name = "des_menu", nullable = false)
    private String descripcion;

    @Column(name = "icono_menu", nullable = false)
    private String icono;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
