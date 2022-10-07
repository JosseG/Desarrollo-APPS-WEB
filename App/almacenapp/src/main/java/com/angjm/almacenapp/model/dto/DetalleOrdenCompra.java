package com.angjm.almacenapp.model.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_detalleordencompra")
public class DetalleOrdenCompra {

    @Id
    @Column(name = "id_detalle_orden", nullable = false)
    private int id;

    @Column(name = "id_orden_compra", nullable = false)
    private int idOrdenCompra;

    @Column(name = "id_producto", nullable = false)
    private String idProducto;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
