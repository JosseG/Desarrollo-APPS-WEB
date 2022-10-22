package com.angjm.almacenapp.controllers;

import com.angjm.almacenapp.repository.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmpleadoController {

    IEmpleadoRepository empleadoRepository;

    public EmpleadoController(@Autowired IEmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping("/empleados")
    public String obtenerEmpleados(Model model){

        model.addAttribute("empleados",empleadoRepository.findAll());
        return "empleados";
    }

    @GetMapping("/empleados/perfil/{nombre}&{apellido}")
    public String obtenerEmpleadoPorNombreApellido(@PathVariable String nombre, @PathVariable String apellido, Model model){
        model.addAttribute("Empleado",empleadoRepository.findByNombreAndApellido(nombre,apellido));
        return "perfil_empleado";
    }

    @GetMapping("/empleados/perfil/{id}")
    public String obtenerEmpleadoPorId(@PathVariable String id,Model model){
        model.addAttribute("Empleado",empleadoRepository.findById(id));
        return "perfil_empleado";
    }

}
