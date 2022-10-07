package com.angjm.almacenapp.model.dto;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario", nullable = false)
    private int id;

    @Column(name = "id_empleado", nullable = false)
    private String idEmpleado;

    @Column(name = "id_cargo", nullable = false)
    private String idCargo;

    @Column(name = "alias_usuario", nullable = false)
    private String alias;

    @Column(name = "contrasena_usuario", nullable = false)
    private String contrasena;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
