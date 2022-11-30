package com.angjm.almacenapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.angjm.almacenapp.model.dto.Inventario;

import com.angjm.almacenapp.repository.IInventarioRepository;
import com.angjm.almacenapp.services.ordencompra.InventarioService;

@Controller
public class InventarioController {
	 final int PAGESIZE = 10;
    @Autowired
    private IInventarioRepository repoInve;
   
	 @Autowired
     private InventarioService inventarioService;
	/*@Autowired
	private ITipoInventarioRepository repoTipoInve;
	
	@Autowired
	private IProductoRepository repoProducto;
	@Autowired
	private IAlmacenRepository repoAlmacen;
	@Autowired
	private IEmpleadoRepository repoEmpleado;*/
    //CONSULTA DE INVENTARIO

    //CONSULTA INVENTARIO












    @GetMapping("/consulta/inventario")
    public String paginaConsultInve(Model model) {
        model.addAttribute("listInventarios", new ArrayList<>());
        return "consultar_inventario";
    }
    @GetMapping("/inventario/consulta/empleadoInve/{pageNo}")
    public String consultaPorEmpleadoPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("apellido") String apellido,
                                      Model model) {

        try{
            Page<Inventario> page = inventarioService.buscarResultadosPaginadosPorEmpleado(apellido, pageNo, PAGESIZE);
            List<Inventario> listaInventarios = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",apellido);
            model.addAttribute("listInventarios", listaInventarios);
            model.addAttribute("campoRequestFiltro","apellido");
            model.addAttribute("tipoFiltro","empleadoInve");

        }catch (Exception e){
            System.out.println("Hola");
        }

        return "consultar_inventario";
    }
    
    @GetMapping("/inventario/consulta/almacenInve/{pageNo}")
    public String consultaPorAlmacenPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("direccion") String direccion,
                                      Model model) {

        try{
            Page<Inventario> page = inventarioService.buscarResultadosPaginadosPorAlmacen(direccion, pageNo, PAGESIZE);
            List<Inventario> listaInventarios = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",direccion);
            model.addAttribute("listInventarios", listaInventarios);
            model.addAttribute("campoRequestFiltro","direccion");
            model.addAttribute("tipoFiltro","almacenInve");

        }catch (Exception e){
            System.out.println("Hola");
        }

        return "consultar_inventario";
    }
    @GetMapping("/inventario/consulta/tipoInve/{pageNo}")
    public String consultaPorTipoInvePaginacion(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("nombre") String nombre,
                                      Model model) {

        try{
            Page<Inventario> page = inventarioService.buscarResultadosPaginadosPorTipoInventario(nombre, pageNo, PAGESIZE);
            List<Inventario> listaInventarios = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",nombre);
            model.addAttribute("listInventarios", listaInventarios);
            model.addAttribute("campoRequestFiltro","nombre");
            model.addAttribute("tipoFiltro","tipoInve");

        }catch (Exception e){
            System.out.println("Hola");
        }

        return "consultar_inventario";
    }
    @GetMapping("/inventario/consulta/ordenInve/{pageNo}")
    public String consultaPorOrdenPaginacion(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("NOrdenCompra") String NOrdenCompra,
                                      Model model) {

        try{
            Page<Inventario> page = inventarioService.buscarResultadosPaginadosPorOrden(NOrdenCompra, pageNo, PAGESIZE);
            List<Inventario> listaInventarios = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",NOrdenCompra);
            model.addAttribute("listInventarios", listaInventarios);
            model.addAttribute("campoRequestFiltro","NOrdenCompra");
            model.addAttribute("tipoFiltro","ordenInve");

        }catch (Exception e){
            System.out.println("Hola");
        }

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
  @GetMapping("/inventario/detalleConsuInve/{id}")
   	public String detalleProducto(@PathVariable int id, 
 			  Model model) {
        Optional<Inventario> inventario = repoInve.findById(id);

 	
 	  model.addAttribute("inventario", inventario);
 	  // productoRepository.save(p); 
 		//model.addAttribute("lstTiposPro", tipoProductoRepository.findAll());

  		return "detalle_consult_inve";
 }

   
 // **************************************************

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

    /*@GetMapping(value = "/inventario/reportes/filtrado/empleado/{0}")
    public String reporteInventariosPorFiltroNombre(Model model) {

        return "reporte_inventario";
    }*/

}
