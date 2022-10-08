package com.onboarding.onboarding;

import java.lang.reflect.Method;
import java.sql.ResultSet;

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

	private static WebSite webSite = null;

	private WebSite(){
	}

	public static WebSite create(){
		if (webSite == null){
			webSite = new WebSite();
		}
		return webSite;
	}

	@GetMapping("/")
	public String index(ModelMap Model) {
		String result = "[";
		WebSite webSite = WebSite.create();
		for (Method method : webSite.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(GetMapping.class)) {
				GetMapping getMapping = method.getAnnotation(GetMapping.class);
				String addressString = getMapping.value()[0];
				result += "\"" + addressString + "\", ";
			}
		}
		result += "]";
		return result;
	}

	@GetMapping("/prepare")
	public String page__prepare(ModelMap Model) {
		String result = "{";

		try {
			SqlConnection.sqlConnect();
			SqlConnection.execAlteringQuery(
				"CREATE TABLE randomThing(" + (
					"id SERIAL PRIMARY KEY" 
				) + ", " + (
					"value int NOT NULL"
				) + ");"
			);
			result += "\"randomThing\": \"added\", ";
		} catch (SQLException e){
			result += "\"randomThing\": \"kept\", ";
		}
		

		result += "}";
		return result;
	}	
	
	@GetMapping("/fill/randomThing")
	public String page__fill_randomThing(ModelMap Model) {
		String result = "[";
		try {
			SqlConnection.sqlConnect();
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

	@GetMapping("/fetch/randomThing")
	public String page__fetch_randomThing(ModelMap Model) {
		String result = "[";
		try {
			SqlConnection.sqlConnect();
			ResultSet sqlResult = SqlConnection.execQuery("SELECT value FROM randomThing;");
			
			sqlResult.next();
			while (true) {
				result += "[" + (
					Integer.toString(sqlResult.getInt(1))
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

	@GetMapping("/check")
	public String page__check(ModelMap Model) {
		String result = "[";
		try {
			SqlConnection.sqlConnect();
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

	@GetMapping("/randomBoolean")
	public String page__randomBoolean(ModelMap Model) {
		String result = "[";
		try {
			SqlConnection.sqlConnect();
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

	@GetMapping("/error")
	public String page__error(ModelMap Model) {
		return "<h1>Error</h1>";
	}
	
}
