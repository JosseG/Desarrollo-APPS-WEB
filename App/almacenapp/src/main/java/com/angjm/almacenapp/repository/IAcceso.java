package com.angjm.almacenapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angjm.almacenapp.model.dto.Acceso;

@Repository
public interface IAcceso extends JpaRepository<Acceso, Integer>{

}
