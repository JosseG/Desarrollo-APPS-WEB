package com.angjm.almacenapp.model.dto;



import jakarta.persistence.*;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    
    @ManyToOne
    @JoinColumn(name="id_tipoinventario", insertable=false, updatable=false)
    private TipoInventario objTipoInve;
    
    @ManyToOne
    @JoinColumn(name="id_producto", insertable=false, updatable=false)
    private Producto objProducto;

    @ManyToOne
    @JoinColumn(name="id_almacen", insertable=false, updatable=false)
    private Almacen objAlmacen;
    
    @ManyToOne
    @JoinColumn(name="id_empleado", insertable=false, updatable=false)
    private Empleado objEmpleado;
}
