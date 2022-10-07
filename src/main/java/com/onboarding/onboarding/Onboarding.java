package com.onboarding.onboarding;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class Onboarding {

	public static void main(String[] args){
		WebSite webSite = WebSite.create();
		//SpringApplication.run(Onboarding.class, args);

		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[BEGIN]\u001B[0m\n");

		String desiredPage = "/";
		for (Method method : webSite.getClass().getDeclaredMethods()) {
			try {
				if (method.isAnnotationPresent(WebPage.class)) {
					System.out.println(method.invoke(webSite));
				}
			} catch(IllegalAccessException | InvocationTargetException e){
			}
		}	

		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[END]\u001B[0m\n");
	}
}
