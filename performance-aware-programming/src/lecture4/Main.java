import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Magic");

        // Get screen dimensions and create a canvas to draw points on
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        final double screenWidth = primaryScreenBounds.getMaxX();
        final double screenHeight = primaryScreenBounds.getMaxY();
        Canvas canvas = new Canvas(screenWidth,  screenHeight);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // Add canvas to stage
        Pane root = new Pane();
        StackPane holder = new StackPane();
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Note: it's important to start the pi estimator and display updater
        // on separate threads to avoid affecting rendering performance
        PiEstimator pe = new PiEstimator(100000);
        Thread peThread = new Thread(pe);
        new Thread(new DisplayUpdater(screenWidth, screenHeight, gc, pe)).start();
        peThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class DisplayUpdater implements Runnable {
        private final double screenWidth;
        private final double screenHeight;
        private final GraphicsContext gc;
        private PiEstimator piEstimator;

        public DisplayUpdater(double screenWidth, double screenHeight,
                              GraphicsContext gc,
                              PiEstimator piEstimator) {
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
            this.gc = gc;
            this.piEstimator = piEstimator;
        }

        @Override
        public void run() {
            while (true) {
                // Query the PI estimator for some new points
                List<Point> points = piEstimator.getNewPoints();
                System.out.println(points.size());
                // Render the points
                for (Point p : points ) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    double dim = Math.min(screenHeight, screenWidth);
                    final double screenX = p.x * dim;
                    final double screenY = p.y * dim;
                    final boolean inside = p.inside;
                    Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (inside) {
                                    gc.setStroke(Color.RED);
                                } else {
                                    gc.setStroke(Color.BLUE);
                                }
                                // convert point coordinates from (0, 1) to screen coordinates
                                double offsetX = Math.abs(screenHeight - screenWidth) / 2;
                                gc.strokeRect(screenX + offsetX, screenY, 1, 1);
                            }
                        });
                }
            }
        }
    }
}
