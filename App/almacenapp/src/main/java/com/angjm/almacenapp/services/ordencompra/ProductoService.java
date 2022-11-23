package com.angjm.almacenapp.services.ordencompra;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.angjm.almacenapp.model.dto.Producto;
import com.angjm.almacenapp.repository.IProductoRepository;

@Service
public class ProductoService {
	
	  @Autowired
	    IProductoRepository productoRepository;
	  
	  	public Page<Producto> buscarResultadosPaginadosPorMarca(String marca, int pageNo, int pageSize){

	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return productoRepository.findByMarca(marca, pageable);
	    }
	    public Page<Producto> buscarResultadosPaginadosPorEstado(String estado, int pageNo, int pageSize){
	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return productoRepository.findByEstado(estado, pageable);
	    }
	    public Page<Producto> buscarResultadosPaginadosPorfindByCodigoBarras(String codigoBarras, int pageNo, int pageSize){
	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return productoRepository.findByCodigoBarras(codigoBarras, pageable);
	    }
	    public Page<Producto> buscarResultadosPaginadosPorId(String id, int pageNo, int pageSize){
	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return productoRepository.findById(id, pageable);
	    }
	    public Page<Producto> buscarResultadosPaginados( int pageNo, int pageSize){
	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        return productoRepository.findAll(pageable);
	    }

}
