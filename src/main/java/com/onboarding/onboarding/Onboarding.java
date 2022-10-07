package com.onboarding.onboarding;

import java.lang.reflect.Method;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class Onboarding {
	public static WebSite webSite = WebSite.create();
		
	public static void loadPage(String address){
		for (Method method : webSite.getClass().getDeclaredMethods()) {
			try {
				if (method.isAnnotationPresent(WebPage.class)) {
					WebPage webPage = method.getAnnotation(WebPage.class);
					if (webPage.address().equals(address)){
						System.out.println(method.invoke(webSite));
						break;
					}
				}
			} catch(IllegalAccessException | InvocationTargetException e){
			}
		}	
	}

	public static void main(String[] args){
		//SpringApplication.run(Onboarding.class, args);

		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[BEGIN]\u001B[0m\n");

		
		loadPage("/check");
		loadPage("/");
		loadPage("/");
		loadPage("/");
		loadPage("/check");
		loadPage("/check");


		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[END]\u001B[0m\n");
	}
}
