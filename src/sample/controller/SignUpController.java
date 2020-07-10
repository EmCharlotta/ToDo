package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.database.DatabaseHandler;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signupLogin;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signupSurname;

    @FXML
    private CheckBox signupCheckboxMale;

    @FXML
    private CheckBox signupCheckboxFemale;

    @FXML
    private Button loginSignupButton;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
    loginSignupButton.setOnAction(event -> {
        try {
            dbHandler.signUpUser(signUpFirstName.getText(), signupSurname.getText(), signupLogin.getText(),
                    signupPassword.getText(),"female");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });
    }
}

