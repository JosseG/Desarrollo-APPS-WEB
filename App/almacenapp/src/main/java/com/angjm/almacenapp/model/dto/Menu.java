package com.angjm.almacenapp.model.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_menu")
public class Menu {

    @Id
    @Column(name = "id_menu", nullable = false)
    private int id;

    @Column(name = "des_menu", nullable = false)
    private String descripcion;

    @Column(name = "icono_menu", nullable = false)
    private String icono;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
