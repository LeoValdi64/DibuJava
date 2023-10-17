import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

    // Método principal de la aplicación donde se construye y muestra la interfaz
    @Override
    public void start(Stage primaryStage) {
        // Crea un GridPane que contendrá los Rectangles (pixels)
        GridPane grid = new GridPane();

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

        // Detecta cuando el ratón es liberado
        grid.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> dragging = false);

        // Crea una escena y la añade al Stage (ventana principal)
        Scene scene = new Scene(grid);
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
            rects[x][y].setFill(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
