package com.angjm.almacenapp.services.ordencompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.angjm.almacenapp.model.dto.Inventario;
import com.angjm.almacenapp.repository.IInventarioRepository;
@Service
public class InventarioService {
	  @Autowired
	    IInventarioRepository inventarioRepository;
	  
	  	public Page<Inventario> buscarResultadosPaginadosPorEmpleado(String apellido, int pageNo, int pageSize){

	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return inventarioRepository.findByObjEmpleado_Apellido(apellido, pageable);
	    }
	  	public Page<Inventario> buscarResultadosPaginadosPorAlmacen(String direccion, int pageNo, int pageSize){

	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return inventarioRepository.findByObjAlmacen_Direccion(direccion, pageable);
	    }
	  	public Page<Inventario> buscarResultadosPaginadosPorTipoInventario(String nombre, int pageNo, int pageSize){

	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return inventarioRepository.findByObjTipoInve_Nombre(nombre, pageable);
	    }
	  	
		public Page<Inventario> buscarResultadosPaginadosPorOrden(String NOrdenCompra, int pageNo, int pageSize){

	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return inventarioRepository.findByObjOrdenCompra_NOrdenCompra(NOrdenCompra, pageable);
	    }
	   
	    public Page<Inventario> buscarResultadosPaginados( int pageNo, int pageSize){
	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return inventarioRepository.findAll(pageable);
	    }
}
