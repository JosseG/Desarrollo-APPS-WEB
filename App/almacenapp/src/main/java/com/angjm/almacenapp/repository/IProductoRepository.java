package com.angjm.almacenapp.repository;

import com.angjm.almacenapp.model.dto.Producto;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,String> {
	

	Page<Producto> findByMarca(String marca, Pageable pageable);
    Page<Producto> findByTipoProducto_Nombre (String nombre, Pageable pageable);
    Page<Producto> findByCodigoBarras(String codigoBarras, Pageable pageable);
    Page<Producto> findById(String id, Pageable pageable);
    Page<Producto> findAll(Pageable pageable);

    
	Producto findFirstByIdIsAfterOrderByIdDesc(String id);
}
