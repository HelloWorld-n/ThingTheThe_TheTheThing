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


	@WebPage(address = "/check")
	public String page__check() {
		String result = "[";
		try {
			SqlConnection.sqlConnect();
			ResultSet rs = SqlConnection.execQuery("SELECT 3 + 5, 3 - 5, 3 * 5, 3 / 5;");
			
			rs.next();
			while (true) {
				result += "[" + (
					Integer.toString(rs.getInt(1))
				) + ", " + (
					Integer.toString(rs.getInt(2))
				) + ", " + (
					Integer.toString(rs.getInt(3))
				) + ", " + (
					Integer.toString(rs.getInt(4))
				) + "]";
				if (rs.next()){
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
			ResultSet rs = SqlConnection.execQuery("SELECT random() > 0.5, random() > 0.5, random() > 0.5, random() > 0.5;");
			
			rs.next();
			while (true) {
				result += "[" + (
					Boolean.toString(rs.getBoolean(1))
				) + ", " + (
					Boolean.toString(rs.getBoolean(2))
				) + ", " + (
					Boolean.toString(rs.getBoolean(3))
				) + ", " + (
					Boolean.toString(rs.getBoolean(4))
				) + "]";
				if (rs.next()){
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
