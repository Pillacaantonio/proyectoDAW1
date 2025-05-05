package com.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class ProyectoDawiApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProyectoDawiApplication.class, args);
		try {
 			InetAddress localHost = InetAddress.getLocalHost();
			System.out.println("Nombre del host: " + localHost.getHostName());
			System.out.println("Dirección IP: " + localHost.getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("No se pudo obtener la dirección del host local.");
		}
	}

}
