package com.angjm.almacenapp.controllers;


import com.angjm.almacenapp.model.dto.OrdenCompra;
import com.angjm.almacenapp.model.dto.Producto;
import com.angjm.almacenapp.repository.IProductoRepository;
import com.angjm.almacenapp.repository.ITipoProductoRepository;
import com.angjm.almacenapp.services.ordencompra.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductoController {
    final int PAGESIZE = 6;
    @Autowired
    private ProductoService productoService;
    IProductoRepository productoRepository;
    ITipoProductoRepository tipoProductoRepository;

    ProductoController(@Autowired IProductoRepository productoRepository, @Autowired ITipoProductoRepository tipoProductoRepository) {
        this.productoRepository = productoRepository;
        this.tipoProductoRepository = tipoProductoRepository;
    }

   /* @GetMapping(value = "/productos/{id}")
    public String obtenerProducto(@PathVariable Object id, Model model){

        Optional<Producto> producto = productoRepository.findById(String.valueOf(id));
        model.addAttribute("producto", producto.orElseGet(Producto::new));

        return "/";
    }

   @GetMapping(value = "/productos")
    public String obtenerTodoProductos(Model model){

        model.addAttribute("producto",productoRepository.findAll());
        return "productos";
    }


    @GetMapping(value="/productos/agregar")
    public String agregarProducto(Model model){

      
        model.addAttribute("pro",new Producto());
    	model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());
        return "RegistrarProducto";
    }

 

    @GetMapping(value="/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable String id, Model model){
        Optional<Producto> producto = productoRepository.findById(id);
        model.addAttribute("lstTiposPro",tipoProductoRepository.findAll());
        model.addAttribute("pro",producto.orElseGet(Producto::new));
        return "ActualizarProducto";
    }

    @PostMapping(value="/productos/actualizar")
    public String actualizarProducto(@ModelAttribute(name = "pro") Producto producto, Model model){
        productoRepository.save(producto);
        model.addAttribute("errorActualizar","todo ok");
        return "redirect:/mantenimiento_producto";
    }

    @GetMapping(value="/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id, Model model){
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()){
            producto.get().setEstado(false);
            productoRepository.save(producto.get());
        }

        return "redirect:/mantenimiento_producto";
    }
  */

    //CONSULTA DE PRODUCTO


    //CONSULTAS

    @GetMapping("/consulta/producto")
    public String reporteOrdenCompra(Model model) {
        model.addAttribute("listProductos", new ArrayList<>());
        return "consultar_producto";
    }

    @GetMapping("/productos/consulta/marcaPro/{pageNo}")
    public String consultaPorMarcaPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                             @RequestParam("marca") String marca,
                                             Model model) {

        try {
            Page<Producto> page = productoService.buscarResultadosPaginadosPorMarca(marca, pageNo, PAGESIZE);
            List<Producto> listaProductoMarca = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro", marca);
            model.addAttribute("listProductos", listaProductoMarca);
            model.addAttribute("campoRequestFiltro", "marca");
            model.addAttribute("tipoFiltro", "marcaPro");

        } catch (Exception e) {
            System.out.println("Hola");
        }

        return "consultar_producto";
    }


    @GetMapping("/productos/consulta/codigoBarPro/{pageNo}")
    public String consultaPorCodigoBarPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                                 @RequestParam("codigoBarras") String codigoBarras,
                                                 Model model) {

        try {
            Page<Producto> page = productoService.buscarResultadosPaginadosPorfindByCodigoBarras(codigoBarras, pageNo, PAGESIZE);
            List<Producto> listaProductoMarca = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro", codigoBarras);
            model.addAttribute("listProductos", listaProductoMarca);
            model.addAttribute("campoRequestFiltro", "codigoBarras");
            model.addAttribute("tipoFiltro", "codigoBarPro");

        } catch (Exception e) {
            System.out.println("Hola");
        }

        return "consultar_producto";
    }

    @GetMapping("/productos/consulta/idPro/{pageNo}")
    public String consultaPorIdPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                          @RequestParam("id") String id,
                                          Model model) {

        try {
            Page<Producto> page = productoService.buscarResultadosPaginadosPorId(id, pageNo, PAGESIZE);
            List<Producto> listaProductoMarca = page.getContent();
            for (Producto e : listaProductoMarca){
                System.out.println(e.getCodigoBarras());
            }

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro", id);
            model.addAttribute("listProductos", listaProductoMarca);
            model.addAttribute("campoRequestFiltro", "id");
            model.addAttribute("tipoFiltro", "idPro");

        } catch (Exception e) {
            System.out.println("Hola");
        }

        return "consultar_producto";
    }

    @GetMapping("/productos/consulta/tipoPro/{pageNo}")
    public String consultaPorTipoPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                            @RequestParam("nombre") String nombre,
                                            Model model) {

        try {
            Page<Producto> page = productoService.buscarResultadosPaginadosPorTipo(nombre, pageNo, PAGESIZE);
            List<Producto> listaProductoMarca = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro", nombre);
            model.addAttribute("listProductos", listaProductoMarca);
            model.addAttribute("campoRequestFiltro", "nombre");
            model.addAttribute("tipoFiltro", "tipoPro");

        } catch (Exception e) {
            System.out.println("Hola");
        }

        return "consultar_producto";
    }


    @GetMapping("/productos/grabarConsu")
    public String detalleProducto(
            Model model) {

        model.addAttribute("producto", new Producto());
        model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());

        return "detalle_consult_pro";
    }

    @GetMapping("/productos/detalleConsuProducto/{id}")
    public String detalleProducto(@PathVariable String id,
                                  Model model) {
        Optional<Producto> producto = productoRepository.findById(id);


        model.addAttribute("producto", producto);
        // productoRepository.save(p);
        model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());

        return "detalle_consult_pro";
    }


    //MANTENIMIENTO PRODUCTO
    @GetMapping("/productos/cargartodos/{pageNo}")
    public String listarProductoos(Model model,@PathVariable(value = "pageNo") int pageNo) {
        int pageSizeProductos = 4;

        try{
            Page<Producto> page = productoService.buscarTodos(pageNo, pageSizeProductos);

            List<Producto> listaProductos = page.getContent();
            model.addAttribute("producto", new Producto());
            model.addAttribute("lsProductos", listaProductos);
            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "mantenimiento_producto";

    }


    @PostMapping("/productos/grabar")
    public String grabarProducto(@Valid @ModelAttribute Producto producto, BindingResult result, RedirectAttributes attributes,
                                 Model model) {
        if (result.hasErrors()) {
            System.out.println("Existe error");
        } else {
            productoRepository.save(producto);
            model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());
            attributes.addFlashAttribute("mensaje", "¡Se guardó correctamente el producto!");
        }
        return "redirect:/productos/cargartodos";


    }

    @GetMapping("/productos/grabar")
    public String grabarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());


        return "insertar_producto";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id, RedirectAttributes attributes,
                                   Model model) {
        productoRepository.deleteById(id);
        attributes.addFlashAttribute("mensajeEliminar", "¡Se eliminó correctamente el producto!");


        return "redirect:/productos/cargartodos";
    }

    @GetMapping("/productos/actualizarProducto/{id}")
    public String editarProducto(@PathVariable String id,
                                 Model model) {
        Optional<Producto> producto = productoRepository.findById(id);


        model.addAttribute("producto", producto);
        // productoRepository.save(p);
        model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());

        return "actualizar_producto";
    }

    @GetMapping("/productos/detallProducto/{id}")
    public String detalProducto(@PathVariable String id,
                                Model model) {
        Optional<Producto> producto = productoRepository.findById(id);


        model.addAttribute("producto", producto);
        // productoRepository.save(p);
        model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());

        return "detalle_producto";
    }

}
