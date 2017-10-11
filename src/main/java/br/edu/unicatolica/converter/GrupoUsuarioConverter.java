package br.edu.unicatolica.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unicatolica.model.GrupoUsuario;

@FacesConverter(forClass=GrupoUsuario.class, value="grupoUsuarioConverter")
public class GrupoUsuarioConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;

		return this.getAttributesFrom(component).get(value);
	}
	  
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
		if(value == null || "".equals(value))
			return "";
		
	    GrupoUsuario entity = (GrupoUsuario) value;  
	  
	    this.addAttribute(component, entity);  
	  
	    Integer codigo = entity.getId();  
	            
	    if (codigo != null)
	        return String.valueOf(codigo);  
	  
	    return (String) value;  
	}  
	  
	protected void addAttribute(UIComponent component, GrupoUsuario grupo) {
		String key = grupo.getId().toString(); 
		this.getAttributesFrom(component).put(key, grupo);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}
	
}