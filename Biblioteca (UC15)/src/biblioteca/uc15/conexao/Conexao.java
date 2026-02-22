/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.conexao;

/**
 *
 * @author PAULO
 */

import java.sql.Connection;
import java.sql.DriverManager;
            
public class Conexao {
            
    public Connection getConexao() {
            
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "SENAC");
            return conn;
            
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}
