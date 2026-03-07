/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.service;

import biblioteca.uc15.DAO.EmprestimoDAO;
import biblioteca.uc15.DAO.LivroDAO;
import biblioteca.uc15.DAO.UsuarioDAO;
import biblioteca.uc15.model.Emprestimo;
import biblioteca.uc15.model.Livro;
import biblioteca.uc15.model.Usuario;
import java.util.List;

/**
 *
 * @author PAULO
 */
public class EmprestimoService {
    
    private EmprestimoDAO emprestimoDAO;
    private LivroDAO livroDAO;
    private UsuarioDAO usuarioDAO;

    public EmprestimoService() {
        this.emprestimoDAO = new EmprestimoDAO();
        this.livroDAO = new LivroDAO();
        this.usuarioDAO = new UsuarioDAO();
    }

    public void realizarEmprestimo(Emprestimo emprestimo) {

        Livro livro = livroDAO.buscarPorId(emprestimo.getLivro().getId());
        Usuario usuario = usuarioDAO.buscarPorId(emprestimo.getUsuario().getId());

        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado.");
        }

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new IllegalArgumentException("Livro indisponível para empréstimo.");
        }

        livro.setQuantidade(livro.getQuantidadeDisponivel() - 1);
        livroDAO.atualizar(livro);

        emprestimoDAO.salvar(emprestimo);
    }

    public void devolverLivro(Emprestimo emprestimo) {

        Livro livro = livroDAO.buscarPorId(emprestimo.getLivro().getId());

        livro.setQuantidade(livro.getQuantidadeDisponivel() + 1);
        livroDAO.atualizar(livro);
    }

    public void excluirEmprestimo(int id) {
        emprestimoDAO.excluir(id);
    }

    public Emprestimo buscarEmprestimo(int id) {
        return emprestimoDAO.buscarPorId(id);
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimoDAO.listar();
    }
}
