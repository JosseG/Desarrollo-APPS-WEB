package com.angjm.almacenapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angjm.almacenapp.model.dto.OrdenCompra;

import java.time.LocalDate;

@Repository
public interface IOrdenCompraRepository extends JpaRepository<OrdenCompra, Integer>{

    Page<OrdenCompra> findByFechaentrega(LocalDate fecha, Pageable pageable);
    Page<OrdenCompra> findByFechaOrdenCompra(LocalDate fecha, Pageable pageable);

}
