package com.onboarding.onboarding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Onboarding {
	public static WebSite webSite = WebSite.create();

	public static void main(String[] args){
		SpringApplication springApplication = new SpringApplication(Onboarding.class);

		System.out.print("\u001B[48;02;255;255;255m\u001B[38;02;0;0;0m[ARGS!]\u001B[0m\n");
		for (String arg : args){
			System.out.println("[ARG] " + arg);
		}

		springApplication.run(args);

	}
}
