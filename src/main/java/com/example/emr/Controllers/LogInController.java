package com.example.emr.Controllers;

import com.example.emr.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import javafx.event.ActionEvent;

public class LogInController {

    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public Label wrongLogIn;
    @FXML
    public Button buttonlogin;
    @FXML
    public Button buttonregister;



    public void userregister(ActionEvent event) throws IOException {
        ViewModel m = new ViewModel();
        m.changeToRegistration();
    }


    public void userlogin(ActionEvent event) throws IOException {
        String enteredUsername = username.getText().toString();
        String enteredPassword = password.getText().toString();
        ViewModel m = new ViewModel();
        if(isUsernameAndPassMatch(enteredUsername,enteredPassword)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Operation Successful");
            alert.setContentText("You have successfully log in!");
            alert.showAndWait();
            m.showDashboard(enteredUsername);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("username and password incorrect");
            alert.showAndWait();
        }
    }


    public boolean isUsernameAndPassMatch(String username, String password) throws IOException {
        FileReader fileReader = new FileReader("user_login_data.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String eachUserData = bufferedReader.readLine();
        while (eachUserData != null) {
            String[] splittedUserData = eachUserData.split(",");
            if (splittedUserData[0].equals(username)) {
                if(splittedUserData[2].equals(password)) {
                    return true;
                }
            }
            eachUserData = bufferedReader.readLine();
        }
        return false;
    }

}




















