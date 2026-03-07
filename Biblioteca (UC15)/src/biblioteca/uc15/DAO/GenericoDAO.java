/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.DAO;

/**
 *
 * @author PAULO
 */

import java.util.List;

public interface GenericoDAO<X> {
    
    void salvar(X entidade);
    void atualizar(X entidade);
    boolean excluir(int id);
    X buscarPorId(int id);
    List<X> listar();
}
