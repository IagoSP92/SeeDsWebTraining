package com.seeds.web.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.dao.spi.ContenidoDAO;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;



@WebServlet("/ContenidoServlet")
public class ContenidoServlet2 extends HttpServlet {
	
	private ContenidoService contenidoSvc = null;

    public ContenidoServlet2() {
    	contenidoSvc = new ContenidoServiceImpl();

    	
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Writer out = response.getWriter();

		String nombre = request.getParameter("nombre");

		try {
			
			Long idContenido = contenidoSvc.verContenido(Long.parseLong(nombre)).getIdContenido();


			String devolvemos= null;
			
			devolvemos = contenidoSvc.verContenido(idContenido).toString();
			
			out.append(devolvemos);

			
		}catch (DataException e) {
			out.append("Hemos tenido un problema");
			
				
			}

		out.flush();

		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
