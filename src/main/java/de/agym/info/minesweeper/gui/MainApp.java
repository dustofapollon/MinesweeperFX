package de.agym.info.minesweeper.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());


        stage.setTitle("MineSweeper");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

   /* private Button startGame;

    public EventHandler bigEventhandler() {
        EventHandler<MouseEvent> eventHandler= new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                e.;
            }

        };
        return eventHandler;
    }
    private void initButton() {
        startGame.setText("Start Game");


         }*/




