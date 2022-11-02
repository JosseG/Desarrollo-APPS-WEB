package com.angjm.almacenapp.controllers;


import com.angjm.almacenapp.model.dto.Producto;
import com.angjm.almacenapp.model.dto.SoloCuenta;
import com.angjm.almacenapp.repository.IEmpleadoRepository;
import com.angjm.almacenapp.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Controller
public class AuthenticationController {

    IEmpleadoRepository empleadoRepository;
    IUsuarioRepository usuarioRepository;

    public AuthenticationController(@Autowired IEmpleadoRepository empleadoRepository,@Autowired IUsuarioRepository usuarioRepository){
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping("/login")
    public String enLogin(Model model){
        model.addAttribute("cuenta",new SoloCuenta());
        return "login";
    }


    @PostMapping("/login")
    public String verificarLogin(@ModelAttribute SoloCuenta usuario, Model model){

        boolean rAlias=usuarioRepository.existsUsuarioByAlias(usuario.getUsuario());
        boolean rContrasena=usuarioRepository.existsUsuarioByContrasena(usuario.getContrasena());

        return rAlias && rContrasena ? "transaccion":"redirect:/";
    }

    @GetMapping("/")
    public String irInicio(){
        return "redirect:/login";
    }



}
