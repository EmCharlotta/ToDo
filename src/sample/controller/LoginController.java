package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animation.Shaker;
import sample.database.DatabaseHandler;
import sample.entity.User;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

    private int userId;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginUserName;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginSignupButton;

    private DatabaseHandler dbHandler;
    @FXML
    void initialize() {

        loginSignupButton.setOnAction(event ->{
            loginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });


        loginButton.setOnAction(event-> {
            String loginName = loginUserName.getText().trim();
            String loginPwd = loginPassword.getText().trim();
            dbHandler = new DatabaseHandler();
            sample.entity.User user = new User();
            user.setLogin(loginName);
            user.setPassword(loginPwd);
            ResultSet userRow = dbHandler.userSignedUpCheck(user);
            int num = 0;
            try {
                while (userRow.next()) {
                    num++;
                    userId = userRow.getInt("userid");
                }
                if (num == 1) {
                    showAddItemScreen();
                }
                else {
                    Shaker shakerLogin = new Shaker(loginUserName);
                    shakerLogin.shake();
                    Shaker shakerPass = new Shaker(loginPassword);
                    shakerPass.shake();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
        private void showAddItemScreen(){
                loginSignupButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/additem.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                AddItemController itemController = loader.getController();
                itemController.setUserId(userId);
                stage.showAndWait();
        }
}

