package com.angjm.almacenapp.controllers;


import com.angjm.almacenapp.model.dao.IDao;
import com.angjm.almacenapp.model.dto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Path;
import java.util.List;

@Controller
public class ProductoController {


    private final IDao<Producto> productoDao;

    ProductoController(@Autowired IDao<Producto> productodao){
        this.productoDao = productodao;
    }

    @GetMapping(value = "/productos/{id}")
    public String obtenerProducto(@PathVariable Object id, Model model){

        Producto producto = productoDao.obtenerPorId(id);
        model.addAttribute("producto",producto);

        return "/";
    }

    @GetMapping(value = "/productos")
    public String obtenerTodoProductos(Model model){

        model.addAttribute("productos",productoDao.obtenerTodo());
        return "productos";
    }


    @GetMapping(value="/productos/agregar")
    public String agregarProducto( Model model){

        model.addAttribute("producto",new Producto());

        return "insertar_producto";
    }

    @PostMapping(value="/productos/agregar/ld")
    public String agregarProducto(@ModelAttribute(name = "producto") Producto producto, Model model){
        productoDao.insertar(producto);
        model.addAttribute("errorAgregar","todo ok");

        return "redirect:/productos";
    }

    @GetMapping(value="/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable String id, Model model){
        model.addAttribute("producto",productoDao.obtenerPorId(id));
        return "insertar_producto";
    }

    @PostMapping(value="/productos/actualizar")
    public String actualizarProducto(@ModelAttribute(name = "producto") Producto producto, Model model){
        productoDao.actualizar(producto);
        model.addAttribute("errorActualizar","todo ok");
        return "redirect:/productos";
    }


}
