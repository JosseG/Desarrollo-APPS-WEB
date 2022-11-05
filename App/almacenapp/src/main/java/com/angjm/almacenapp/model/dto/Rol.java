package com.angjm.almacenapp.model.dto;


import lombok.Data;

import jakarta.persistence.*;

import java.util.Set;

@Data
@Entity
@Table(name = "tb_rol")
public class Rol {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_rol")
    private int id;

    @Column(name = "nombre_rol")
    private String nombre;

}
