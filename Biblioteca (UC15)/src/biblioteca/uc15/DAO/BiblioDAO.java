/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.DAO;

import biblioteca.uc15.conexao.Conexao;
import biblioteca.uc15.model.Emprestimo;
import biblioteca.uc15.model.Livro;
import biblioteca.uc15.model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PAULO
 */
public class BiblioDAO {
    
    private Conexao conexao;
    private Connection conn;
            
    public BiblioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO livro(titulo, autor, ano, categoria, quantidade) VALUES " + "(?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getCategoria());
            stmt.setInt(5, livro.getQuantidadeDisponivel());
            stmt.execute();
            
            
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro: " + e.getMessage());
        }
    }
    
    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, email, cpf) VALUES " + "(?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());            
            stmt.setString(3, usuario.getCPF());            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
    
    public void inserirEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo(usuario_id, livro_id, data_emprestimo, data_prevista) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, emprestimo.getUsuario().getId());  
            stmt.setInt(2, emprestimo.getLivro().getId());         
            stmt.setDate(3, (Date) emprestimo.getDataEmprestimo());     
            stmt.setDate(4, (Date) emprestimo.getDataPrevista()); 
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Empréstimo cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar emrpréstimo: " + e.getMessage());
        }
    }
    
    public Livro getLivros (int id){
        String sql = "SELECT * FROM livro WHERE id = ?";
        try {
                  
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
        
            Livro livro = new Livro();
          
            rs.next(); 
            livro.setId(id);
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setAno(rs.getInt("ano"));
            livro.setCategoria(rs.getString("categoria"));
            livro.setQuantidade(rs.getInt("quantidade"));

            return livro;
            
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }
    
    public Usuario getUsuarios (int id){
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
                  
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
        
            Usuario usuario = new Usuario();
          
            rs.next(); 
            usuario.setId(id);
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setCPF(rs.getString("cpf"));

            return usuario;
            
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }
    
    public Emprestimo getEmprestimos (int id){
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        try {
                  
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
        
            Emprestimo emprestimo = new Emprestimo();
          
            rs.next(); 
            emprestimo.setId(id);
            
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("usuario_id"));
            emprestimo.setUsuario(usuario);

            Livro livro = new Livro();
            livro.setId(rs.getInt("livro_id"));
            emprestimo.setLivro(livro);
            
            emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
            emprestimo.setDataPrevista(rs.getDate("data_prevista"));

            return emprestimo;
            
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }
    
    public boolean excluirLivro(int id) {
        String sql = "DELETE FROM livro WHERE id = ?";

        try {
            
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConexao();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir livro: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluirUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConexao();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluirEmprestimo(int id) {
        String sql = "DELETE FROM emprestimo WHERE id = ?";

        try {
            
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConexao();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir empréstimo: " + e.getMessage());
            return false;
        }
    }
    
    public boolean atualizarLivro(Livro livro){
        String sql = "UPDATE livro SET titulo = ?, autor = ?, ano = ?, categoria = ?, quantidade = ? WHERE id = ?";
        
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConexao();
            
            PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getCategoria());
            stmt.setInt(5, livro.getQuantidadeDisponivel());
            stmt.setInt(6, livro.getId());
            stmt.execute();
            
            stmt.close();
            conn.close();
            
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir livro: " + e.getMessage());
            return false;
        }
    }
    
    public boolean atualizarUsuario(Usuario usuario){
        String sql = "UPDATE usuario SET nome = ?, email = ?, cpf = ? WHERE id = ?";
        
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConexao();
            
            PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCPF());
            stmt.setInt(4, usuario.getId());
            stmt.execute();
            
            stmt.close();
            conn.close();
            
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
            return false;
        }
    }   
    
    public List<Livro> getLivros(String nomeLivro) {
        String sql = "SELECT * FROM livro WHERE titulo LIKE ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeLivro + "%");
            ResultSet rs = stmt.executeQuery();

            List<Livro> listaLivros = new ArrayList<>();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano"));
                livro.setCategoria(rs.getString("categoria"));
                livro.setQuantidade(rs.getInt("quantidade"));

                listaLivros.add(livro);
            }

            return listaLivros;
        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    public List<Livro> getLivrosAutor(String nomeLivro) {
        String sql = "SELECT * FROM livro WHERE autor LIKE ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeLivro + "%");
            ResultSet rs = stmt.executeQuery();

            List<Livro> listaLivros = new ArrayList<>();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano"));
                livro.setCategoria(rs.getString("categoria"));
                livro.setQuantidade(rs.getInt("quantidade"));

                listaLivros.add(livro);
            }

            return listaLivros;
        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    public List<Livro> getLivrosCategoria(String nomeLivro) {
        String sql = "SELECT * FROM livro WHERE categoria LIKE ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeLivro + "%");
            ResultSet rs = stmt.executeQuery();

            List<Livro> listaLivros = new ArrayList<>();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano"));
                livro.setCategoria(rs.getString("categoria"));
                livro.setQuantidade(rs.getInt("quantidade"));

                listaLivros.add(livro);
            }

            return listaLivros;
        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    public List<Usuario> getUsuarios(String nomeUsuario) {
        String sql = "SELECT * FROM usuario WHERE nome LIKE ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeUsuario + "%");
            ResultSet rs = stmt.executeQuery();

            List<Usuario> listaUsuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCPF(rs.getString("cpf"));

                listaUsuarios.add(usuario);
            }

            return listaUsuarios;
        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    public List<Usuario> getUsuariosEmail(String nomeUsuario) {
        String sql = "SELECT * FROM usuario WHERE email LIKE ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeUsuario + "%");
            ResultSet rs = stmt.executeQuery();

            List<Usuario> listaUsuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCPF(rs.getString("cpf"));

                listaUsuarios.add(usuario);
            }

            return listaUsuarios;
        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    public List<Usuario> getUsuariosCPF(String nomeUsuario) {
        String sql = "SELECT * FROM usuario WHERE cpf LIKE ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeUsuario + "%");
            ResultSet rs = stmt.executeQuery();

            List<Usuario> listaUsuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCPF(rs.getString("cpf"));

                listaUsuarios.add(usuario);
            }

            return listaUsuarios;
        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    public List<Emprestimo> getEmprestimos(String nomeUsuario) {
        String sql = "SELECT e.id, e.data_emprestimo, e.data_prevista, " +
                 "u.nome AS usuario_nome, " +
                 "l.titulo AS livro_titulo " +
                 "FROM emprestimo e " +
                 "INNER JOIN usuario u ON e.usuario_id = u.id " +
                 "INNER JOIN livro l ON e.livro_id = l.id " +
                 "WHERE u.nome LIKE ?";

        List<Emprestimo> listaEmprestimos = new ArrayList<>();

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeUsuario + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataPrevista(rs.getDate("data_prevista"));

                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("usuario_nome"));
                emprestimo.setUsuario(usuario);

                Livro livro = new Livro();
                livro.setTitulo(rs.getString("livro_titulo"));
                emprestimo.setLivro(livro);

                listaEmprestimos.add(emprestimo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmprestimos;
    }
    
     public List<Emprestimo> getEmprestimosLivro(String nomeUsuario) {
        String sql = "SELECT e.id, e.data_emprestimo, e.data_prevista, " +
                 "u.nome AS usuario_nome, " +
                 "l.titulo AS livro_titulo " +
                 "FROM emprestimo e " +
                 "INNER JOIN usuario u ON e.usuario_id = u.id " +
                 "INNER JOIN livro l ON e.livro_id = l.id " +
                 "WHERE l.titulo LIKE ?";

        List<Emprestimo> listaEmprestimos = new ArrayList<>();

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeUsuario + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataPrevista(rs.getDate("data_prevista"));

                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("usuario_nome"));
                emprestimo.setUsuario(usuario);

                Livro livro = new Livro();
                livro.setTitulo(rs.getString("livro_titulo"));
                emprestimo.setLivro(livro);

                listaEmprestimos.add(emprestimo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmprestimos;
    }
}
