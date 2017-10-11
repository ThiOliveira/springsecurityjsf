package br.edu.unicatolica.infra.jsf;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import br.edu.unicatolica.util.AES;

public class FacesUtil implements Serializable {

	private static final long serialVersionUID = -4864113349154248550L;

	@Inject
	private FacesContext context;

	@Inject
	private ExternalContext external;

	@Inject
	private HttpServletRequest request;

	public boolean isPostBack() {
		return context.isPostback();
	}

	public boolean isNotPostBack() {
		return !isPostBack();
	}

	public void redirect(String page) {
		try {
			String contextPath = external.getRequestContextPath();

			external.redirect(contextPath + page);
			context.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

	public String getParamName(String paramName) {
		return request.getParameter(paramName);
	}

	public Integer getParam(String paramName) {
		String param = request.getParameter(paramName);

		if (param == null)
			return null;

		if (param.length() != 22)
			return -1;

		try {
			return Integer.valueOf(new AES().decodificar(param));
		} catch (Exception e) {
			return -1;
		}
	}

	public String getParamMatricula(String paramName) {
		String param = request.getParameter(paramName);

		if (param == null || param.length() != 22)
			return null;

		try {
			return new AES().decodificar(param);
		} catch (Exception e) {
			return null;
		}
	}

	public String buscaArquivo(String path) {
		return external.getRealPath(path);
	}

	public String recuperaLogo() {
		return buscaArquivo("/resources/images/logo-unicatolica.png");
	}

	public static String getUrl() {
		return getFacesContext().getViewRoot().getViewId();
	}

	private static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public String getParameterValue(String parametro) {
		return external.getRequestParameterMap().get(parametro);
	}

	public boolean hasParameters() {
		return !external.getRequestParameterMap().isEmpty();
	}

	public static void updateComponent(String idComponent) {
		RequestContext.getCurrentInstance().update(idComponent);
	}

	public static void updateComponents(String... idsComponent) {
		for (String id : idsComponent) {
			RequestContext.getCurrentInstance().update(id);
		}
	}
	
	public static void executeJS(String javascript) {
		RequestContext.getCurrentInstance().execute(javascript);
	}
}