/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.service;

import biblioteca.uc15.DAO.UsuarioDAO;
import biblioteca.uc15.model.Usuario;
import java.util.List;

/**
 *
 * @author PAULO
 */
public class UsuarioService {
    
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void cadastrarUsuario(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }

        if (usuario.getCPF() == null || usuario.getCPF().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }

        usuarioDAO.salvar(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
    }

    public boolean excluirUsuario(int id) {
        return usuarioDAO.excluir(id);
    }

    public Usuario buscarUsuario(int id) {
        return usuarioDAO.buscarPorId(id);
    }
    
    public List<Usuario> buscarPorNome(String nome) {
        return usuarioDAO.buscarPorNome(nome);
    }

    public List<Usuario> buscarPorEmail(String email) {
        return usuarioDAO.buscarPorEmail(email);
    }

    public List<Usuario> buscarPorCPF(String cpf) {
        return usuarioDAO.buscarPorCPF(cpf);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }
}
