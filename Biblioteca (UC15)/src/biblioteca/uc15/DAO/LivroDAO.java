/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.DAO;

/**
 *
 * @author PAULO
 */
import biblioteca.uc15.model.Livro;
import biblioteca.uc15.conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO implements GenericoDAO<Livro> {

    @Override
    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, ano, categoria, quantidade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getCategoria());
            stmt.setInt(5, livro.getQuantidadeDisponivel());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar livro", e);
        }
    }

    @Override
    public void atualizar(Livro livro) {
        String sql = "UPDATE livro SET titulo=?, autor=?, ano=?, categoria=?, quantidade=? WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getCategoria());
            stmt.setInt(5, livro.getQuantidadeDisponivel());
            stmt.setInt(6, livro.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar livro", e);
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM livro WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir livro", e);
        }
    }

    @Override
    public Livro buscarPorId(int id) {
        String sql = "SELECT * FROM livro WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapLivro(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro", e);
        }

        return null;
    }

    @Override
    public List<Livro> listar() {
        String sql = "SELECT * FROM livro";
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                livros.add(mapLivro(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar livros", e);
        }

        return livros;
    }

    private Livro mapLivro(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setId(rs.getInt("id"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutor(rs.getString("autor"));
        livro.setAno(rs.getInt("ano"));
        livro.setCategoria(rs.getString("categoria"));
        livro.setQuantidade(rs.getInt("quantidade"));
        return livro;
    }
}
