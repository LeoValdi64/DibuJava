import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;

public class App extends Application {

    // Constantes para definir el ancho, alto y tamaño de cada "pixel"
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int PIXEL_SIZE = 35;

    // Matriz para guardar una referencia a cada Rectangle (pixel)
    private Rectangle[][] rects = new Rectangle[WIDTH][HEIGHT];
    // Bandera para saber si el usuario está arrastrando el ratón
    private boolean dragging = false;

    // ColorPicker
    private static ColorPicker colorPicker = new ColorPicker();

    // Método principal de la aplicación donde se construye y muestra la interfaz
    @Override
    public void start(Stage primaryStage) {

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
        
        //Titulo
        Label lbTitulo = new Label("DibuJava");
        lbTitulo.setFont(Font.font("Arial",FontWeight.BOLD, 30));
        StackPane titulo = new StackPane(lbTitulo);
        StackPane.setMargin(lbTitulo, new Insets(10));

        //Menu bajo

        // ColorPicker
        colorPicker.setValue(Color.BLACK); // Color inicial del ColorPicker

        //boton de borrar
        Button btnBorrar = new Button("Borrar");
        


        HBox menuBajo = new HBox(20,colorPicker,btnBorrar); // Crear el Menu Bajo
        
        // Formacion de los componentes
        VBox leayer = new VBox(5,titulo,grid, menuBajo); // 5 es el espaciado entre los elementos del VBox


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

        //boton de borrar
        btnBorrar.setOnAction(e -> {
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {                    
                    rects[x][y].setFill(Color.WHITE); // Usamos el color seleccionado en el ColorPicker
                }
            }
        });


        // Detecta cuando el ratón es liberado
        grid.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> dragging = false);

        // -----------------Creacion de la Ventana----------------------------
        // Crear un margen
        StackPane root = new StackPane(leayer);
        StackPane.setMargin(leayer, new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.SALMON, null, null)));

        // Crea una escena y la añade al Stage (ventana principal)
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DibuJava"); // Título de la ventana
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
    
    

    public static void main(String[] args) {
        launch(args);
    }
}
