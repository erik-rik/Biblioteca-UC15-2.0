/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.DAO;

/**
 *
 * @author PAULO
 */
import biblioteca.uc15.model.Emprestimo;
import biblioteca.uc15.conexao.Conexao;
import biblioteca.uc15.model.Livro;
import biblioteca.uc15.model.Usuario;
        
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
        
public class EmprestimoDAO implements GenericoDAO<Emprestimo> {
    
    @Override
    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo(usuario_id, livro_id, data_emprestimo, data_prevista) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(4, new java.sql.Date(emprestimo.getDataPrevista().getTime()));

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Empréstimo cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar empréstimo", e);
        }
    }
    
    @Override
    public void atualizar(Emprestimo emprestimo) {

        String sql = "UPDATE emprestimo SET usuario_id=?, livro_id=?, data_emprestimo=?, data_prevista=? WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(4, new java.sql.Date(emprestimo.getDataPrevista().getTime()));
            stmt.setInt(5, emprestimo.getId());

            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Empréstimo atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar empréstimo", e);
        }
    }
    
    @Override
    public boolean excluir(int id) {
        String sql = "DELETE FROM emprestimo WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Emprestimo buscarPorId(int id) {
        String sql = "SELECT * FROM emprestimo WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapEmprestimo(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empréstimo", e);
        }

        return null;
    }
    
    public List<Emprestimo> buscarPorUsuario(String nomeUsuario) {

        String sql = """
                SELECT e.id, e.data_emprestimo, e.data_prevista,
                       u.id AS usuario_id, u.nome,
                       l.id AS livro_id, l.titulo
                FROM emprestimo e
                INNER JOIN usuario u ON e.usuario_id = u.id
                INNER JOIN livro l ON e.livro_id = l.id
                WHERE u.nome LIKE ?
                """;

        List<Emprestimo> lista = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nomeUsuario + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapEmprestimoJoin(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empréstimos por usuário", e);
        }

        return lista;
    }

    
    public List<Emprestimo> buscarPorLivro(String tituloLivro) {

        String sql = """
                SELECT e.id, e.data_emprestimo, e.data_prevista,
                       u.id AS usuario_id, u.nome,
                       l.id AS livro_id, l.titulo
                FROM emprestimo e
                INNER JOIN usuario u ON e.usuario_id = u.id
                INNER JOIN livro l ON e.livro_id = l.id
                WHERE l.titulo LIKE ?
                """;

        List<Emprestimo> lista = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + tituloLivro + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapEmprestimoJoin(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empréstimos por livro", e);
        }

        return lista;
    }

    
    @Override
    public List<Emprestimo> listar() {
        String sql = "SELECT e.id, e.data_emprestimo, e.data_prevista, u.id AS usuario_id, u.nome, l.id AS livro_id, l.titulo "
                + "FROM emprestimo e INNER JOIN usuario u ON e.usuario_id = u.id INNER JOIN livro l ON e.livro_id = l.id";
         
        List<Emprestimo> lista = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapEmprestimoJoin(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar empréstimos", e);
        }

        return lista;
    }

    public boolean existeEmprestimoUsuario(int usuarioId) {

        String sql = "SELECT 1 FROM emprestimo WHERE usuario_id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean existeEmprestimoLivro(int livroId) {

        String sql = "SELECT 1 FROM emprestimo WHERE livro_id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, livroId);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Emprestimo mapEmprestimo(ResultSet rs) throws SQLException {

        Emprestimo e = new Emprestimo();

        e.setId(rs.getInt("id"));

        Usuario u = new Usuario();
        u.setId(rs.getInt("usuario_id"));
        e.setUsuario(u);

        Livro l = new Livro();
        l.setId(rs.getInt("livro_id"));
        e.setLivro(l);

        e.setDataEmprestimo(rs.getDate("data_emprestimo"));
        e.setDataPrevista(rs.getDate("data_prevista"));

        return e;
    }
    
    private Emprestimo mapEmprestimoJoin(ResultSet rs) throws SQLException {

        Emprestimo e = new Emprestimo();

        e.setId(rs.getInt("id"));
        e.setDataEmprestimo(rs.getDate("data_emprestimo"));
        e.setDataPrevista(rs.getDate("data_prevista"));

        Usuario u = new Usuario();
        u.setId(rs.getInt("usuario_id"));
        u.setNome(rs.getString("nome"));
        e.setUsuario(u);

        Livro l = new Livro();
        l.setId(rs.getInt("livro_id"));
        l.setTitulo(rs.getString("titulo"));
        e.setLivro(l);

        return e;
    }
}
