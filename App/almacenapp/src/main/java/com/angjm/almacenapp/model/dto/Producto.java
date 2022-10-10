package com.angjm.almacenapp.model.dto;

import jakarta.persistence.*;
import lombok.Data;
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

    @Column(name = "codigobar_producto")
    private String codigoBarras;

    @Column(name = "descripcion_producto")
    private String descripcion;

    @Column(name = "marca_producto")
    private String marca;

    @Column(name = "estado")
    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "id_tipoprod")
    private TipoProducto tipoProducto;

}
