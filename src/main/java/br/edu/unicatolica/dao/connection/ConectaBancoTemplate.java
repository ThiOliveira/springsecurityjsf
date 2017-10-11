package br.edu.unicatolica.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConectaBancoTemplate implements Conectavel {
	private Connection connection;

	public Connection conectaBanco() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:jboss/datasources/templateDS");
			connection = ds.getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return connection;
	}

	public void desconectaBanco() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

}
