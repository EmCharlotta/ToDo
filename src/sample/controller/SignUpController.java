package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.database.DatabaseHandler;
import sample.entity.User;

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

    loginSignupButton.setOnAction(event -> {
        createUser();
    });
    }

    private void createUser(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        String name = signUpFirstName.getText();
        String surname = signupSurname.getText();
        String login = signupLogin.getText();
        String password = signupPassword.getText();
        String gender;
        if(signupCheckboxMale.isSelected()){
            gender = "Male";
        }
        else gender = "Female";
        User user = new User(name, surname, login, password, gender);

        try {
            dbHandler.signUpUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

