package br.edu.unicatolica.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unicatolica.model.Permissao;

@FacesConverter("permissaoConverter")
public class PermissaoConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;

		return this.getAttributesFrom(component).get(value);
	}
	  
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
		if(value == null || "".equals(value))
			return "";
		
	    Permissao entity = (Permissao) value;  
	  
	    this.addAttribute(component, entity);  
	  
	    Integer codigo = entity.getId();  
	            
	    if (codigo != null)
	        return String.valueOf(codigo);  
	  
	    return (String) value;  
	}  
	  
	protected void addAttribute(UIComponent component, Permissao permissao) {
		String key = permissao.getId().toString(); 
		this.getAttributesFrom(component).put(key, permissao);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}
	
}