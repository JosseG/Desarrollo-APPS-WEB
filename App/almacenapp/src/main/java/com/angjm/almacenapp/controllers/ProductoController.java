package com.angjm.almacenapp.controllers;



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

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductoController {
	final int PAGESIZE = 10;
	 @Autowired
	    private ProductoService productoService;
    IProductoRepository productoRepository;
    ITipoProductoRepository tipoProductoRepository;

    ProductoController( @Autowired IProductoRepository productoRepository, @Autowired ITipoProductoRepository tipoProductoRepository){
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
  
    
    //CONSULTA DE PRODUCTO
    
    //abrir pagina consulta producto 
   @GetMapping("/consulta/producto")
    public String abrirConsultPro(Model model) {
	  model.addAttribute("produc",new Producto());
	  
	   return "consultar_producto";
    }*/
   
   //Filtrar por marca
    @GetMapping("/consulta/producto")
    public String reporteOrdenCompra(Model model) {
        model.addAttribute("listProductos", new ArrayList<>());
        return "consultar_producto";
    }

    @GetMapping("/productos/consulta/marcaPro/{pageNo}")
    public String consultaPorMarcaPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("marca") String marca,
                                      Model model) {

        try{
            Page<Producto> page = productoService.buscarResultadosPaginadosPorMarca(marca, pageNo, PAGESIZE);
            List<Producto> listaProductoMarca = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",marca);
            model.addAttribute("listProductos", listaProductoMarca);
            model.addAttribute("campoRequestFiltro","marca");
            model.addAttribute("tipoFiltro","marcaPro");

        }catch (Exception e){
            System.out.println("Hola");
        }

        return "consultar_producto";
    }

   
    @GetMapping("/productos/consulta/codigoBarPro/{pageNo}")
    public String consultaPorCodigoBarPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("codigoBarras") String codigoBarras,
                                      Model model) {

        try{
            Page<Producto> page = productoService.buscarResultadosPaginadosPorfindByCodigoBarras(codigoBarras, pageNo, PAGESIZE);
            List<Producto> listaProductoMarca = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",codigoBarras);
            model.addAttribute("listProductos", listaProductoMarca);
            model.addAttribute("campoRequestFiltro","codigoBarras");
            model.addAttribute("tipoFiltro","codigoBarPro");

        }catch (Exception e){
            System.out.println("Hola");
        }

        return "consultar_producto";
    }
    @GetMapping("/productos/consulta/idPro/{pageNo}")
    public String consultaPorIdPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("id") String id,
                                      Model model) {

        try{
            Page<Producto> page = productoService.buscarResultadosPaginadosPorId(id, pageNo, PAGESIZE);
            List<Producto> listaProductoMarca = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",id);
            model.addAttribute("listProductos", listaProductoMarca);
            model.addAttribute("campoRequestFiltro","id");
            model.addAttribute("tipoFiltro","idPro");

        }catch (Exception e){
            System.out.println("Hola");
        }

        return "consultar_producto";
    }
   
   @GetMapping("/productos/grabarConsu")
  	public String detalleProducto(
  				  Model model) {
  		
      	  model.addAttribute("producto", new Producto());
      	  // productoRepository.save(p); 
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

  
	   
  
 /*  @GetMapping("/buscarPorTipo")
   public String buscarPorTipo(@RequestParam String tipo, Model model,@ModelAttribute("product") Producto producto) {
		  model.addAttribute("productoTipo",productoRepository.findByMarca(marca));
		   return "consultar_producto";
	    }*/
//MANTENIMIENTO PRODUCTO, SE REUTILIZA EL METODO DETALLE
   @GetMapping("/productos/cargartodos")
   public String listarOrdenesCompra(Model model) {
   model.addAttribute("producto",new Producto());
   	 model.addAttribute("lsProductos", productoRepository.findAll());

       return "mantenimiento_producto";
   }

  
   @PostMapping("/productos/grabar")
	public String grabarProducto(@Valid @ModelAttribute Producto producto,BindingResult result ,
				  Model model) {
	   if(result.hasErrors()){
		   System.out.println("Existe error");
       }else {
    	   productoRepository.save(producto); 
   		model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());
       }
	   return "redirect:/productos/cargartodos";		
		  
		
	}

   @GetMapping("/productos/grabar")
   public String grabarProducto(Model model) {
   model.addAttribute("producto",new Producto());
	model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());


       return "insertar_producto";
   }

   @GetMapping("/productos/eliminar/{id}")
  	public String eliminarProducto(@PathVariable String id,  
			  Model model) {
	   productoRepository.deleteById(id);

  		  
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
}
