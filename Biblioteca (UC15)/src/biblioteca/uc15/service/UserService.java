/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.service;

import biblioteca.uc15.DAO.UserDAO;

/**
 *
 * @author PAULO
 */
public class UserService {
    
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }
    
    public boolean autenticar(String login, String senha){
        return userDAO.verificarLogin(login, senha);
    }
}
