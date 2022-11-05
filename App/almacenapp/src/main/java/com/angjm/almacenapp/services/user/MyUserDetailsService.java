package com.angjm.almacenapp.services.user;

import com.angjm.almacenapp.model.dto.Cargo;
import com.angjm.almacenapp.model.dto.Usuario;
import com.angjm.almacenapp.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    IUsuarioRepository usuarioRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario= usuarioRepository.getUsuarioByAlias(username);

        if(usuario==null){
            return null;
        }

        List<GrantedAuthority> listaCargos = new ArrayList<>();

        for (Cargo cargo: usuario.getCargo()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(cargo.getNombre());
            listaCargos.add(grantedAuthority);
        }


        return new User(usuario.getAlias(), usuario.getContrasena(), listaCargos);
    }
}
