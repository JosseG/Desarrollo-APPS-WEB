package com.angjm.almacenapp.model.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_inventario")
public class Inventario {

    @Id
    @Column(name = "id_inventario", nullable = false)
    private String id;

    @Column(name = "id_producto", nullable = false)
    private String idProducto;

    @Column(name = "id_tipoinventario", nullable = false)
    private int idTipoInventario;

    @Column(name = "id_almacen", nullable = false)
    private String idAlmacen;

    @Column(name = "id_empleado", nullable = false)
    private String idEmpleado;

    @Column(name = "cantidad_inventario", nullable = false)
    private int cantidad;

    @Column(name = "descripcion_inventario", nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

}
