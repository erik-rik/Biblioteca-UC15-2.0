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

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar empréstimo", e);
        }
    }
    
    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM emprestimo WHERE id=?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir empréstimo", e);
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
    
    @Override
    public List<Emprestimo> listar() {
        String sql = "SELECT e.id, e.data_emprestimo, e.data_prevista, u.id AS usuario_id, u.nome, l.id AS livro_id, l.titulo "
                + "FROM emprestimo e INNER JOIN usuario u ON e.usuario_id = u.id INNER JOIN livro l ON e.livro_id = l.id";
         
        List<Emprestimo> lista = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapEmprestimoComJoin(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar empréstimos", e);
        }

        return lista;
    }

    
    private Emprestimo mapEmprestimo(ResultSet rs) throws SQLException {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(rs.getInt("id"));
        emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
        emprestimo.setDataPrevista(rs.getDate("data_prevista"));
        
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("usuario_id"));
        emprestimo.setUsuario(usuario);

        Livro livro = new Livro();
        livro.setId(rs.getInt("livro_id"));
        emprestimo.setLivro(livro);

        return emprestimo;
    }
    
    private Emprestimo mapEmprestimoComJoin(ResultSet rs) throws SQLException {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(rs.getInt("id"));
        emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
        emprestimo.setDataPrevista(rs.getDate("data_prevista"));

        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("usuario_id"));
        usuario.setNome(rs.getString("nome"));
        emprestimo.setUsuario(usuario);

        Livro livro = new Livro();
        livro.setId(rs.getInt("livro_id"));
        livro.setTitulo(rs.getString("titulo"));
        emprestimo.setLivro(livro);

        return emprestimo;
    }
}
