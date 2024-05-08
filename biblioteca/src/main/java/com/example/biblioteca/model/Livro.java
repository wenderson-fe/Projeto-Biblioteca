package com.example.biblioteca.model;

public class Livro {
    //private int id;
    private String titulo;
    private String autor;
    private int numCopias;
    private boolean emprestado = false;

    public Livro (String titulo, String autor, int numCopias) {
        this.titulo = titulo;
        this.autor = autor;
        this.numCopias = numCopias;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public boolean isEmprestado() {
        return emprestado;
    }
}
