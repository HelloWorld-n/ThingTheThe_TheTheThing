package com.onboarding.onboarding.company;

import com.onboarding.onboarding.SqlConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public class EmployeeUtil {
	public static List<Employee> findAll(){
		List<Employee> result = new ArrayList<Employee>();
		try {
			ResultSet sqlResult = SqlConnection.execQuery("SELECT * FROM \"employee\" ORDER BY id;");
			
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

	public static Employee findById(long id){
		Employee result = null;
		try {
			ResultSet sqlResult = SqlConnection.execQuery("SELECT * FROM \"employee\" WHERE id = " + id + ";");
			
			while (sqlResult.next()) {
				Employee employee = (
					new Employee(
						sqlResult.getString("first_name"),
						sqlResult.getString("last_name"),
						sqlResult.getString("email_id")
					)
				);
				employee.setId(sqlResult.getLong("id"));
				result = employee;
			}
		} catch (SQLException e) {
		}
		return result;
	}
	
	public static Employee save(Employee employee){
		try {
			PreparedStatement stm = SqlConnection.sqlConnect().prepareStatement(
				"INSERT INTO \"employee\"(first_name, last_name, email_id) VALUES (?, ?, ?);"
			);
			stm.setString(1, employee.getFirstName());
			stm.setString(2, employee.getLastName());
			stm.setString(3, employee.getEmailId());
			stm.executeUpdate();
			
		} catch (SQLException e) {
		}
		return employee;
	}

	public static Employee update(Employee employee){
		try {
			PreparedStatement stm = SqlConnection.sqlConnect().prepareStatement(
				(
					"UPDATE \"employee\""
				) + " " + (
					"SET " + " " + (
						"first_name = ?,"
					 ) + " " + (
						"last_name = ?,"
					 ) + " " + (
						"email_id = ?"
					 )
				) + " " + (
					"WHERE id = ?;"
				)
			);
			stm.setString(1, employee.getFirstName());
			stm.setString(2, employee.getLastName());
			stm.setString(3, employee.getEmailId());
			stm.setLong(4, employee.getId());
			stm.executeUpdate();
			
		} catch (SQLException e) {
		}
		return employee;
	}
	
	public static void delete(Long id){
		try {
			PreparedStatement stm = SqlConnection.sqlConnect().prepareStatement(
				(
					"DELETE FROM \"employee\""
				) + " " + (
					"WHERE id = ?;"
				)
			);
			stm.setLong(1, id);
			stm.executeUpdate();
			
		} catch (SQLException e) {
		}
	}
}
