package scenes;

import Main.MainStage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Created by lzx on 2017/4/5.
 */
public class Start extends Scene {

    public static Label startTitle;
    public static double opacity = 1;
    public static double speedOpacity = 0.02;

    public Start(Parent root, double width, double height) {
        super(root, width, height);

        Image image = new Image(getClass().getResourceAsStream("..\\pictures\\back.jpg"));
        ImageView imageView = new ImageView(image);
        Button screenBtn = new Button("", imageView);
        screenBtn.setOnAction((ActionEvent e) -> {
            MainStage.stage.setScene(MainStage.game);
        });
        screenBtn.setDefaultButton(true);

        startTitle = new Label("Click any where to start");
        startTitle.setScaleX(1.5);
        startTitle.setScaleY(1.5);
        startTitle.setTranslateY(230);
        startTitle.setTextFill(Color.gray(1));
        startTitle.setOnMouseClicked(me -> MainStage.stage.setScene(MainStage.game));

        MainStage.stackPane.getChildren().add(screenBtn);
        MainStage.stackPane.getChildren().add(startTitle);

    }
}
