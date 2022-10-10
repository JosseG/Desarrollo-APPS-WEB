package com.angjm.almacenapp.controllers;


import com.angjm.almacenapp.model.dao.IDao;
import com.angjm.almacenapp.model.dto.Producto;
import com.angjm.almacenapp.repository.IProductoRepository;
import com.angjm.almacenapp.repository.ITipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ProductoController {

    IProductoRepository productoRepository;
    ITipoProductoRepository tipoProductoRepository;

    ProductoController( @Autowired IProductoRepository productoRepository, @Autowired ITipoProductoRepository tipoProductoRepository){
        this.productoRepository = productoRepository;
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @GetMapping(value = "/productos/{id}")
    public String obtenerProducto(@PathVariable Object id, Model model){

        Optional<Producto> producto = productoRepository.findById(String.valueOf(id));
        model.addAttribute("producto", producto.orElseGet(Producto::new));

        return "/";
    }

    @GetMapping(value = "/productos")
    public String obtenerTodoProductos(Model model){

        model.addAttribute("productos",productoRepository.findAll());
        return "productos";
    }


    @GetMapping(value="/productos/agregar")
    public String agregarProducto(Model model){

        model.addAttribute("tipoProductos",tipoProductoRepository.findAll());
        model.addAttribute("producto",new Producto());

        return "insertar_producto";
    }

    @PostMapping(value="/productos/agregar")
    public String agregarProducto(@Valid @ModelAttribute("producto") Producto producto, Model model, BindingResult result){
        if(result.hasErrors()){

        }else {
            productoRepository.save(producto);
        }
        return "redirect:/productos";
    }

    @GetMapping(value="/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable String id, Model model){
        Optional<Producto> producto = productoRepository.findById(id);
        model.addAttribute("tipoProductos",tipoProductoRepository.findAll());
        model.addAttribute("producto",producto.orElseGet(Producto::new));
        return "actualizar_producto";
    }

    @PostMapping(value="/productos/actualizar")
    public String actualizarProducto(@ModelAttribute(name = "producto") Producto producto, Model model){
        productoRepository.save(producto);
        model.addAttribute("errorActualizar","todo ok");
        return "redirect:/productos";
    }

    @GetMapping(value="/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id, Model model){
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()){
            producto.get().setEstado(false);
            productoRepository.save(producto.get());
        }

        return "redirect:/productos";
    }


}
