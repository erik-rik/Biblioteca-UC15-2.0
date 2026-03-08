/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.DAO;

/**
 *
 * @author PAULO
 */
import biblioteca.uc15.conexao.Conexao;
import java.sql.*;

public class UserDAO {
    
    public boolean verificarLogin(String login, String senha) {

        String sql = "SELECT * FROM user WHERE login = ? AND senha = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
