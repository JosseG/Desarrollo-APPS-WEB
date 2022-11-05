package com.angjm.almacenapp.repository;

import com.angjm.almacenapp.model.dto.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {


    Optional<Usuario> findUsuarioByAlias(String alias);

    Usuario getUsuarioByAlias(String alias);
    boolean existsUsuarioByAlias(String alias);
    boolean existsUsuarioByContrasena(String contrasena);

}
