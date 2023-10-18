
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {

    // Constantes para definir el ancho, alto y tamaño de cada "pixel"
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int PIXEL_SIZE = 35;
    private static Color lapiz = Color.BLACK;

    // Matriz para guardar una referencia a cada Rectangle (pixel)
    private Rectangle[][] rects = new Rectangle[WIDTH][HEIGHT];
    // Bandera para saber si el usuario está arrastrando el ratón
    private boolean dragging = false;

    // ColorPicker
    private static ColorPicker colorPicker = new ColorPicker();

    // Método principal de la aplicación donde se construye y muestra la interfaz
    @Override
    public void start(Stage primaryStage) {
        // Crea un FileChooser para abrir y guardar archivos
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dubujito en DibuJava", "*.dibu"));

        // Crea un GridPane que contendrá los Rectangles (pixels)
        GridPane grid = new GridPane();
        grid.setHgap(-1); // Espacio horizontal entre Rectangles
        grid.setVgap(-1); // Espacio vertical entre Rectangles

        // Doble bucle para crear y añadir cada Rectangle al GridPane
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Rectangle rect = new Rectangle(PIXEL_SIZE, PIXEL_SIZE, Color.WHITE);
                // Comenta la línea que añade un borde gris a cada Rectangle
                // rect.setStroke(Color.GRAY);
                // Guarda el Rectangle en la matriz
                rects[x][y] = rect;
                // Añade el Rectangle al GridPane en la posición correcta
                grid.add(rect, x, y);
            }
        }

        // Titulo
        Label lbTitulo = new Label("DibuJava");
        lbTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        StackPane titulo = new StackPane(lbTitulo);
        StackPane.setMargin(lbTitulo, new Insets(-10, 0, 10, 0));

        // Menu bajo

        // ColorPicker
        colorPicker.setValue(Color.BLACK); // Color inicial del ColorPicker

        // boton de borrar
        Button btnBorrar = new Button("Borrar Pantalla", new ImageView(new Image("img/borrarPantalla.png")));

        // Boton del borrador
        Button btnBorrador = new Button("Borrador", new ImageView(new Image("img/borrador.png")));

        // Boton del lapiz
        Button btnLapiz = new Button("Lapiz", new ImageView(new Image("img/lapiz.png")));

        // Boton de cargar
        Button btnCargar = new Button("Cargar Imagen", new ImageView(new Image("img/importar.png")));

        // Boton de guardar
        Button btnGuardar = new Button("Guardar Imagen", new ImageView(new Image("img/guardar.png")));

        HBox menuBajo = new HBox(20, colorPicker, btnLapiz, btnBorrador, btnBorrar); // Crear el Menu Bajo
        menuBajo.setAlignment(Pos.CENTER);
        HBox menuBox2 = new HBox(20, btnCargar, btnGuardar);
        menuBox2.setAlignment(Pos.CENTER);

        // Formacion de los componentes
        grid.setAlignment(Pos.CENTER);
        VBox leayer = new VBox(10, titulo, grid, menuBajo, menuBox2); // 5 es el espaciado entre los elementos del VBox

        // -------------------------Eventos-----------------------------------

        // Detecta cuando el ratón es presionado
        grid.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            dragging = true; // Activa la bandera de arrastre
            colorPixel(event); // Llama al método para colorear el pixel
        });

        // Detecta cuando el ratón es arrastrado
        grid.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            // Solo colorea si la bandera de arrastre está activa
            if (dragging) {
                colorPixel(event);
            }
        });

        // boton de borrar
        btnBorrar.setOnAction(e -> {
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    rects[x][y].setFill(Color.WHITE); // Usamos el color seleccionado en el ColorPicker
                }
            }
        });

        // boton de borrador
        btnBorrador.setOnAction(e -> {
            lapiz = colorPicker.getValue();
            colorPicker.setValue(Color.WHITE);
        });

        // Boton del lapiz
        btnLapiz.setOnAction(e -> {
            colorPicker.setValue(lapiz);
        });

        // Boton de guardar en mi propio formatio .dibu
        btnGuardar.setOnAction(e -> {
            String archivo = "";

            String dibujo[][] = new String[WIDTH][HEIGHT];
            Set<String> colores = new HashSet<>();
            // Revisa los colores y los guarda de manera que no se repitan
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    Color color = (Color) rects[i][j].getFill();
                    colores.add(String.format("#%02X%02X%02X",
                            (int) (color.getRed() * 255),
                            (int) (color.getGreen() * 255),
                            (int) (color.getBlue() * 255)));
                }
            }
            List<String> coloresList = new ArrayList<>(colores);
            // Guarda los colores en un array
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    String tempColor = String.format("#%02X%02X%02X",
                            (int) (((Color) rects[i][j].getFill()).getRed() * 255),
                            (int) (((Color) rects[i][j].getFill()).getGreen() * 255),
                            (int) (((Color) rects[i][j].getFill()).getBlue() * 255));
                    byte lugar = (byte) coloresList.indexOf(tempColor);
                    if (lugar != -1) {
                        String numerin = Integer.toBinaryString(lugar); // Convierte el número a binario y lo guarda en
                                                                        // un String manteniendo los 0 a la izquierda
                        while (numerin.length() < 8) {
                            numerin = "0" + numerin;
                        }
                        dibujo[i][j] = numerin; // Guarda el numero en el array
                    } else
                        mostrarError("Error al guardar");

                }
            }

            // ----Guardar en el archivo----
            // Guardar colores
            archivo += coloresList.size() + " ";// Numero de colores
            for (String string : coloresList) {
                archivo += string + " ";
            }

            // Tamaño del hexadecimal
            // Guardar dibujo en hexadecimal por cada fila
            String binario = "";
            StringBuilder hexadecimal = new StringBuilder();
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    binario += dibujo[i][j];
                }
                for (int k = binario.length(); k > 0; k -= 4) {
                    String cuarteto = binario.substring(k - 4, k);
                    int valorDecimal = Integer.parseInt(cuarteto, 2);
                    hexadecimal.insert(0, Integer.toHexString(valorDecimal).toUpperCase());// Tamaño del hexadecimal =
                                                                                           // 32
                }
                archivo += hexadecimal.toString() + " ";
                binario = "";
                hexadecimal = new StringBuilder();

            }
            // for (int i = 0; i < dibujo.length; i++) {
            // for (int j = 0; j < dibujo[0].length; j++) {
            // System.out.print(Integer.parseInt(dibujo[i][j],2)+", ");
            // }
            // System.out.println();
            // }
            /*
             * 1. Guardar el numero de colores
             * 2. Guardar los colores
             * 3. Guardar el dibujo en hexadecimal
             * 
             */
            // Guardar el archivo
            File file = fileChooser.showSaveDialog(primaryStage); // Asume que primaryStage es tu Stage principal
            if (file != null) {
                saveToFile(file, archivo);
            }
            // System.out.println(archivo);
        });

        // Boton de cargar
        btnCargar.setOnAction(e -> {
            byte[][] dibujoBinario = new byte[WIDTH][HEIGHT];
            String archivo = "";
            // Cargar archivo
            File file = fileChooser.showOpenDialog(primaryStage); // Asume que primaryStage es tu Stage principal
            if (file == null) {
                return;
            }
            archivo = abrirYLeerArchivo(file);
            // System.out.println(archivo);
            String[] datos = archivo.split(" ");

            // Sacar los colores
            int numeroColores = Integer.parseInt(datos[0]);
            String[] colores = new String[numeroColores];
            for (int i = 0; i < numeroColores; i++) {
                colores[i] = datos[i + 1];
            }
            // Sacar los hexadecimales que valen por cada fila
            String[] hexadecimal = new String[datos.length - numeroColores - 1];
            String binario = new String();
            for (int i = 0; i < hexadecimal.length; i++) {
                hexadecimal[i] = datos[i + numeroColores + 1];

                // Convertir los hexadecimales a binario
                for (char digitoHex : hexadecimal[i].toCharArray()) {
                    // Convertir el dígito hexadecimal a entero
                    int valorDecimal = Integer.parseInt(Character.toString(digitoHex), 16);

                    // Convertir el entero a una cadena binaria de 4 bits
                    String representacionBinaria = String.format("%4s", Integer.toBinaryString(valorDecimal)).replace(
                            ' ',
                            '0');

                    binario += representacionBinaria;
                }
                // Guardar el binario en el array
                int cont = 0;
                // System.out.println(binario);
                for (int j = 0; j < WIDTH; j++) {
                    dibujoBinario[i][j] = (byte) (Integer.parseInt(binario.substring(cont, cont + 8), 2));
                    // System.out.print(binario.substring(cont, cont+8)+", ");
                    cont += 8;
                }
                // System.out.println();
                binario = "";
            }

            // for (int i = 0; i < dibujoBinario.length; i++) {
            // for (int j = 0; j < dibujoBinario.length; j++) {
            // System.out.print(dibujoBinario[i][j]+", ");
            // }
            // System.out.println();
            // }

            // Pintar el dibujo
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    pintarCeldas(i, j, colores[dibujoBinario[i][j]]);
                }
            }

        });

        // Detecta cuando el ratón es liberado
        grid.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> dragging = false);

        // -----------------Creacion de la Ventana----------------------------
        // Establece el ícono
        Image appIcon = new Image(getClass().getResourceAsStream("img/icono32x32.png"));
        primaryStage.getIcons().add(appIcon);
        // Crear un margen
        StackPane root = new StackPane(leayer);
        StackPane.setMargin(leayer, new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.SALMON, null, null)));

        // Crea una escena y la añade al Stage (ventana principal)
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DibuJava"); // Título de la ventana

        // Tamaño minimo por defecto
        primaryStage.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                primaryStage.setMinWidth(primaryStage.getWidth());
                primaryStage.setMinHeight(primaryStage.getHeight());
            }
        });

        primaryStage.show(); // Muestra la ventana
    }

    // Método para colorear el pixel basado en la posición del ratón
    private void colorPixel(MouseEvent event) {
        // Calcula la posición x, y del pixel basado en las coordenadas del ratón
        int x = (int) event.getX() / PIXEL_SIZE;
        int y = (int) event.getY() / PIXEL_SIZE;
        // Si la posición es válida, colorea el pixel de negro
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            rects[x][y].setFill(colorPicker.getValue()); // Usamos el color seleccionado en el ColorPicker

            // System.out.println("Pixel pintado: ["+x+", "+y+"]");
            // System.out.println("Con el color: "+rects[x][y].getFill());
        }
    }

    // Guardar un archivo
    private void saveToFile(File file, String texto) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(texto); // Aquí escribe los datos que desees guardar
            writer.close();
        } catch (IOException ex) {
            mostrarError("Error al guardar el archivo: " + ex.getMessage());
        }

    }

    private void pintarCeldas(int i, int j, String color) {
        rects[i][j].setFill(Color.web(color));
    }

    // Abrir y leer Archivo
    private String abrirYLeerArchivo(File file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = "";
            String texto = "";
            while ((line = bufferedReader.readLine()) != null) {
                texto += line; // Aquí procesa los datos que lees del archivo
            }
            bufferedReader.close();
            return texto;
        } catch (IOException ex) {
            mostrarError("Error al leer el archivo: " + ex.getMessage());
        }
        return "";

    }

    private void mostrarError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // No queremos un encabezado
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
