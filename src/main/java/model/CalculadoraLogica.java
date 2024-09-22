package model;

public class CalculadoraLogica {

    public double calcular(double primeiroNumero, double segundoNumero, String operador) {
        switch (operador) {
            case "+":
                return primeiroNumero + segundoNumero;
            case "-":
                return primeiroNumero - segundoNumero;
            case "*":
                return primeiroNumero * segundoNumero;
            case "/":
                if (segundoNumero == 0) {
                    throw new ArithmeticException("Divisão por zero");
                }
                return primeiroNumero / segundoNumero;
            default:
                throw new IllegalArgumentException("Operador inválido");
        }
    }

    public double raizQuadrada(double numero) {
        if (numero < 0) {
            throw new ArithmeticException("Raiz quadrada de número negativo");
        }
        return Math.sqrt(numero);
    }

    public double elevarAoQuadrado(double numero) {
        return Math.pow(numero, 2);
    }
}

