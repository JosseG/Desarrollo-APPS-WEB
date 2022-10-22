package com.angjm.almacenapp.model.dto;


import jakarta.persistence.*;
import lombok.*;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tipoproducto")
public class TipoProducto {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipoprod", nullable = false)
    private int id;

    @Column(name = "nombre_tipoprod", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

    /*@OneToMany(fetch=FetchType.LAZY, mappedBy="tipoProducto")
    private List<Producto> productos = new ArrayList<>(0);*/


}
