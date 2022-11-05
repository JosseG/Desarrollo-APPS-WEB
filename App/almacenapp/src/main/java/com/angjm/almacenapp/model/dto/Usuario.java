package com.angjm.almacenapp.model.dto;



import lombok.Data;

import jakarta.persistence.*;
import java.util.Set;

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


    @Column(name = "alias_usuario", nullable = false)
    private String alias;

    @Column(name = "contrasena_usuario", nullable = false)
    private String contrasena;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tb_cargo_usuario",
            joinColumns=@JoinColumn(name="id_usuario"),
            inverseJoinColumns=@JoinColumn(name="id_cargo"))
    private Set<Cargo> cargo;

}
