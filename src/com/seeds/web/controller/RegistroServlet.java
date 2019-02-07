package com.seeds.web.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Registro")
public class RegistroServlet extends HttpServlet {
	

    public RegistroServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		

		String nombre = request.getParameter("nombre");
		Writer out = response.getWriter();
		out.append("<html>");
		out.append("<body>");
		out.append("<h1>Bienvenido a See Ds WebTraing, tontarron, chivato </h1>");

		out.append("<h2>Hola "+nombre+"</h2>");
		out.append("<h3>Sabes un secreto o pulpix e un tonterron</h3>");
		out.append("</html>");
		out.append("</body>");


		out.flush();		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
