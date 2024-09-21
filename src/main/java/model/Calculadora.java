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
    private double primeiroNumero, segundoNumero, resultado;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora");

        // configurando o visor da calculadora
        visor = new TextField();
        visor.setEditable(false);
        visor.setStyle("-fx-font-size: 24; -fx-background-color: #222; -fx-text-fill: white;");

        // botoes
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
                "√", "x²"
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

        // vbox para organizar os botoes na vertical e horizontal
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(visor, painelBotoes);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonAction(String texto) {
        try {
            if (texto.charAt(0) >= '0' && texto.charAt(0) <= '9') {
                visor.setText(visor.getText() + texto); // adiciona os numeros digitados no visor
            } else if (texto.equals("C")) {
                visor.setText(""); // limpa o visor
                primeiroNumero = segundoNumero = resultado = 0;
                operador = "";
            } else if (texto.equals("=")) {
                segundoNumero = Double.parseDouble(visor.getText());
                switch (operador) {
                    case "+":
                        resultado = primeiroNumero + segundoNumero; break;
                    case "-":
                        resultado = primeiroNumero - segundoNumero; break;
                    case "*":
                        resultado = primeiroNumero * segundoNumero; break;
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
            } else if (texto.equals("√")) {
                primeiroNumero = Double.parseDouble(visor.getText());
                resultado = Math.sqrt(primeiroNumero);
                visor.setText(String.valueOf(resultado));
            } else if (texto.equals("x²")) {
                primeiroNumero = Double.parseDouble(visor.getText());
                resultado = Math.pow(primeiroNumero, 2);
                visor.setText(String.valueOf(resultado));
            } else { // operaçoes
                operador = texto;
                primeiroNumero = Double.parseDouble(visor.getText());
                visor.setText(""); // limpa o visor para o exibir o proximo numero
            }
        } catch (Exception ex) {
            visor.setText("Erro");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
