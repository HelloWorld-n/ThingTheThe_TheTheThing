package com.onboarding.onboarding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

class OnboardingServer {
	private static OnboardingServer onboardingServer = null;
	
	private static final int port = 8080;

	public static String loadPage(String address){
		WebSite webSite = WebSite.create();
		String result = "Content-Type: application/json; charset=utf-8\n\n";
		for (Method method : webSite.getClass().getDeclaredMethods()) {
			try {
				if (method.isAnnotationPresent(WebPage.class)) {
					WebPage webPage = method.getAnnotation(WebPage.class);
					if (webPage.address().equals(address)){
						result += method.invoke(webSite);
						break;
					}
				}
			} catch(IllegalAccessException | InvocationTargetException e){
			}
		}
		return result;
	}

	public static void listen() {
		try {
			ServerSocket server = new ServerSocket(port);
			while (true) {
				Socket socket = server.accept();
				InetAddress addr = socket.getInetAddress();

				
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream(); 
				BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
				PrintWriter out = new PrintWriter(outputStream);

				 
				String address = in.readLine().split(" ")[1];
				System.out.println((
					"Connection made to " + addr.getHostName() 
				) + (
					" [" + addr.getHostAddress() + "]:" + port + address
				));
				out.print(loadPage(address));
				socket.close();
			}
		} catch (IOException e) {
			System.out.println("Exception detected: " + e);
		}
	}

}

@SpringBootApplication
public class Onboarding {
	public static WebSite webSite = WebSite.create();

	public static void main(String[] args){
		//SpringApplication.run(Onboarding.class, args);

		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[BEGIN]\u001B[0m\n");
		OnboardingServer.listen();


		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[END]\u001B[0m\n");
	}
}
