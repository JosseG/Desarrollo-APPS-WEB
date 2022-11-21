package com.angjm.almacenapp.model.dto;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_producto")
public class Producto {

    @Id
    @Column(name = "id_producto", nullable = false)
    private String id;
    @Column(name = "id_tipoprod", nullable = false)
    private int tipo;

    
    @Column(name = "codigobar_producto", nullable = false)
    private String codigoBarras;

    @Column(name = "descripcion_producto", nullable = false)
    private String descripcion;

    @Column(name = "marca_producto", nullable = false)
    private String marca;

    @Column(name = "stock_producto", nullable = false)
    private String stock;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "id_tipoprod", insertable=false, updatable=false)
    private TipoProducto tipoProducto;

}
