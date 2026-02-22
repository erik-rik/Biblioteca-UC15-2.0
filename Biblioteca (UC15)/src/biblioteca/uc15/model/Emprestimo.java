/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.model;

import java.util.Date;

/**
 *
 * @author PAULO
 */
public class Emprestimo {
    
    private int id;
    private Usuario usuario;
    private Livro livro;
    private Date dataEmprestimo;
    private Date dataPrevista;

    public int getId() { 
        return id; 
    }
    
    public Usuario getUsuario() { 
        return usuario; 
    }
    
    public Livro getLivro() { 
        return livro; 
    }
    
    public Date getDataEmprestimo() { 
        return dataEmprestimo; 
    }
    
    public Date getDataPrevista() { 
        return dataPrevista; 
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public void setDataPrevista (Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }
}