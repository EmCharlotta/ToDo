package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
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
    private TextField singupSurname;

    @FXML
    private CheckBox signupCheckboxMale;

    @FXML
    private CheckBox signupCheckboxFemale;

    @FXML
    private Button loginSignupButton;

    @FXML
    void initialize() {

    }
}

