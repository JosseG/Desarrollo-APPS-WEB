package com.angjm.almacenapp.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "tb_acceso_cargo")
public class AccesoCargo {

    @Id
    @Column(name = "id_acceso", nullable = false)
    private String idAcceso;

    @Column(name = "id_cargo", nullable = false)
    private String idCargo;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
