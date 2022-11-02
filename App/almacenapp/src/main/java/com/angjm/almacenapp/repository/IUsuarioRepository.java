package com.angjm.almacenapp.repository;

import com.angjm.almacenapp.model.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {


    boolean existsUsuarioByAlias(String alias);
    boolean existsUsuarioByContrasena(String contrasena);

}
