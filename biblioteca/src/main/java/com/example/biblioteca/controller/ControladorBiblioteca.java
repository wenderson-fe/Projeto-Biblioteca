package com.example.biblioteca.controller;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repository.RepositorioBiblioteca;

import java.util.List;
import java.util.Scanner;

public class ControladorBiblioteca {
    private RepositorioBiblioteca repositorioBiblioteca;
    Scanner sc = new Scanner(System.in);

    public ControladorBiblioteca(RepositorioBiblioteca repositorioBiblioteca) {
        this.repositorioBiblioteca = repositorioBiblioteca;
    }

    public void exibirMenu() {
        System.out.println("--------- Menu ----------");
        System.out.println("1. Cadastrar Livro");
        System.out.println("-------------------------");
        System.out.println("2. Listar Livros ");
        System.out.println("-------------------------");
        System.out.println("3. Emprestar Livro");
        System.out.println("-------------------------");
        System.out.println("4. Devolver Livro");
        System.out.println("-------------------------");
        System.out.println("5. Sair do Sistema");
        System.out.println("-------------------------");
    }

    public void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarLivro();
                break;

            case 2:
                listarLivrosNaoEmprestados();
                break;

            case 3:
                emprestarLivro();
                break;

            case 4:
                devolverLivro();
                break;

            case 5:
                saindoDoSistema();
                break;

            default:
                System.out.println("Opção inválida!");
                break;
        }

    }

    public void cadastrarLivro() {
        // Receber informações do livro
        System.out.println("Digite o título do livro:");
        String titulo = sc.nextLine();
        System.out.println("Digite o autor do livro:");
        String autor = sc.nextLine();
        System.out.println("Digite o número de cópias do livro:");
        int numCopias = Integer.parseInt(sc.nextLine());

        // Criar objeto do livro com as informações recebidas
        Livro novoLivro = new Livro(titulo, autor, numCopias);

        // Adicionar o livro ao repositório
        repositorioBiblioteca.adicionarLivro(novoLivro);
    }

    public void listarLivrosNaoEmprestados() {
        //Chama o método "listarLivrosNaoEmprestados" e obtém seu retorno
        List<Livro> livrosNaoEmprestados = repositorioBiblioteca.listarLivrosNaoEmprestados();
        if (livrosNaoEmprestados.isEmpty()) {
            System.out.println("Não há livros disponíveis para empréstimo no momento.");
        } else {
            System.out.println("Livros disponíveis para empréstimo:");
            for (Livro livro : livrosNaoEmprestados) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Número de cópias: " + livro.getNumCopias());
                System.out.println("--------------------------------------");
            }
        }
    }

    public void emprestarLivro() {
        System.out.println("Digite o nome do livro: ");
        String titulo = sc.nextLine();
        repositorioBiblioteca.emprestarLivro(titulo);
    }

    public void devolverLivro() {
        System.out.println("Digite o nome do livro: ");
        String titulo = sc.nextLine();
        repositorioBiblioteca.devolverLivro(titulo);
    }

    public void saindoDoSistema() {
        System.out.println("Você realmente deseja sair do sistema? (S/N)");
        String resposta = sc.nextLine();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("Saindo do sistema...");
            System.exit(0);
        } else if (resposta.equalsIgnoreCase("N")) {
            System.out.println("Operação de saída cancelada.");

        } else {
            System.out.println("Opção inválida. Operação de saída cancelada.");
        }

    }
}
