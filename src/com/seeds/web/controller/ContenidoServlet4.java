package com.seeds.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.dao.impl.ContenidoDAOImpl;
import com.isp.seeds.model.Contenido;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.mysql.cj.util.StringUtils;


@WebServlet("/Contenido4")
public class ContenidoServlet4 extends HttpServlet {
	
	private static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);
	
	private ContenidoService contenidoSvc = null;

    public ContenidoServlet4() {
  
    	contenidoSvc = new ContenidoServiceImpl();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		logger.debug((request.getParameterMap()).toString());
			
		Writer out = response.getWriter();
		String devolvemos= null;

		String nombre = request.getParameter("nombre");
		String fechaMin = request.getParameter("fechaMin");
		String fechaMax = request.getParameter("fechaMax");
		String id = request.getParameter("id");
		
		Date fMin = null;
		Date fMax = null;
		Long idCont = null;
		
		try {

			//if (id!=null) { idCont = Long.parseLong(id); }
			
			if(!StringUtils.isEmptyOrWhitespaceOnly(id)) {
				idCont = Long.parseLong(id);
			} else {
				//El campo es obligatorio
			}
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			System.out.println(fechaMin);
			
			if(fechaMin != null) { fMin = df.parse(fechaMin);}
			
			System.out.println(fMin);
			
			if(fechaMax != null) { fMax = df.parse(fechaMax);}
			
			
			if(!StringUtils.isEmptyOrWhitespaceOnly(nombre)) {
				nombre= nombre.trim();
			} else {
				//El campo es obligatorio
			}		
			
		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		try {
			// Aqui haberia que mirar se houbo algun error, se o houbo non se chama a capa de negocio
			
			ContenidoCriteria criteria = new ContenidoCriteria();
			if(nombre != null ) {  criteria.setNombre(nombre); }
			if(fMin != null ) { criteria.setFechaAlta(fMin);  }  
			if(fMax != null ) { criteria.setFechaAltaHasta(fMax);  }  
			if(idCont != null ) { criteria.setIdContenido(idCont);  }
			
			List<Contenido> listado = new ArrayList<Contenido>();
			listado = contenidoSvc.buscarCriteria(criteria);
			devolvemos="CONTENIDOS COINCIDENTES: <br>";
			for(Contenido c: listado) {
				devolvemos+= c.toString();
				devolvemos+="\n<br><br>";
			}
			
			out.append(devolvemos);
			
		} catch ( DataException e) {
			out.append("Hemos tenido un problema"+e.getMessage());
			e.printStackTrace();	
			}
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
