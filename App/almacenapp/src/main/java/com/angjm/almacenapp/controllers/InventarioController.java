package com.angjm.almacenapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.angjm.almacenapp.repository.IInventarioRepository;

@Controller
public class InventarioController {
	@Autowired
	private IInventarioRepository repoInve;
	
	
	//abrir pagina de consulta inventario , probando (falta definir el combo)
	@GetMapping("/consulta/inventario")
	public String paginaConsultInve(Model model) {
		model.addAttribute(repoInve.findAll());
		return "consultar_inventario";
	}
	
}
