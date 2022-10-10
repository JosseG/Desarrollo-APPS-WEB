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
    @Column(name = "id_empleado", nullable = false)
    private String id;

    @Column(name = "id_almacen", nullable = false)
    private String idAlmacen;

    @Column(name = "nombre_empleado", nullable = false)
    private String nombre;

    @Column(name = "apellido_empleado", nullable = false)
    private String apellido;

    @Column(name = "correo_empleado", nullable = false)
    private String correo;

    @Column(name = "telefono_empleado", nullable = false)
    private String telefono;

    @Column(name = "estado")
    private boolean estado = true;

}
