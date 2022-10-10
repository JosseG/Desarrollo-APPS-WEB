package com.angjm.almacenapp.repository;

import com.angjm.almacenapp.model.dto.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoProductoRepository extends JpaRepository<TipoProducto,Integer> {

}
