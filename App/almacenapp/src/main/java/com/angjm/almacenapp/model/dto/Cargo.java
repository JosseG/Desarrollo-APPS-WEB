package com.angjm.almacenapp.model.dto;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cargo", nullable = false)
    private int id;

    @Column(name = "nombre_cargo", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;


}
