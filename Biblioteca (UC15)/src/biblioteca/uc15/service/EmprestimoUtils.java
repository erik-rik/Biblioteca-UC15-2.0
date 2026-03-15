/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author PAULO
 */
public class EmprestimoUtils {
    
    public static long calcularDiasEmprestimo(Date dataEmprestimo, Date dataDevolucao) {

        long diferenca = dataDevolucao.getTime() - dataEmprestimo.getTime();

        return TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
    }
}
