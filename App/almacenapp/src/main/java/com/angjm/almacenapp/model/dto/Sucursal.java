package com.angjm.almacenapp.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "tb_sucursal")
public class Sucursal {

    @Id
    @Column(name = "id_sucursal", nullable = false)
    private String id;

    @Column(name = "sector_sucursal", nullable = false)
    private String sector;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
