package com.onboarding.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.sql.ResultSet;

@SpringBootApplication
public class Onboarding {

	public static void main(String[] args){
		//SpringApplication.run(Onboarding.class, args);
		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[BEGIN]\u001B[0m\n");
		
		try {
			SqlConnection.sqlConnect();
			ResultSet rs = SqlConnection.execQuery("SELECT 3 + 5, 3 - 5, 3 * 5, 3 / 5;");
			
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
