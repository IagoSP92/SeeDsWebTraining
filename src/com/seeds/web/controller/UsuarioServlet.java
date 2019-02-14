package com.seeds.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.isp.seeds.model.Contenido;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.UsuarioService;
import com.seeds.web.model.Errors;
import com.seeds.web.utils.Actions;
import com.seeds.web.utils.AttributeNames;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.ErrorCodes;
import com.seeds.web.utils.ParameterNames;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.ViewPath;


@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(UsuarioServlet.class);

	private DateUtils dateUtils = null;

	private UsuarioService usuarioSvc = null;
	private ContenidoService contenidoSvc = null;


	public UsuarioServlet() {
		super();
		usuarioSvc = new UsuarioServiceImpl();
		contenidoSvc = new ContenidoServiceImpl();
		dateUtils = new DateUtils();


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

		} else if (Actions.BUSCAR.equalsIgnoreCase(action)) {


			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String fechaMin = request.getParameter(ParameterNames.FECHA_MIN);
			String fechaMax = request.getParameter(ParameterNames.FECHA_MAX);
			String id = request.getParameter(ParameterNames.ID_CONTENIDO);

			List<Contenido> listado = new ArrayList<Contenido>();

			ContenidoCriteria criteria = new ContenidoCriteria();

			criteria.setNombre(ValidationUtils.validateString(nombre));				
			criteria.setFechaAlta(dateUtils.dateFormat(fechaMin));
			criteria.setFechaAltaHasta(dateUtils.dateFormat(fechaMax));				
			criteria.setIdContenido(ValidationUtils.validateLong(id));				

			try {
				listado = contenidoSvc.buscarCriteria(criteria);
			} catch (DataException e) {
				e.printStackTrace();
			}				

			List<String> resultados = new ArrayList<String>();

			for(Contenido c: listado) {
				resultados.add(c.toString());					
			}
			// Limpiar
			// ...

			// Validar
			//..

			// if hasErrors

			// else

			request.setAttribute(AttributeNames.RESULTADOS, resultados);

			target = ViewPath.BUSCADOR;

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
