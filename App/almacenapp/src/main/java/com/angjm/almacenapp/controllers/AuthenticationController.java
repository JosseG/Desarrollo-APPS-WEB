package com.angjm.almacenapp.controllers;


import com.angjm.almacenapp.repository.IEmpleadoRepository;
import com.angjm.almacenapp.repository.IUsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.Arrays;


@Controller
public class AuthenticationController {

    IEmpleadoRepository empleadoRepository;
    IUsuarioRepository usuarioRepository;

    public AuthenticationController(@Autowired IEmpleadoRepository empleadoRepository, @Autowired IUsuarioRepository usuarioRepository) {
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping("/login")
    public String enLogin(Model model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth.getPrincipal().equals("anonymousUser")){
            return "login";
        }else{
            return "redirect:/homep";
        }


    }

    @GetMapping("/homep")
    public String enHome(Model model,HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(Arrays.toString(auth.getAuthorities().toArray()));
        request.getSession().setAttribute("empleadonombre", auth.getName());
        request.getSession().setAttribute("empleadorol", auth.getAuthorities().toArray()[0]);
        return "home_p";
    }


    @GetMapping("/")
    public String irInicio() {
        return "redirect:/login";
    }




}
