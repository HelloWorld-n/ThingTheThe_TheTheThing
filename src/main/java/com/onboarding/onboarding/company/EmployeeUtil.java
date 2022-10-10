package com.onboarding.onboarding.company;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.onboarding.onboarding.SqlConnection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

public class EmployeeUtil {
	public static List<Employee> findAll(){
		List<Employee> result = new ArrayList<Employee>();
		try {
			ResultSet sqlResult = SqlConnection.execQuery("SELECT * FROM \"employee\";");
			
			while (sqlResult.next()) {
				Employee employee = (
					new Employee(
						sqlResult.getString("first_name"),
						sqlResult.getString("last_name"),
						sqlResult.getString("email_id")
					)
				);
				employee.setId(sqlResult.getLong("id"));
				result.add(employee);
			}
		} catch (SQLException e) {
		}
		return result;
	}

}
