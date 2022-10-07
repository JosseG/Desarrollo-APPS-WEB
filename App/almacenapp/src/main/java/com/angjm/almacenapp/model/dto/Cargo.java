package com.angjm.almacenapp.model.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cargo")
public class Cargo {

    @Id
    @Column(name = "id_cargo", nullable = false)
    private String id;

    @Column(name = "nombre_cargo", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;


}
