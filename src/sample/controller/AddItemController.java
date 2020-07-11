package sample.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import sample.animation.Shaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {

    @Getter
    @Setter
    public static int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private URL location;

    @FXML
    private ImageView addButton;

    @FXML
    private Label tasksCompletedLabel;
    @FXML
    void initialize() {

        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("Clicked to Add task");
            Shaker shakeButton = new Shaker(addButton);
            shakeButton.shake();
            FadeTransition fadeButton = new FadeTransition(Duration.seconds(2), addButton);
            FadeTransition fadeLabel = new FadeTransition(Duration.seconds(2), tasksCompletedLabel);
            addButton.relocate(520,100);
            addButton.setOpacity(0);
            tasksCompletedLabel.relocate(420, 150);
            tasksCompletedLabel.setOpacity(0);

            fadeButton.setFromValue(1);
            fadeButton.setToValue(0);
            fadeButton.setAutoReverse(false);
            fadeButton.play();

            fadeLabel.setFromValue(1);
            fadeLabel.setToValue(0);
            fadeLabel.setAutoReverse(false);
            fadeLabel.play();

            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));

                AddItemController.userId = getUserId();

                FadeTransition transition = new FadeTransition(Duration.seconds(2), formPane);
                transition.setFromValue(0);
                transition.setToValue(1);
                transition.play();
                anchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

}
