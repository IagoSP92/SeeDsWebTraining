package com.seeds.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Contenido;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;
import com.seeds.web.utils.PrintMap;



@WebServlet("/Contenido")
public class ContenidoServlet3 extends HttpServlet {
	
	private ContenidoService contenidoSvc = null;

    public ContenidoServlet3() {
  
    	contenidoSvc = new ContenidoServiceImpl();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		Map<String, String[]> mapa = new HashMap <String, String[]>();
		
		String cadena;
		
		mapa = PrintMap.printStringMap(mapa);
		System.out.println(cadenaMapa);
		*/
		/* Ahora deberiasmos facer unha clase que reciva o mapa */
			
		Writer out = response.getWriter();

		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String apellido = request.getParameter("apellido");
		String fnac = request.getParameter("fnac");
		String id = request.getParameter("id");
		Long idCont = null;
		
		try {
			
			idCont = Long.parseLong(id);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			Date fechaNacimiento = null;
			
			fechaNacimiento = df.parse(fnac);
			
		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		try {
			String devolvemos= null;
			//Long idContenido = contenidoSvc.(Long.parseLong(nombre)).getIdContenido();
			
			Contenido contenido1= new Contenido();
			contenido1.setFechaAlta(new Date());
			contenido1.setFechaMod(new Date());
			contenido1.setNombre(nombre);
 
			/*
			contenido1 =contenidoSvc.crearContenido(contenido1);
			devolvemos = contenidoSvc.verContenido(contenido1.getIdContenido()).toString();
			
			if(!StringUtils.isEmptyOrWhitespaceOnly(nombre)) {
				nombre= nombre.trim();
			} else {
				//El campo es obligatorio
			}
			*/
			
			Contenido c1 = new Contenido();
			c1= contenidoSvc.buscarId(idCont);
			if(c1 !=null) {
				devolvemos = c1.toString();
			}
			//devolvemos = contenidoSvc.buscarId(2l).toString();

			out.append(devolvemos);
			
		} catch (DataException e) {
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
