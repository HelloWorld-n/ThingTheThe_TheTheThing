package com.onboarding.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

interface DbContract {
    public static final String HOST = "jdbc:postgresql://localhost:5432/";
    public static final String DB_NAME = "postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "password";
}

@SpringBootApplication
public class SqlConnection {
	private static Connection sqlConnection = null;

	public static void sqlConnect(){
		try {
            Class.forName("org.postgresql.Driver");
			if (sqlConnection == null){
				sqlConnection = DriverManager.getConnection(
					DbContract.HOST + DbContract.DB_NAME,
					DbContract.USERNAME,
					DbContract.PASSWORD
				);
			}
             
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
		
	}

	public static ResultSet execQuery(String query) throws SQLException {
		return sqlConnection.createStatement().executeQuery(query);
	}

	public static ResultSet execAlteringQuery(String query) throws SQLException {
		ResultSet result = sqlConnection.createStatement().executeQuery(query);
		sqlConnection.commit();
		return result;

	}

}
