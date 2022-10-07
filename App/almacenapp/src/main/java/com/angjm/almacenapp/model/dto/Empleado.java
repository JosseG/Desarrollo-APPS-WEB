package com.angjm.almacenapp.model.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_empleado")
public class Empleado {

    @Id
    @Column(name = "id_almacen", nullable = false)
    private String id;

    @Column(name = "id_sucursal", nullable = false)
    private String idSucursal;

    @Column(name = "desc_almacen", nullable = false)
    private String descripcion;

    @Column(name = "direccion_almacen", nullable = false)
    private String direccion;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
