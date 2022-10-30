package com.angjm.almacenapp.repository;



import com.angjm.almacenapp.model.dto.Inventario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventarioRepository  extends JpaRepository<Inventario, String>{

	List<Inventario> findAllByEstado(String estado);
}
