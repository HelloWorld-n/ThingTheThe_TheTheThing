package com.onboarding.onboarding;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@CrossOrigin(origins = "http://[::1]:8080")
@RestController
@RequestMapping("/")
public class WebSite {


	@Autowired
	public WebSite(){
	}


	@GetMapping(value = "/", headers = "Accept=application/json")
	public String index(ModelMap model) {
		return PageUtil.fetchAllPages(WebSite.class, model);
	}

	@GetMapping(value = "/prepare", headers = "Accept=application/json")
	public String page__prepare(ModelMap Model) {
		String result = "{";

		try {
			SqlConnection.execAlteringQuery(
				"CREATE TABLE \"randomThing\" (" + (
					"id SERIAL PRIMARY KEY" 
				) + ", " + (
					"value int NOT NULL"
				) + ");"
			);
			result += "\"randomThing\": \"added\", ";
		} catch (SQLException e){
			result += "\"randomThing\": \"kept\", ";
		}

		try {
			SqlConnection.execAlteringQuery(
				"CREATE TABLE \"employee\" (" + (
					"id SERIAL PRIMARY KEY" 
				) + ", " + (
					"first_name varchar NOT NULL"
				) + ", " + (
					"last_name varchar NOT NULL"
				) + ", " + (
					"email_id varchar NOT NULL"
				) + ");"
			);
			result += "\"employee\": \"added\", ";
		} catch (SQLException e){
			result += "\"employee\": \"kept\", ";
		}

		result += "}";
		return result.replaceAll(",\\s*\\}", "}");
	}	
	
	@GetMapping(value = "/randomThing/fill", headers = "Accept=application/json")
	public String page__fill_randomThing(ModelMap Model) {
		String result = "[";
		try {
			SqlConnection.execAlteringQuery(
				"INSERT INTO randomThing(" + (
					"value" 
				) + ") VALUES (" + (
					"floor(random() * 16)"
				) + ");"
			);
		} catch (SQLException e){			
		}
		result += "]";
		return result;
	}

	@GetMapping(value = "/randomThing/find", headers = "Accept=application/json")
	public String page__randomThing_find(ModelMap Model) {
		String result = "[";
		try {
			ResultSet sqlResult = SqlConnection.execQuery("SELECT * FROM randomThing;");
			
			sqlResult.next();
			while (true) {
				result += "{" + (
					"\"id\": " + Integer.toString(sqlResult.getInt("id"))
				) + ", " + (
					"\"value\": " + Integer.toString(sqlResult.getInt("value"))
				) + "}";
				if (sqlResult.next()){
					result += ", ";
				} else {
					break;
				}
			}
		} catch (SQLException e) {
			System.err.print("\u001B[48;02;255;0;0m\u001B[38;02;0;0;0m[ERROR]\u001B[0m Can't run query!\n");
			result += "{\"error\": \"can't run query\"}";
		}
		result += "]";
		return result;
	}


	@GetMapping(value = "/randomThing/find/{id}", headers = "Accept=application/json")
	public String page__randomThing_find(ModelMap Model, @PathVariable Long id) {
		String result = "{}";
		try {
			ResultSet sqlResult = SqlConnection.execQuery(
				"SELECT * FROM randomThing WHERE id = " + id + ";"
			);
			
			sqlResult.next();
			result = "{" + (
				"\"id\": " + Integer.toString(sqlResult.getInt("id"))
			) + ", " + (
				"\"value\": " + Integer.toString(sqlResult.getInt("value"))
			) + "}";
		} catch (SQLException e) {
			System.err.print("\u001B[48;02;255;0;0m\u001B[38;02;0;0;0m[ERROR]\u001B[0m Can't run query!\n");
			result = "{\"error\": \"can't run query\"}";
		}
		return result;
	}

	@GetMapping(value = "/check", headers = "Accept=application/json")
	public String page__check(ModelMap Model) {
		String result = "[";
		try {
			ResultSet sqlResult = SqlConnection.execQuery("SELECT 3 + 5, 3 - 5, 3 * 5, 3 / 5;");
			
			sqlResult.next();
			while (true) {
				result += "[" + (
					Integer.toString(sqlResult.getInt(1))
				) + ", " + (
					Integer.toString(sqlResult.getInt(2))
				) + ", " + (
					Integer.toString(sqlResult.getInt(3))
				) + ", " + (
					Integer.toString(sqlResult.getInt(4))
				) + "]";
				if (sqlResult.next()){
					result += ", ";
				} else {
					break;
				}
			}
		} catch (SQLException e) {
			System.err.print("\u001B[48;02;255;0;0m\u001B[38;02;0;0;0m[ERROR]\u001B[0m Can't run query!\n");
		}
		result += "]";
		return result;
	}

	@GetMapping(value = "/randomBoolean", headers = "Accept=application/json")
	public String page__randomBoolean(ModelMap Model) {
		String result = "[";
		try {
			ResultSet sqlResult = SqlConnection.execQuery("SELECT random() > 0.5, random() > 0.5, random() > 0.5, random() > 0.5;");
			
			sqlResult.next();
			while (true) {
				result += "[" + (
					Boolean.toString(sqlResult.getBoolean(1))
				) + ", " + (
					Boolean.toString(sqlResult.getBoolean(2))
				) + ", " + (
					Boolean.toString(sqlResult.getBoolean(3))
				) + ", " + (
					Boolean.toString(sqlResult.getBoolean(4))
				) + "]";
				if (sqlResult.next()){
					result += ", ";
				} else {
					break;
				}
			}
		} catch (SQLException e) {
			System.err.print("\u001B[48;02;255;0;0m\u001B[38;02;0;0;0m[ERROR]\u001B[0m Can't run query!\n");
		}
		result += "]";
		return result;
	}

	@GetMapping(value = "/error")
	public String page__error(ModelMap Model) {
		return "<h1>Error</h1>";
	}
	
}
