/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.model;

/**
 *
 * @author PAULO
 */
public class Livro {
    
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private String categoria;
    private int quantidade;
    
    public int getId() { 
        return id; 
    }
    
    public String getTitulo() { 
        return titulo; 
    }
    
    public int getAno() {
        return ano;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public String getAutor() { 
        return autor; 
    }
    
    public int getQuantidadeDisponivel() {  
        return quantidade; 
    }

    public void emprestar() { 
        quantidade--; 
    }
    
    public void devolver() { 
        quantidade++; 
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
            
    public void setAutor (String autor) {
        this.autor = autor;
    }
            
    public void setAno (int ano) {
        this.ano = ano;
    }
            
    public void setCategoria (String categoria) {
        this.categoria = categoria;
    }
            
    public void setQuantidade (int quantidade) {
        this.quantidade = quantidade;
    }        
    
    @Override
    public String toString() {
        return this.titulo; 
    }
}
