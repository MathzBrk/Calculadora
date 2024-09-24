package model;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Application {

    private TextField visor;
    private String operador;
    private double resultado;
    private boolean novoNumero;
    private boolean operadorPressionado;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora");

        // Configurando o visor da calculadora
        visor = new TextField();
        visor.setEditable(false);
        visor.setStyle("-fx-font-size: 24; -fx-background-color: #222; -fx-text-fill: white;");

        // Botões
        GridPane painelBotoes = new GridPane();
        painelBotoes.setPadding(new Insets(10));
        painelBotoes.setVgap(10);
        painelBotoes.setHgap(10);
        painelBotoes.setAlignment(Pos.CENTER);

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+",
                "√", "x²", "%"
        };

        int row = 0;
        int col = 0;
        for (String texto : botoes) {
            Button botao = new Button(texto);
            botao.setStyle("-fx-font-size: 18; -fx-background-color: #222; -fx-text-fill: white;");
            botao.setPrefSize(80, 80);
            botao.setOnAction(e -> handleButtonAction(texto));
            painelBotoes.add(botao, col, row);
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        // Limpando o buffer ao iniciar a calculadora
        limparBuffer();

        // VBox para organizar os botões
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(visor, painelBotoes);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para limpar o buffer
    private void limparBuffer() {
        visor.setText(""); // Limpa o visor
        operador = ""; // Zera o operador
        resultado = 0; // Reseta o resultado
        novoNumero = true; // Marca que um novo número deve ser inserido
        operadorPressionado = false; // Marca que nenhum operador foi pressionado
    }

    private void handleButtonAction(String texto) {
        try {
            if (texto.charAt(0) >= '0' && texto.charAt(0) <= '9') {
                if (novoNumero) {
                    visor.setText(""); // Limpa o visor se for um novo número
                    novoNumero = false;
                }
                visor.setText(visor.getText() + texto); // Adiciona o número ao visor
                operadorPressionado = false;
            } else if (texto.equals("C")) {
                limparBuffer(); // Chama o método para limpar o buffer
            } else if (texto.equals("=")) {
                if (!operadorPressionado && !operador.isEmpty()) {
                    calcular(Double.parseDouble(visor.getText())); // Realiza a última operação
                    visor.setText(String.valueOf(resultado));
                    operador = "";
                    novoNumero = true;
                }
            } else if (texto.equals("√")) {
                resultado = Math.sqrt(Double.parseDouble(visor.getText()));
                visor.setText(String.valueOf(resultado));
                novoNumero = true;
            } else if (texto.equals("x²")) {
                resultado = Math.pow(Double.parseDouble(visor.getText()), 2);
                visor.setText(String.valueOf(resultado));
                novoNumero = true;
            } else if (texto.equals("+") || texto.equals("-") || texto.equals("*") || texto.equals("/") || texto.equals("%")) {
                if (!operadorPressionado) {
                    if (!operador.isEmpty()) {
                        calcular(Double.parseDouble(visor.getText())); // Realiza a operação acumulada
                    } else {
                        resultado = Double.parseDouble(visor.getText()); // Armazena o primeiro número
                    }
                    operador = texto;
                    novoNumero = true;
                    operadorPressionado = true;
                }
            }
        } catch (Exception ex) {
            visor.setText("Erro");
            limparBuffer(); // Limpa o buffer em caso de erro
        }
    }

    // Método de cálculo
    private void calcular(double numero) {
        switch (operador) {
            case "+":
                resultado += numero;
                break;
            case "-":
                resultado -= numero;
                break;
            case "*":
                resultado *= numero;
                break;
            case "/":
                if (numero != 0) {
                    resultado /= numero;
                } else {
                    visor.setText("Erro");
                    limparBuffer();
                }
                break;
            case "%":
                resultado = resultado * (numero / 100); // Cálculo de porcentagem
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
