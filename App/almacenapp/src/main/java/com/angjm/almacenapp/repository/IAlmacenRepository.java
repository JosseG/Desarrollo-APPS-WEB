package com.angjm.almacenapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angjm.almacenapp.model.dto.Almacen;
@Repository
public interface IAlmacenRepository extends JpaRepository<Almacen, String>{

}
