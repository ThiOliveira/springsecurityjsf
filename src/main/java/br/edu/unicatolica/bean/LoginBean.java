package br.edu.unicatolica.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.unicatolica.infra.jsf.MessagesHelper;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 747341059329169105L;

	@Inject
	private MessagesHelper helper;
	
	@Inject
	private FacesContext context;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;
	
	private static final String URL_LOGIN = "/public/login.xhtml";

	public void acessar() throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(URL_LOGIN);
		dispatcher.forward(request, response);
		
		context.responseComplete();
	}

	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			helper.addErrorMessage("Erro! Matrícula ou senha inválidos!");
		}
		
	}

}