package com.onboarding.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

interface DbContract {
    public static final String HOST = "jdbc:postgresql://localhost:5432/";
    public static final String DB_NAME = "postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "password";
}

@SpringBootApplication
public class Onboarding {
	private static Connection sqlConnection = null;

	public static void sqlConnect(){
		try {
            Class.forName("org.postgresql.Driver");
			if (sqlConnection == null){
				sqlConnection = DriverManager.getConnection(
					DbContract.HOST+DbContract.DB_NAME,
					DbContract.USERNAME,
					DbContract.PASSWORD
				);
			}
            System.out.println("DB connected");
             
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
		
	}

	public static ResultSet execQuery(String query) throws SQLException {
		return sqlConnection.createStatement().executeQuery(query);
	}

	public static void main(String[] args){
		//SpringApplication.run(Onboarding.class, args);
		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[BEGIN]\u001B[0m\n");
		
		try {
			sqlConnect();
			ResultSet rs = execQuery("SELECT 3 + 5, 3 - 5, 3 * 5, 3 / 5;");
			
			while(rs.next()) {
				System.out.printf(
					"%d\n%s\n%s\n%d\n", 
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getInt(4)
				);
			}
		} catch (SQLException e) {
			System.out.print("\u001B[48;02;255;0;0m\u001B[38;02;0;0;0m[ERROR]\u001B[0m Can't run query!\n");
		}

		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[END]\u001B[0m\n");
	}
}
