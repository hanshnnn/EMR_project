package com.example.emr.Controllers;

import com.example.emr.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.*;

public class RegistrationAccountController {

    @FXML
    public ImageView gobacklogin;
    @FXML
    public TextField registerUsername;
    @FXML
    public TextField registerPassword;
    @FXML
    public TextField registerIc;
    @FXML
    public TextField registerConfirmPassw;
    @FXML
    public Button saveRegister;



    public void patientbacklogin(MouseEvent event) throws Exception {
        ViewModel m = new ViewModel();
        m.showLogin();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Operation Successful");
        alert.setContentText("You have successfully log out!");

        // Display the Alert pop-up
        alert.showAndWait();
        m.showLogin();
    }


    public boolean isUsernameExist(String username) throws IOException {
        FileReader fileReader = new FileReader("user_login_data.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String eachUserData = bufferedReader.readLine();
        while (eachUserData != null) {
            String[] splittedUserData = eachUserData.split(",");
            if (splittedUserData[0].equals(username)) {
                return true;
            }
            eachUserData = bufferedReader.readLine();
        }
        return false;
    }


    public boolean isIcExist (String Ic) throws IOException {
        FileReader fileReader = new FileReader("user_login_data.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String eachUserData = bufferedReader.readLine();
        while (eachUserData != null) {
            String[] splittedUserData = eachUserData.split(",");
            if (splittedUserData[1].equals(Ic)) {
                return true;
            }
            eachUserData = bufferedReader.readLine();
        }
        return false;
    }


    public void addData(ActionEvent event ) throws IOException {

        ViewModel m = new ViewModel();
        if(registerUsername.getText().toString().isEmpty() || registerPassword.getText().toString().isEmpty() || registerIc.getText().toString().isEmpty() || registerConfirmPassw.getText().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("Please fill all data!");
            alert.showAndWait();
            return;
        }
        if(!registerPassword.getText().toString().equals(registerConfirmPassw.getText().toString())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("Your password and confirmation pass not same");
            alert.showAndWait();
            return;
        }
        if(registerUsername.getText().toString().length() < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("Your username must be bigger than 3 characters!");
            alert.showAndWait();
            return;
        }
        if(registerPassword.getText().toString().length() < 8 || registerConfirmPassw.getText().toString().length() < 8 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("Your password must be bigger than 8 characters!");
            alert.showAndWait();
            return;
        }
        if(registerIc.getText().toString().length() != 12 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("Your ic must be 12 characters!");
            alert.showAndWait();
            return;
        }
        if(isUsernameExist(registerUsername.getText().toString())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("Username exist. Please use another username");
            alert.showAndWait();
            return;
        }
        if(isIcExist(registerIc.getText().toString())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("IC exist. Please use another IC");
            alert.showAndWait();
            return;
        }


        String filename ="RegistrationData.txt";
        String content = registerUsername.getText().toString() + "," + registerIc.getText().toString() + "," + registerPassword.getText().toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) {
            writer.newLine();
            writer.write(content);
            writer.flush();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Operation Successful");
            alert.setContentText("You have successfully registered!");
            alert.showAndWait();
            m.showLogin();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }




}
