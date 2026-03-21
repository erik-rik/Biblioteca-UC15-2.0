/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package biblioteca.uc15.test;



/**
 *
 * @author PAULO
 */
import biblioteca.uc15.service.EmprestimoUtils;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmprestimoUtilsTest {

    @Test
    public void deveCalcularDiasCorretamente() {

        Calendar cal = Calendar.getInstance();

        cal.set(2025, Calendar.MAY, 1);
        Date inicio = cal.getTime();

        cal.set(2025, Calendar.MAY, 5);
        Date fim = cal.getTime();

        long dias = EmprestimoUtils.calcularDiasEmprestimo(inicio, fim);

        assertEquals(4, dias);
    }

    @Test
    public void deveRetornarZeroQuandoMesmaData() {

        Calendar cal = Calendar.getInstance();

        cal.set(2025, Calendar.MAY, 1);
        Date data1 = cal.getTime();

        cal.set(2025, Calendar.MAY, 1);
        Date data2 = cal.getTime();

        long dias = EmprestimoUtils.calcularDiasEmprestimo(data1, data2);

        assertEquals(0, dias);
    }
    
    @Test
    public void deveLancarErroQuandoDataInvalida() {

        Calendar cal = Calendar.getInstance();

        cal.set(2025, Calendar.MAY, 5);
        Date inicio = cal.getTime();

        cal.set(2025, Calendar.MAY, 1);
        Date fim = cal.getTime();

        EmprestimoUtils.calcularDiasEmprestimo(inicio, fim);
    }
}

