package com.seeds.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.spi.UsuarioService;
import com.seeds.web.model.Errors;
import com.seeds.web.utils.Actions;
import com.seeds.web.utils.AttributeNames;
import com.seeds.web.utils.ErrorCodes;
import com.seeds.web.utils.ParameterNames;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ViewPath;


@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	
	private static Logger logger = LogManager.getLogger(UsuarioServlet.class);
	
	private UsuarioService usuarioSvc = null;
       
    public UsuarioServlet() {
        super();
        usuarioSvc = new UsuarioServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter(ParameterNames.ACTION);

		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}
		 
		Errors errors = new Errors(); 
		String target = null;
		
		if (Actions.REGISTRO.equalsIgnoreCase(action)) {
			
			// Recuperacion
			
			String email = request.getParameter(ParameterNames.EMAIL);
			String password = request.getParameter(ParameterNames.PASSWORD);
			
			// Limpieza
			// ...
			
			// Validacion 
			// ...
			
			if (StringUtils.isEmpty(email)) {
				errors.add(ParameterNames.EMAIL,ErrorCodes.MANDATORY_PARAMETER);
			}
			
			Usuario usuario = null;
			if (!errors.hasErrors()) {
				try { 
					usuario = usuarioSvc.logIn(email, password); 
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION,ErrorCodes.ERROR_DABAIXO);
				}
			}
			
			if(usuario==null) {
				errors.add(ParameterNames.ACTION,ErrorCodes.AUTHENTICATION_ERROR);
			}
			
			if (errors.hasErrors()) {
				
				if (logger.isDebugEnabled()) {
					logger.debug("Autenticacion fallida: {}", errors);
				}
						
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.ENTRAR;
				
			} else {				
				SessionManager.set(request, SessionAttributeNames.USUARIO, usuario);	
				target = ViewPath.HOME;
			}
			
		} else if (Actions.SALIR.equalsIgnoreCase(action)) {
			SessionManager.set(request, SessionAttributeNames.USUARIO, null);
			target = ViewPath.HOME;
		} else {
			// Mmm...
			logger.error("Action desconocida");
			// target ?
		}
		request.getRequestDispatcher(target).forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
