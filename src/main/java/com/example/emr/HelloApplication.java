package com.example.emr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
      ViewModel viewModel = new ViewModel();
        viewModel.showLogin();
    }

    public static void main(String[] args) {
        launch();
    }
}