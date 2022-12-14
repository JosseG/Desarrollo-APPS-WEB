package com.angjm.almacenapp.model.dto;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "tb_acceso")
public class Acceso {

    @Id
    @Column(name = "id_acceso", nullable = false)
    private String id;

    @Column(name = "id_menu", nullable = false)
    private int idMenu;

    @Column(name = "nombre_acceso", nullable = false)
    private String nombre;
    
    @Column(name = "url_acceso", nullable = false)
    private String url_acceso;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;



}
