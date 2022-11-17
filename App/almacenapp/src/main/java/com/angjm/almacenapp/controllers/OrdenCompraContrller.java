package com.angjm.almacenapp.controllers;


import com.angjm.almacenapp.services.ordencompra.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.angjm.almacenapp.model.dto.OrdenCompra;
import com.angjm.almacenapp.repository.IAcceso;
import com.angjm.almacenapp.repository.IMenuReposytory;
import com.angjm.almacenapp.repository.IOrdenCompraRepository;
import com.angjm.almacenapp.repository.IProveedorRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdenCompraContrller {

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
    private IAcceso objSubMenu;


    @GetMapping("/ordencompra/cargartodos")
    public String listarOrdenesCompra(Model model) {
        model.addAttribute("ordencompra", new OrdenCompra());
        model.addAttribute("lsOrdencompra", objOrdenCompra.findAll());

        model.addAttribute("lsProveedor", objProveedor.findAll());
        model.addAttribute("listadoMenu", objMenu.findAll());
        model.addAttribute("listadoSubMenu", objSubMenu.findAll());
        System.out.println(objSubMenu.findAll());

        return "orden_compra";
    }


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

}
