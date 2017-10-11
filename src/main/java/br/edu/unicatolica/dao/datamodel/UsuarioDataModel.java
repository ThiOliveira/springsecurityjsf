package br.edu.unicatolica.dao.datamodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.dao.filter.UsuarioFilter;
import br.edu.unicatolica.model.Usuario;

public class UsuarioDataModel extends LazyDataModel<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioFilter filter;
	
	private UsuarioDAO usuarioDAO;
	
	private int total;

	public UsuarioDataModel(UsuarioDAO usuarioDAO, UsuarioFilter filter) {
		this.filter = filter;
		this.usuarioDAO = usuarioDAO;
	}

	@Override
	public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		this.total = usuarioDAO.count(filter).intValue();
		
		setRowCount(total);
		
		return usuarioDAO.pesquisar(first, pageSize, filter);
	}
	
	public int getTotal() {
		return total;
	}
	
}