package space1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by lzx on 2017/3/21.
 */
public class Frame extends Application implements Runnable {

    static Scene scene;
    static Scene scene2;
    static Stage stage;
    static Button backBtn;
    static Thread thread;
    static Canvas canvas;
    static GraphicsContext gc;

    int x = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pstage) {
        StackPane stackPane = new StackPane();

        scene = new Scene(stackPane,1025,561);
        scene2 = new Scene(new Group(), 1025, 561);

        stage = new Stage();
        Stage finalStage = stage;
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                thread.stop();
            }
        });

        Button screenBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream("..\\back.jpg"))));
        screenBtn.setOnAction((ActionEvent e) -> {
            finalStage.setScene(scene2);
        });
        screenBtn.setDefaultButton(true);

        Label startTitle = new Label("Click any where to start");
        startTitle.setScaleX(1.5);
        startTitle.setScaleY(1.5);
        startTitle.setTranslateY(200);
        startTitle.setTextFill(Color.WHITE);

        ((StackPane) scene.getRoot()).getChildren().add(screenBtn);
        ((StackPane) scene.getRoot()).getChildren().add(startTitle);


        Button backBtn = new Button("Print Hello");
        backBtn.setTranslateX(100);
        backBtn.setDefaultButton(true);
        backBtn.setOnAction((ActionEvent e) -> {
            finalStage.setScene(scene);
        });

        canvas = new Canvas(300, 250);
        canvas.setTranslateX(200);
        canvas.setTranslateY(200);
        gc = canvas.getGraphicsContext2D();
        drawShapes(gc);

        ((Group) scene2.getRoot()).getChildren().add(backBtn);
        ((Group) scene2.getRoot()).getChildren().add(canvas);

        stage.show();
        thread = new Thread(this);
        thread.start();
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setLineWidth(5);
        gc.setFill(Color.BLUE);
        gc.clearRect(x - 5, 5, x + 40, 50);
        gc.fillOval(x, 10, 40, 40);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x++;
            System.out.println(x);
            drawShapes(gc);
        }
    }
}
