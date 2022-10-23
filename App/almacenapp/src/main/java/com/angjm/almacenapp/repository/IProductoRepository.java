package com.angjm.almacenapp.repository;

import com.angjm.almacenapp.model.dto.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,String> {
	
	List<Producto> findByMarca(String marca);
}
