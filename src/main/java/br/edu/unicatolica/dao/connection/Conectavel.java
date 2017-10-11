package br.edu.unicatolica.dao.connection;

import java.sql.Connection;

public interface Conectavel {
	
	Connection conectaBanco();

	void desconectaBanco();

	Connection getConnection();
}