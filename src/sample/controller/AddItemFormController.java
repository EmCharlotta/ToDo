package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import sample.database.DatabaseHandler;
import sample.entity.Task;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {

    @Getter
    @Setter
    private int userId;

    private DatabaseHandler dbHandler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField taskName;

    @FXML
    private TextField taskDescription;

    @FXML
    private Button addTaskButton;
    @FXML
    private Label taskAddedLabel;

    @FXML
    private Button myTasks;

    @FXML
    void initialize() {

        dbHandler = new DatabaseHandler();
        addTaskButton.setOnAction(actionEvent ->
        {
            Task task = new Task();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            String name = taskName.getText().trim();
            String descr = taskDescription.getText().trim();

            if(!name.equals("") && !descr.equals("")){
                task.setTask(name);
                task.setDescription(descr);
                task.setDatecreated(timestamp);
                task.setUserId(AddItemController.userId);

                dbHandler.addTask(task);

                taskAddedLabel.setVisible(true);
                myTasks.setVisible(true);
                myTasks.setText("My Tasks: " + dbHandler.getAllTasks(AddItemController.userId));
                taskName.setText("");
                taskDescription.setText("");
                myTasks.setOnAction(actionEvent1 -> {

                });
            }
            else System.out.println("Поля не могут быть пустыми");

        });
    }
}
