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

        Scene scene = getStartScene(stage);

        stage.setScene(scene);
        stage.show();
    }

    private Scene getStartScene(Stage stage) {
        Button button = new Button("My Button");

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

        return scene;
    }

    private void startGame(Stage stage, int height, int width, int bombs) {
        backend_bone backend = new backend_bone(height, width, bombs);

        FXMLLoader loader = new FXMLLoader();

        FXMLController controller = new FXMLController();
        controller.setBackend(backend);
        loader.setController(controller);
        loader.setLocation(getClass().getResource("scene.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene gameScene = new Scene(root);
        gameScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        backend.onLoose( () -> youWin(stage));

        stage.setScene(gameScene);
        stage.setFullScreen(true);

    }

    private void youWin(Stage stage){

    }

    private void youLoose(Stage stage){

    }



    public static void main(String[] args) {
        launch(args);
    }

}






