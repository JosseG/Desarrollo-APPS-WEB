package com.angjm.almacenapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.angjm.almacenapp.model.dto.Inventario;
import com.angjm.almacenapp.model.dto.Producto;
import com.angjm.almacenapp.repository.IAlmacenRepository;
import com.angjm.almacenapp.repository.IEmpleadoRepository;
import com.angjm.almacenapp.repository.IInventarioRepository;
import com.angjm.almacenapp.repository.IProductoRepository;
import com.angjm.almacenapp.repository.ITipoInventarioRepository;

@Controller
public class InventarioController {
	@Autowired
	private IInventarioRepository repoInve;
	@Autowired
	private ITipoInventarioRepository repoTipoInve;
	
	@Autowired
	private IProductoRepository repoProducto;
	@Autowired
	private IAlmacenRepository repoAlmacen;
	@Autowired
	private IEmpleadoRepository repoEmpleado;
	//CONSULTA DE INVENTARIO
	
	//abrir pagina de consulta inventario (prueba)
	@GetMapping("/consulta/inventario")
	public String paginaConsultInve(Model model) {
		  model.addAttribute("lstInventario",repoInve.findAll());		
		  return "consultar_inventario";
	}
    

   //Filtrar por tipo inventario( aún no está definido el inventario)
/*   @GetMapping("/buscarPorTipoInventario")
   public String buscarPorTipo(@RequestParam String tipo, Model model,@ModelAttribute("inve") Inventario inventario) {
		  model.addAttribute("inventarioTipo",repoInve.findByTipo(tipo));
		  return "consultar_inventario";	    
   }
	   */
	
	@GetMapping("/inventario/cargar")
	public String abrirPagProd(Model model) {
		model.addAttribute("producto", new Producto());
		//linea para agregar datos a combos.
		return "";
	}

	@GetMapping("/inventario/listado")
	public String listaInventario(Model model) {
		// metodo para listar, si lo hubiera
		model.addAttribute("lstInventario", repoInve.findAll());
		return "";
	}
	
}
