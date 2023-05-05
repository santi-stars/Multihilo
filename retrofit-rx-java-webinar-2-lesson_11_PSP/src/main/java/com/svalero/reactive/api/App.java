package com.svalero.reactive.api;

import com.svalero.reactive.api.controller.AppController;
import com.svalero.reactive.api.util.R;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class App extends Application{

        @Override
        public void start(Stage primaryStage) throws Exception {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("main-app.fxml"));
            loader.setController(new AppController());
            ScrollPane mainPane = loader.load();
            Scene scene = new Scene(mainPane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Numbers Application");
            primaryStage.show();
        }

        @Override
        public void stop() throws Exception{
            super.stop();
        }

        public static void main(String[] args) {
            launch();
        }
    }
    
    