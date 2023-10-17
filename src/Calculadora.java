import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Calculadora extends Application {
    public void addPrefDimensionButton(Button btn, int width, int height) {
        btn.setPrefWidth(width);
        btn.setPrefHeight(height);
    }

    public void addMaxDimensionButton(Button btn) {
        btn.setMaxHeight(Double.MAX_VALUE);
        btn.setMaxWidth(Double.MAX_VALUE);
    }


    @Override
    public void start(Stage ventana) {
        // Botones nuemericos
        Button btn0 = new Button("0");
        Button btn1 = new Button("1");
        Button btn2 = new Button("2");
        Button btn3 = new Button("3");
        Button btn4 = new Button("4");
        Button btn5 = new Button("5");
        Button btn6 = new Button("6");
        Button btn7 = new Button("7");
        Button btn8 = new Button("8");
        Button btn9 = new Button("9");

        // Botones de operaciones
        Button btnSuma = new Button("+");
        Button btnResta = new Button("-");
        Button btnMultiplicacion = new Button("X");
        Button btnDivicion = new Button("/");

        Button btnIgual = new Button("=");
        Button btnBorrar = new Button("C");

        // Campo de operaciones
        TextField textOperaciones = new TextField();

        // Fila 0
        // El elemetno se va a ubicar en la fila 0, culumna 0, y va ocupar 4 columnas y
        // 1 fila
        GridPane.setConstraints(textOperaciones, 0, 0, 4, 1);

        // Fila 1
        GridPane.setConstraints(btn7, 0, 1);
        GridPane.setConstraints(btn8, 1, 1);
        GridPane.setConstraints(btn9, 2, 1);
        GridPane.setConstraints(btnSuma, 3, 1);

        // Fila 2
        GridPane.setConstraints(btn4, 0, 2);
        GridPane.setConstraints(btn5, 1, 2);
        GridPane.setConstraints(btn6, 2, 2);
        GridPane.setConstraints(btnResta, 3, 2);

        // Fila 3
        GridPane.setConstraints(btn1, 0, 3);
        GridPane.setConstraints(btn2, 1, 3);
        GridPane.setConstraints(btn3, 2, 3);
        GridPane.setConstraints(btnMultiplicacion, 3, 3);

        // Fila 4
        GridPane.setConstraints(btnBorrar, 0, 4);
        GridPane.setConstraints(btn0, 1, 4);
        GridPane.setConstraints(btnIgual, 2, 4);
        GridPane.setConstraints(btnDivicion, 3, 4);

        // Dimension Predefinida y Maxima Nodos Hijos

        textOperaciones.setMaxWidth(Double.MAX_VALUE);
        textOperaciones.setMaxHeight(Double.MAX_VALUE);
        textOperaciones.setPrefHeight(35);

        addPrefDimensionButton(btn0, 55, 35);
        addPrefDimensionButton(btn1, 55, 35);
        addPrefDimensionButton(btn2, 55, 35);
        addPrefDimensionButton(btn3, 55, 35);
        addPrefDimensionButton(btn4, 55, 35);
        addPrefDimensionButton(btn5, 55, 35);
        addPrefDimensionButton(btn6, 55, 35);
        addPrefDimensionButton(btn7, 55, 35);
        addPrefDimensionButton(btn8, 55, 35);
        addPrefDimensionButton(btn9, 55, 35);
        addPrefDimensionButton(btnSuma, 55, 35);
        addPrefDimensionButton(btnResta, 55, 35);
        addPrefDimensionButton(btnMultiplicacion, 55, 35);
        addPrefDimensionButton(btnDivicion, 55, 35);
        addPrefDimensionButton(btnIgual, 55, 35);
        addPrefDimensionButton(btnBorrar, 55, 35);

        // Hacer que se ajuste al tamaño de la ventana
        GridPane.setVgrow(textOperaciones, Priority.ALWAYS);
        GridPane.setVgrow(btn7, Priority.ALWAYS);
        GridPane.setVgrow(btn4, Priority.ALWAYS);
        GridPane.setVgrow(btn1, Priority.ALWAYS);
        GridPane.setVgrow(btnBorrar, Priority.ALWAYS);

        GridPane.setHgrow(btn7, Priority.ALWAYS);
        GridPane.setHgrow(btn8, Priority.ALWAYS);
        GridPane.setHgrow(btn9, Priority.ALWAYS);
        GridPane.setHgrow(btnSuma, Priority.ALWAYS);

        addMaxDimensionButton(btn0);
        addMaxDimensionButton(btn1);
        addMaxDimensionButton(btn2);
        addMaxDimensionButton(btn3);
        addMaxDimensionButton(btn4);
        addMaxDimensionButton(btn5);
        addMaxDimensionButton(btn6);
        addMaxDimensionButton(btn7);
        addMaxDimensionButton(btn8);
        addMaxDimensionButton(btn9);
        addMaxDimensionButton(btnSuma);
        addMaxDimensionButton(btnResta);
        addMaxDimensionButton(btnMultiplicacion);
        addMaxDimensionButton(btnDivicion);
        addMaxDimensionButton(btnIgual);
        addMaxDimensionButton(btnBorrar);


        textOperaciones.setAlignment(Pos.CENTER_RIGHT);
        textOperaciones.setEditable(false);

        btn0.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "0");
        });
        btn1.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "1");
        });
        btn2.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "2");
        });
        btn3.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "3");
        });
        btn4.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "4");
        });
        btn5.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "5");
        });
        btn6.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "6");
        });
        btn7.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "7");
        });
        btn8.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "8");
        });
        btn9.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "9");
        });
        btnSuma.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "+");
        });
        btnResta.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "-");
        });
        btnMultiplicacion.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "*");
        });
        btnDivicion.setOnAction(e -> {
            textOperaciones.setText(textOperaciones.getText() + "/");
        });
        
        btnIgual.setOnAction(e -> {
            String operacion = textOperaciones.getText();
            String[] numeros = operacion.split("[+-/*]");
            String[] operadores = operacion.split("[0-9]+");
            double resultado = Double.parseDouble(numeros[0]);
            for (int i = 1; i < numeros.length; i++) {
                switch (operadores[i]) {
                    case "+":
                        resultado += Double.parseDouble(numeros[i]);
                        break;
                    case "-":
                        resultado -= Double.parseDouble(numeros[i]);
                        break;
                    case "*":
                        resultado *= Double.parseDouble(numeros[i]);
                        break;
                    case "/":
                        resultado /= Double.parseDouble(numeros[i]);
                        break;
                }
            }
            textOperaciones.setText(String.valueOf(resultado));
        });
        btnBorrar.setOnAction(e -> {
            textOperaciones.setText("");
        });











        // Crear el Layout con GridPane
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER); // Posicionar el layout al centro de la ventana
        // Espacios entre celdas
        layout.setHgap(5);
        layout.setVgap(5);

        // Agregar los elementos al layout
        layout.getChildren().addAll(textOperaciones, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
                btnSuma, btnResta, btnMultiplicacion, btnDivicion, btnIgual, btnBorrar);

        // Crear un margen
        StackPane.setMargin(layout, new Insets(10));
        StackPane root = new StackPane(layout);

        // Crear Ventana y mostrarla
        Scene scene = new Scene(root, 300, 250);
        ventana.setTitle("Calculadora");
        ventana.setScene(scene);
        ventana.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
