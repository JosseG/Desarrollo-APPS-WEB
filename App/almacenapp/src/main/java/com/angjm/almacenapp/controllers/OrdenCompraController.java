package com.angjm.almacenapp.controllers;


import com.angjm.almacenapp.services.ordencompra.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.angjm.almacenapp.model.dto.OrdenCompra;
import com.angjm.almacenapp.repository.IAccesoRepository;
import com.angjm.almacenapp.repository.IMenuReposytory;
import com.angjm.almacenapp.repository.IOrdenCompraRepository;
import com.angjm.almacenapp.repository.IProveedorRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
public class OrdenCompraController {

    final int PAGESIZE = 5;
    @Autowired
    private IOrdenCompraRepository objOrdenCompra;

    @Autowired
    private OrdenCompraService ordenCompraService;

    @Autowired
    private IProveedorRepository objProveedor;

    @Autowired
    private IMenuReposytory objMenu;

    @Autowired
    private IAccesoRepository objSubMenu;


    
    //MANTENIMIENTOS
    @GetMapping("/ordencompra/cargartodos/{pageNo}")
    public String listarOrdenesCompra(Model model,@PathVariable(value = "pageNo") int pageNo) {
        int pageSizeOrdenes = 10;


        try{
            Page<OrdenCompra> page=ordenCompraService.listarResultadosPaginados(pageNo,pageSizeOrdenes);

            List<OrdenCompra> listaOrdenesCompra = page.getContent();

            model.addAttribute("ordenCompra", new OrdenCompra());
            model.addAttribute("lsOrdencompra", listaOrdenesCompra);

            model.addAttribute("lsProveedor", objProveedor.findAll());
            model.addAttribute("listadoMenu", objMenu.findAll());
            model.addAttribute("listadoSubMenu", objSubMenu.findAll());
            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "orden_compra";
    }

    @PostMapping("/ordenCompra/grabar")
   	public String grabarOrden(@Valid @ModelAttribute OrdenCompra ordenCompra,BindingResult result , RedirectAttributes attributes,
   				  Model model) {
   	  
        	  objOrdenCompra.save(ordenCompra);
              model.addAttribute("lsProveedor", objProveedor.findAll());
        		attributes.addFlashAttribute("mensaje", "??Se guard?? correctamente el orden de compra!");

   	   return "redirect:/ordencompra/cargartodos/0";		
   		  
   		
   	}

      @GetMapping("/ordenCompra/grabar")
      public String grabarOrden(Model model) {
          model.addAttribute("ordenCompra", new OrdenCompra());
      model.addAttribute("lsProveedor", objProveedor.findAll());


          return "insertar_orden";
      }

      @GetMapping("/ordenCompra/eliminar/{id}")
     	public String eliminarOrden(@PathVariable int id,   RedirectAttributes attributes,
   			  Model model) {
    	  objOrdenCompra.deleteById(id);
    		attributes.addFlashAttribute("mensajeEliminar", "??Se elimin?? correctamente el orden de compra!");

     		  
          return "redirect:/ordencompra/cargartodos/0";
     	}
      
      @GetMapping("/ordenCompra/actualizarOrden/{id}")
     	public String editarOrden(@PathVariable int id, RedirectAttributes attributes,
   			  Model model) {
    	  Optional<OrdenCompra> ordenCompra = objOrdenCompra.findById(id);

    	   	
       	  model.addAttribute("ordenCompra", ordenCompra);
   	  // productoRepository.save(p); 
          model.addAttribute("lsProveedor", objProveedor.findAll());

   		return "actualizar_orden";
      }
      
      @GetMapping("/ordenCompra/detallOrden/{id}")
     	public String detalOrden(@PathVariable int id, 
   			  Model model) {
          Optional<OrdenCompra> ordenCompra = objOrdenCompra.findById(id);

   	
   	  model.addAttribute("ordenCompra", ordenCompra);
   
      model.addAttribute("lsProveedor", objProveedor.findAll());

   		return "detalle_orden";
      }

    
    
    
    
    
    
    
    //REPORTES

    @GetMapping("/ordencompra/reporte")
    public String reporteOrdenCompra(Model model) {
        model.addAttribute("listaOrdenesC", new ArrayList<>());
        return "reporte_orden_compra";
    }


    @GetMapping("/ordencompra/reporte/fechaentrega/{pageNo}")
    public String reporteOrdenCompraFiltrandoPorOrdenEntrega(@PathVariable(value = "pageNo") int pageNo,
                                                             @RequestParam("fecha") String fechaOrdenEntrega,
                                                             Model model) {

        try{
            Page<OrdenCompra> page = ordenCompraService.buscarResultadosPaginadosPorFechaEntrega(LocalDate.parse(fechaOrdenEntrega),pageNo, PAGESIZE);
            List<OrdenCompra> listaOrdenesCompra = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",fechaOrdenEntrega);
            model.addAttribute("listaOrdenesC", listaOrdenesCompra);
            model.addAttribute("campoRequestFiltro","fecha");
            model.addAttribute("tipoFiltro","fechaentrega");

        }catch (Exception e){
            System.out.println("Hola");
        }

        return "reporte_orden_compra";
    }

    @GetMapping("/ordencompra/reporte/fechaemision/{pageNo}")
    public String reporteOrdenCompraFiltrandoPorEmisionOrden(@PathVariable(value = "pageNo") int pageNo,
                                                             @RequestParam("fechaem") String fechaOrdenEmision,
                                                             Model model) {

        try{
            Page<OrdenCompra> page = ordenCompraService.buscarResultadosPaginadosPorFechaEmision(LocalDate.parse(fechaOrdenEmision),pageNo, PAGESIZE);
            List<OrdenCompra> listaOrdenesCompra = page.getContent();

            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",fechaOrdenEmision);
            model.addAttribute("campoRequestFiltro","fechaem");
            model.addAttribute("listaOrdenesC", listaOrdenesCompra);
            model.addAttribute("tipoFiltro","fechaemision");

        }catch (Exception e){
            System.out.println("Hola");
        }

        return "reporte_orden_compra";
    }

    @GetMapping("/ordencompra/reporte/ultimosdias/{pageNo}")
    public String reportOrdenCOmpraFiltrandoPorDias(@PathVariable(value = "pageNo") int pageNo,
                                                    @RequestParam("ultimosXdias") String dias,
                                                    Model model){
        Calendar calendar = Calendar.getInstance();
        java.util.Date today = calendar.getTime();

        System.out.println("Parseo de dias" +Integer.parseInt(dias));

        calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dias));
        java.util.Date daysBefore =  calendar.getTime();

        LocalDate todayLocalDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate daysBeforeLocalDate = daysBefore.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        System.out.println(todayLocalDate.toString());
        System.out.println(daysBeforeLocalDate.toString());

        try{
            Page<OrdenCompra> page = ordenCompraService.buscarResultadosPorDiasPaginados(daysBeforeLocalDate,todayLocalDate,pageNo, PAGESIZE);
            List<OrdenCompra> listaOrdenesCompra = page.getContent();


            model.addAttribute("pagActual", pageNo);
            model.addAttribute("totalPags", page.getTotalPages());
            model.addAttribute("totalElementos", page.getTotalElements());
            model.addAttribute("valorFiltro",dias);
            model.addAttribute("campoRequestFiltro","ultimosXdias");
            model.addAttribute("listaOrdenesC", listaOrdenesCompra);
            model.addAttribute("tipoFiltro","ultimosdias");

        }catch (Exception e){
            System.out.println("En filtro por d??as");
        }

        return "reporte_orden_compra";

    }


}
