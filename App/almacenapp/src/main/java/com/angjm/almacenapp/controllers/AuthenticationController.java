package com.angjm.almacenapp.controllers;


import com.angjm.almacenapp.repository.IEmpleadoRepository;
import com.angjm.almacenapp.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class AuthenticationController {

    IEmpleadoRepository empleadoRepository;
    IUsuarioRepository usuarioRepository;

    public AuthenticationController(@Autowired IEmpleadoRepository empleadoRepository, @Autowired IUsuarioRepository usuarioRepository) {
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping("/login")
    public String enLogin(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return (auth.getPrincipal().equals("anonymousUser")) ? "login" : "redirect:/inventario/orden/genera";

    }


    @GetMapping("/")
    public String irInicio() {
        return "redirect:/login";
    }




}
