package com.example.biblioteca.repository;

import com.example.biblioteca.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioBibliotecaImpl implements RepositorioBiblioteca {

    @Override
    public void adicionarLivro(Livro livro) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:db;USER=sa;PASSWORD=")) {
            String sql = "INSERT INTO livro (titulo, autor, num_copias) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setInt(3, livro.getNumCopias());
            statement.executeUpdate();
            System.out.println("Livro cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro");
            e.printStackTrace();
        }
    }

    @Override
    public List<Livro> listarLivrosNaoEmprestados() {
        List<Livro> livrosNaoEmprestados = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:db;USER=sa;PASSWORD=")) {
            String sql = "SELECT * FROM livro WHERE emprestado = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int numCopias = resultSet.getInt("num_copias");

                Livro livro = new Livro(titulo, autor, numCopias);
                livrosNaoEmprestados.add(livro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros não emprestados");
            e.printStackTrace();

        }

        return livrosNaoEmprestados;

    }

    @Override
    public void emprestarLivro(String titulo) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:db;USER=sa;PASSWORD=")) {
            String sql = "UPDATE livro SET emprestado = ? WHERE titulo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setString(2, titulo);
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Livro emprestado com sucesso!");
            } else {
                System.out.println("Não foi possível encontrar o livro especificado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao emprestar o livro");
            e.printStackTrace();
        }

    }

    @Override
    public void devolverLivro(String titulo) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:db;USER=sa;PASSWORD=")) {
            String sql = "UPDATE livro SET emprestado = ? WHERE titulo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false);
            statement.setString(2, titulo);
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Livro devolvido com sucesso!");
            } else {
                System.out.println("Não foi possível encontrar o livro especificado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao devolver o livro");
            e.printStackTrace();
        }
    }
}
