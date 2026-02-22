/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.model;

/**
 *
 * @author PAULO
 */
public class Usuario {
    
    private int id;
    private String nome;
    private String email;
    private String cpf;

    public int getId() { 
        return id; 
    }
    
    public String getNome() { 
        return nome; 
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getCPF() {
        return cpf;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
            
    public void setEmail(String email) {
        this.email = email;
    }
            
    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
            
    @Override
    public String toString() {
        return this.nome;
    }
}