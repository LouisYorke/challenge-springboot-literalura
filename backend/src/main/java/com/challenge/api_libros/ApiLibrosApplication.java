package com.challenge.api_libros;

import com.challenge.api_libros.principal.Consola;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiLibrosApplication implements CommandLineRunner {
	private final Consola consola;

	public ApiLibrosApplication(Consola consola) {
		this.consola = consola;
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiLibrosApplication.class, args);
	}
	@Override
	public void run(String... args) {
		consola.muestraElMenu();
	}
}
