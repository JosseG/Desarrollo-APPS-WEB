package com.angjm.almacenapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccesoController {



    @GetMapping("/configuracion/roles")
    public String configRoles(Model model) {

        return "configuracion_roles";
    }



}
