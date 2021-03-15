package de.agym.info.minesweeper.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MineSweeper");
        startScreen(stage);

    }

    private void startScreen(Stage stage) {
        Button button = new Button("Start Game");

        TextField height = new TextField("10");
        TextField width = new TextField("10");
        TextField bombCount = new TextField("20");

        VBox formBox = new VBox(10.0,
                new HBox(new Label("Height"), height),
                new HBox(new Label("Width"), width),
                new HBox(new Label("Bomb Count"), bombCount),
                button);

        Scene scene = new Scene(formBox, 400, 400);

        button.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            startGame(stage,
                    Integer.parseInt(height.getText()),
                    Integer.parseInt(width.getText()),
                    Integer.parseInt(bombCount.getText())
            );
        });
        stage.setScene(scene);
        stage.show();
    }

    private void startGame(Stage stage, int height, int width, int bombs) {
        backend_bone backend = new backend_bone(height, width, bombs);

        FXMLLoader loader = new FXMLLoader();

        FXMLController controller = new FXMLController(backend);
        loader.setController(controller);
        loader.setLocation(getClass().getResource("scene.fxml"));
        backend.onLoose(() -> youLoose(stage));
        backend.onWin(() -> youWin(stage));


        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene gameScene = new Scene(root);
        gameScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());


        stage.setScene(gameScene);
        stage.setFullScreen(true);

    }

    private void youWin(Stage stage){
        Button button = new Button("Restart Game");

        VBox formBox = new VBox(10.0,
                new HBox(new Label("YOU WIN")),
                button);

        Scene scene = new Scene(formBox, 400, 400);

        button.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            startScreen(stage);
        });
        stage.setScene(scene);
        stage.show();
    }

    private void youLoose(Stage stage){
        // Stage mit Image erzeugen
        // Button für Restart
        System.out.println("YOU LOST");

        Button button = new Button("Try again!");

        VBox formBox = new VBox(10.0,
                new HBox(new Label("YOU LOOSE")),
                button);

        Scene scene = new Scene(formBox, 400, 400);

        button.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            startScreen(stage);
        });
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

}






