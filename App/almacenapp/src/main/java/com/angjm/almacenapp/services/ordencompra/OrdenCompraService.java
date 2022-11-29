package com.angjm.almacenapp.services.ordencompra;

import com.angjm.almacenapp.model.dto.OrdenCompra;
import com.angjm.almacenapp.repository.IOrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrdenCompraService {

    @Autowired
    IOrdenCompraRepository ordenCompraRepository;

    public Page<OrdenCompra> buscarResultadosPaginadosPorFechaEntrega(LocalDate fecha, int pageNo, int pageSize){

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ordenCompraRepository.findByFechaentrega(fecha,pageable);
    }
    public Page<OrdenCompra> buscarResultadosPaginadosPorFechaEmision(LocalDate fecha, int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ordenCompraRepository.findByFechaOrdenCompra(fecha,pageable);
    }

    public Page<OrdenCompra> listarResultadosPaginados(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ordenCompraRepository.findAll(pageable);
    }


    public Page<OrdenCompra> buscarResultadosPorDiasPaginados(LocalDate fecha1,LocalDate fecha2, int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return ordenCompraRepository.findByFechaOrdenCompraBetween(fecha1,fecha2,pageable);
    }



}
