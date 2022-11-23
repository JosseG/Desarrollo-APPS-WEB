package com.angjm.almacenapp.repository;



import com.angjm.almacenapp.model.dto.Inventario;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventarioRepository  extends JpaRepository<Inventario, Integer>{

	Page<Inventario> findByObjTipoInve_Nombre (String nombre, Pageable pageable);
	Page<Inventario> findByObjAlmacen_Direccion (String direccion, Pageable pageable);
	Page<Inventario> findByObjEmpleado_Apellido(String apellido, Pageable pageable);
	Page<Inventario> findByObjOrdenCompra_NOrdenCompra(String NOrdenCompra, Pageable pageable);
	
}