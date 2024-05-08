package com.example.biblioteca.repository;

import com.example.biblioteca.model.Livro;

import java.util.List;

public interface RepositorioBiblioteca {
    void adicionarLivro(Livro livro);
    List<Livro> listarLivrosNaoEmprestados();
    void emprestarLivro(String titulo);
    void devolverLivro(String titulo);
}
