/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package biblioteca.uc15.test;

import biblioteca.uc15.service.EmprestimoUtils;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PAULO
 */
public class EmprestimoUtilsTest {
    
    @Test
    public void testeCalcularDiasEmprestimo() {

        Calendar cal = Calendar.getInstance();

        cal.set(2025, Calendar.MAY, 1);
        Date dataEmprestimo = cal.getTime();

        cal.set(2025, Calendar.MAY, 5);
        Date dataDevolucao = cal.getTime();

        long dias = EmprestimoUtils.calcularDiasEmprestimo(dataEmprestimo, dataDevolucao);

        assertEquals(4, dias);
    }

    @Test
    public void testeMesmoDia() {

        Calendar cal = Calendar.getInstance();

        cal.set(2025, Calendar.MAY, 1);
        Date data1 = cal.getTime();

        cal.set(2025, Calendar.MAY, 1);
        Date data2 = cal.getTime();

        long dias = EmprestimoUtils.calcularDiasEmprestimo(data1, data2);

        assertEquals(0, dias);
    }
}
