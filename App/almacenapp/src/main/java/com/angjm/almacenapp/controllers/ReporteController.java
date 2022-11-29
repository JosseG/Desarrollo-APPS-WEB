package com.angjm.almacenapp.controllers;


import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;

@Controller
public class ReporteController {


    private final ResourceLoader _resourceLoader;
    private final DataSource _dataSource;

    public ReporteController(@Autowired ResourceLoader resourceLoader, @Autowired DataSource dataSource){
        _resourceLoader = resourceLoader;
        _dataSource = dataSource;
    }


    @GetMapping(value = "/ordencompra/reportepdf/ultimosdias")
    public void reportePdfUltimosXDias(@RequestParam("ultimosXdias") String dias, HttpServletResponse response){


        Calendar calendar = Calendar.getInstance();
        java.util.Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dias));
        java.util.Date daysBefore =  calendar.getTime();

        LocalDate todayLocalDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate daysBeforeLocalDate = daysBefore.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        response.setHeader("Content-Disposition","inline;");
        response.setContentType("application/pdf");

        try{
            String recurso = _resourceLoader.getResource("classpath:static/jasper/ReporteUltimosXDias.jasper").getURI().getPath();
            HashMap<String,Object> parametros = new HashMap<>();
            parametros.put("fechaantes",daysBeforeLocalDate.toString());
            parametros.put("fechaahora",todayLocalDate.toString());

            JasperPrint jasperPrint = JasperFillManager.fillReport(recurso,parametros,_dataSource.getConnection());
            OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @GetMapping(value = "/ordencompra/reportepdf/fechaemision")
    public void reportePdfFechaEmision(@RequestParam("fechaem") String fechaOrdenEmision,HttpServletResponse response){

        response.setHeader("Content-Disposition","inline;");
        response.setContentType("application/pdf");

        try{
            String recurso = _resourceLoader.getResource("classpath:static/jasper/ReporteFechaEmision.jasper").getURI().getPath();
            HashMap<String,Object> parametros = new HashMap<>();
            parametros.put("fechaemision", LocalDate.parse(fechaOrdenEmision).toString());

            JasperPrint jasperPrint = JasperFillManager.fillReport(recurso,parametros,_dataSource.getConnection());
            OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @GetMapping(value = "/ordencompra/reportepdf/fechaentrega")
    public void reportePdfFechaEntrega(@RequestParam("fecha") String fechaOrdenEntrega, HttpServletResponse response){

        response.setHeader("Content-Disposition","inline;");
        response.setContentType("application/pdf");

        try{
            String recurso = _resourceLoader.getResource("classpath:static/jasper/ReporteFechaEntrega.jasper").getURI().getPath();
            HashMap<String,Object> parametros = new HashMap<>();
            parametros.put("fechaentrega", LocalDate.parse(fechaOrdenEntrega).toString());

            JasperPrint jasperPrint = JasperFillManager.fillReport(recurso,parametros,_dataSource.getConnection());
            OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }


}
