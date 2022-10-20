package com.angjm.almacenapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angjm.almacenapp.model.dto.OrdenCompra;

@Repository
public interface IOrdenCompraRepository extends JpaRepository<OrdenCompra, Integer>{

}
