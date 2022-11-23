package com.angjm.almacenapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.angjm.almacenapp.model.dto.Inventario;
import com.angjm.almacenapp.model.dto.Producto;

import com.angjm.almacenapp.repository.IInventarioRepository;

@Controller
public class InventarioController {
    @Autowired
    private IInventarioRepository repoInve;
	/*@Autowired
	private ITipoInventarioRepository repoTipoInve;
	
	@Autowired
	private IProductoRepository repoProducto;
	@Autowired
	private IAlmacenRepository repoAlmacen;
	@Autowired
	private IEmpleadoRepository repoEmpleado;*/
    //CONSULTA DE INVENTARIO

    //abrir pagina de consulta inventario (prueba)
    @GetMapping("/consulta/inventario")
    public String paginaConsultInve(Model model) {
        model.addAttribute("inve", new Inventario());
        return "consultar_inventario";
    }


  
   // Detalle del inventario
    @GetMapping("/inventario/grabarInve")
  	public String detalleProducto(
  				  Model model) {
  		
      	  model.addAttribute("inventario", new Inventario());
      	  // productoRepository.save(p); 
    //	model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());
    
  		return "detalle_consult_inve";
  	} 
 /*   @GetMapping("/inventario/detalleConsuInve/{id}")
   	public String detalleProducto(@PathVariable String id, 
 			  Model model) {
        Optional<Inventario> inventario = repoInve.findById(id).

 	
 	  model.addAttribute("inventario", inventario);
 	  // productoRepository.save(p); 
 		//model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());

  		return "detalle_consult_inve";
 }*/

    
    // **************************

    @GetMapping("/inventario/cargarListado")
    public String abrirPagProd(Model model) {
        model.addAttribute("producto", new Producto());
        // para cargar combo, solo si hay o se elimina
        model.addAttribute("lst", repoInve.findAll());
        return "";
    }

    //listado
    @GetMapping("/inventario/listado")
    public String abrirListado(Model model) {
        model.addAttribute("listadoInventarios", repoInve.findAll());
        return "";
    }

    @PostMapping("/inventario/grabar")
    public String grabarInventario(@ModelAttribute Inventario inventa) {
        repoInve.save(inventa);
        return "";
    }

    /*@PostMapping("/inventario/editar")
    public String editarInventario(@ModelAttribute Inventario in, Model model) {
        model.addAttribute("inventario", repoInve.findById(in.getId()));
        return "";
    }*/

    @GetMapping(value = "/inventario/orden/genera")
    public String generarOrden(Model model) {
        model.addAttribute("Nom", "Jose");
        return "transaccion";
    }

    @GetMapping(value = "/inventario/orden/preview")
    public String mostrarPreview(Model model) {
        return "preview_o_inventario";
    }

    @GetMapping(value = "/inventario/reportes")
    public String reporteInventarios(Model model) {
        return "reporte_inventario";
    }

}
