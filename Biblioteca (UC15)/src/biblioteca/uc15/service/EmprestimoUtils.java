/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.uc15.service;

/**
 *
 * @author PAULO
 */
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EmprestimoUtils {

    public static long calcularDiasEmprestimo(Date dataEmprestimo, Date dataDevolucao) {

        if (dataEmprestimo == null || dataDevolucao == null) {
            throw new IllegalArgumentException("Datas não podem ser nulas");
        }

        long diferenca = dataDevolucao.getTime() - dataEmprestimo.getTime();

        return TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
    }

}
