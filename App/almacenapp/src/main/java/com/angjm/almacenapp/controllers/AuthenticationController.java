package com.angjm.almacenapp.controllers;


import com.angjm.almacenapp.model.dto.SoloCuenta;
import com.angjm.almacenapp.repository.IEmpleadoRepository;
import com.angjm.almacenapp.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.enterprise.inject.Model;

@Controller
public class AuthenticationController {

    IEmpleadoRepository empleadoRepository;
    IUsuarioRepository usuarioRepository;

    public AuthenticationController(@Autowired IEmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }


    @GetMapping("/login")
    public String enLogin(){
        return "login";
    }


    @PostMapping("/login")
    public String verificarLogin(@ModelAttribute SoloCuenta usuario, Model model){

        boolean rAlias=usuarioRepository.findUsuarioByAlias(usuario.getUsuario());
        boolean rContrasena=usuarioRepository.findUsuarioByContrasena(usuario.getContrasena());

        return rAlias && rContrasena ? "transaccion":"redirect:/";
    }

    @GetMapping("/")
    public String irInicio(){
        return "redirect:/login";
    }



}
