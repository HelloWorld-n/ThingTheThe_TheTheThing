package com.onboarding.onboarding;

import java.lang.reflect.Method;
import java.sql.ResultSet;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

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

	@WebPage(address = "/")
	public String index() {
		String result = "[";
		WebSite webSite = WebSite.create();
		for (Method method : webSite.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(WebPage.class)) {
				WebPage webPage = method.getAnnotation(WebPage.class);
				String addressString = webPage.address();
				result += "\"" + addressString + "\", ";
			}
		}
		result += "]";
		return result;
	}

	@WebPage(address = "/prepare")
	public String page__prepare() {
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
	
	@WebPage(address = "/fill/randomThing")
	public String page__fill_randomThing() {
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

	@WebPage(address = "/fetch/randomThing")
	public String page__fetch_randomThing() {
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
		return result;
	}

	@WebPage(address = "/check")
	public String page__check() {
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

	@WebPage(address = "/randomBoolean")
	public String page__randomBoolean() {
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
	
}
