package com.onboarding.onboarding;

import java.sql.SQLException;
import java.sql.ResultSet;

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

	@WebPage(page = "/")
	public String index() {
		return "[\"Hi!\"]";
	}
	
	@WebPage(page = "/check")
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
			System.out.print("\u001B[48;02;255;0;0m\u001B[38;02;0;0;0m[ERROR]\u001B[0m Can't run query!\n");
		}
		result += "]";
		return result;
	}
}
