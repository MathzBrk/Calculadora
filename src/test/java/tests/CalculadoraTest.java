package tests;
import model.CalculadoraLogica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    private final CalculadoraLogica calculadora = new CalculadoraLogica();

    @Test
    public void testAdicao() {
        assertEquals(7, calculadora.calcular(3, 4, "+"));
    }

    @Test
    public void testSubtracao() {
        assertEquals(1, calculadora.calcular(5, 4, "-"));
    }

    @Test
    public void testMultiplicacao() {
        assertEquals(20, calculadora.calcular(5, 4, "*"));
    }

    @Test
    public void testDivisao() {
        assertEquals(2, calculadora.calcular(8, 4, "/"));
    }

    @Test
    public void testDivisaoPorZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.calcular(8, 0, "/");
        });
        assertEquals("Divisão por zero", exception.getMessage());
    }

    @Test
    public void testRaizQuadrada() {
        assertEquals(4, calculadora.raizQuadrada(16));
    }

    @Test
    public void testRaizQuadradaDeNumeroNegativo() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.raizQuadrada(-16);
        });
        assertEquals("Raiz quadrada de número negativo", exception.getMessage());
    }

    @Test
    public void testElevacaoAoQuadrado() {
        assertEquals(25, calculadora.elevarAoQuadrado(5));
    }
}

