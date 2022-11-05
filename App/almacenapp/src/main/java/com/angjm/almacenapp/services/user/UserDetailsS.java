package com.angjm.almacenapp.services.user;

import com.angjm.almacenapp.model.dto.Rol;
import com.angjm.almacenapp.model.dto.Usuario;
import com.angjm.almacenapp.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsS implements UserDetailsService {

    @Autowired
    IUsuarioRepository usuarioRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario= usuarioRepository.getUsuarioByAlias(username);

        if(usuario==null){
            return null;
        }

        List<GrantedAuthority> listaRoles = new ArrayList<>();
        System.out.println("Roles -> " + usuario.getRol().toString());

        for (Rol rol: usuario.getRol()) {
            System.out.println(rol.getNombre());
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.getNombre());
            listaRoles.add(grantedAuthority);
        }

        System.out.println(usuario.getAlias() + " ->  " + usuario.getContrasena());


        return new User(usuario.getAlias(), usuario.getContrasena(), listaRoles);
    }
}
