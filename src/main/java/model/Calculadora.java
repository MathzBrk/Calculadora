package model;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField visor;
    private String operador;
    private double primeiroNumero, segundoNumero, resultado;

    public Calculadora() {
        // Configurações da janela principal
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Visor da calculadora
        visor = new JTextField();
        visor.setFont(new Font("Arial", Font.PLAIN, 24));
        visor.setEditable(false);
        add(visor, BorderLayout.NORTH);

        // Botões da calculadora
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 4, 10, 10));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+",
                "√", "x²"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.PLAIN, 18));
            botao.addActionListener(this);
            painelBotoes.add(botao);
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.charAt(0) >= '0' && comando.charAt(0) <= '9') {
                visor.setText(visor.getText() + comando); // Adiciona números ao visor
            } else if (comando.equals("C")) {
                visor.setText(""); // Limpa o visor
                primeiroNumero = segundoNumero = resultado = 0;
                operador = "";
            } else if (comando.equals("=")) {
                segundoNumero = Double.parseDouble(visor.getText());

                // Realiza a operação com base no operador escolhido
                switch (operador) {
                    case "+": resultado = primeiroNumero + segundoNumero; break;
                    case "-": resultado = primeiroNumero - segundoNumero; break;
                    case "*": resultado = primeiroNumero * segundoNumero; break;
                    case "/":
                        if (segundoNumero != 0) {
                            resultado = primeiroNumero / segundoNumero;
                        } else {
                            visor.setText("Erro");
                            return;
                        }
                        break;
                }

                visor.setText(String.valueOf(resultado));
            } else if (comando.equals("√")) {
                primeiroNumero = Double.parseDouble(visor.getText());
                resultado = Math.sqrt(primeiroNumero);
                visor.setText(String.valueOf(resultado)); // Exibe o resultado da raiz imediatamente
            } else if (comando.equals("x²")) {
                primeiroNumero = Double.parseDouble(visor.getText());
                resultado = Math.pow(primeiroNumero, 2);
                visor.setText(String.valueOf(resultado)); // Exibe o resultado da potência imediatamente
            } else { // Operações aritméticas
                operador = comando;
                primeiroNumero = Double.parseDouble(visor.getText());
                visor.setText(""); // Limpa o visor para o próximo número
            }
        } catch (Exception ex) {
            visor.setText("Erro");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calc = new Calculadora();
            calc.setVisible(true);
        });
    }
}
