package com.angjm.almacenapp.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.angjm.almacenapp.model.dto.OrdenCompra;
import com.angjm.almacenapp.repository.IAcceso;
import com.angjm.almacenapp.repository.IMenuReposytory;
import com.angjm.almacenapp.repository.IOrdenCompraRepository;
import com.angjm.almacenapp.repository.IProveedorRepository;

@Controller
public class OrdenCompraContrller {
	
	@Autowired
	private IOrdenCompraRepository objOrdenCompra;
	
	@Autowired
	private IProveedorRepository objProveedor;
	
	@Autowired
	private IMenuReposytory objMenu;
	
	@Autowired
	private IAcceso objSubMenu;
	
	
		@GetMapping("/ordenCompra/cargarTodos")
		public String ListarOrdeneCompra(Model model) {
			model.addAttribute("ordencompra", new OrdenCompra());
			model.addAttribute("lsOrdencompra",objOrdenCompra.findAll());

			model.addAttribute("lsProveedor",objProveedor.findAll());
			model.addAttribute("listadoMenu",objMenu.findAll());
			model.addAttribute("listadoSubMenu",objSubMenu.findAll());
			System.out.println(objSubMenu.findAll());
			
			return "orden_compra";
		}

}
