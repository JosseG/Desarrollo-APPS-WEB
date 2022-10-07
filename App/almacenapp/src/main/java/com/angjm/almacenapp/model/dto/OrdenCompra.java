package com.angjm.almacenapp.model.dto;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "tb_orden_compra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_orden_compra", nullable = false)
    private int id;

    @Column(name = "id_proveedor", nullable = false)
    private int idProveedor;

    @Column(name = "nro_orden_compra", nullable = false)
    private String NOrdenCompra;

    @Column(name = "fechaorden_compra", nullable = false)
    private Date fechaOrdenCompra;

    @Column(name = "fechaentrega", nullable = false)
    private Date fechaentrega;

    @Column(name = "condicionespago", nullable = false)
    private String condicionesPago;

    @Column(name = "transporte", nullable = false)
    private String transporte;

    @Column(name = "valortotal_orden", nullable = false)
    private double valorTotal;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
