package com.angjm.almacenapp.model.dto;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "tb_proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_proveedor", nullable = false)
    private int id;

    @Column(name = "razon_social_proveedor", nullable = false)
    private String razonSocial;

    @Column(name = "nombre_comercial_proveedor", nullable = false)
    private String nombre;

    @Column(name = "numero_ruc_proveedor", nullable = false)
    private String ruc;

    @Column(name = "email_proveedor", nullable = false)
    private String email;

    @Column(name = "direccion_proveedor", nullable = false)
    private String direccion;

    @Column(name = "departamento_proveedor", nullable = false)
    private String departamento;

    @Column(name = "telefono_proveedor", nullable = false)
    private String telefono;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
