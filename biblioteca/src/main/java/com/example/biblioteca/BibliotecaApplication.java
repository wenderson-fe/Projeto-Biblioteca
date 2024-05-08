package com.example.biblioteca;

import com.example.biblioteca.controller.ControladorBiblioteca;
import com.example.biblioteca.repository.RepositorioBiblioteca;
import com.example.biblioteca.repository.RepositorioBibliotecaImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
		Scanner sc = new Scanner(System.in);
		int continuar = 0;

		// Configuração e inicialização do repositório
		RepositorioBiblioteca repositorio = new RepositorioBibliotecaImpl();

		// Criação do controlador e injeção de dependência do repositório
		ControladorBiblioteca controlador = new ControladorBiblioteca(repositorio);

		do {
			controlador.exibirMenu();

			System.out.println("Escolha: ");
			int escolha;
			escolha = sc.nextInt();

			controlador.executarOpcao(escolha);

		} while (continuar != 5);

	}



}
