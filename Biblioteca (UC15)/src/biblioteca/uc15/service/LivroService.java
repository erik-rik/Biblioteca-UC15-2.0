/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.service;

import biblioteca.uc15.DAO.LivroDAO;
import biblioteca.uc15.model.Livro;
import java.util.List;

/**
 *
 * @author PAULO
 */
public class LivroService {
    
    private LivroDAO livroDAO;

    public LivroService() {
        this.livroDAO = new LivroDAO();
    }

    public void cadastrarLivro(Livro livro) {

        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Título do livro é obrigatório.");
        }

        if (livro.getQuantidadeDisponivel() < 0) {
            throw new IllegalArgumentException("Quantidade inválida.");
        }

        livroDAO.salvar(livro);
    }

    public void atualizarLivro(Livro livro) {
        livroDAO.atualizar(livro);
    }

    public boolean excluirLivro(int id) {
        return livroDAO.excluir(id);
    }

    public Livro buscarLivro(int id) {
        return livroDAO.buscarPorId(id);
    }
    
    public List<Livro> buscarPorTitulo(String titulo) {
        return livroDAO.buscarPorTitulo(titulo);
    }

    public List<Livro> buscarPorAutor(String autor) {
        return livroDAO.buscarPorAutor(autor);
    }

    public List<Livro> buscarPorCategoria(String categoria) {
        return livroDAO.buscarPorCategoria(categoria);
    }

    public List<Livro> listarLivros() {
        return livroDAO.listar();
    }
}
