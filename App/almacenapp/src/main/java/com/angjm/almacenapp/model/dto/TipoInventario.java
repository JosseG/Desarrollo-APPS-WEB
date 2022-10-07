package com.angjm.almacenapp.model.dto;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_tipoinventario")
public class TipoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipoinventario", nullable = false)
    private String id;

    @Column(name = "nombre_tipoinventario", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
