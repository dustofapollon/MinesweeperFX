package de.agym.info.minesweeper.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MineSweeper");
        startScreen(stage);

    }

    private void startScreen(Stage stage) {

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);


        String fontStyle = "-fx-font-size:40;-fx-font-family:monospace";
        String fontStyleBold = "-fx-font-weight:bold;"+ fontStyle;
        TextField height = new TextField("0");
        height.setPrefSize(150,30);
        height.setStyle(fontStyle);

        TextField width = new TextField("0");
        width.setPrefSize(150 ,30);
        width.setStyle(fontStyle);

        TextField bombCount = new TextField("0");
        bombCount.setPrefSize(150,30);
        bombCount.setStyle(fontStyle);

        Button startButton = new Button("START GAME"); // ImageView
        startButton.setStyle(fontStyleBold);


        Label hoch = new Label("Height");
        hoch.setStyle(fontStyleBold);
        Label breit = new Label("Width");
        breit.setStyle(fontStyleBold);
        Label bombi = new Label("Bombs");
        bombi.setStyle(fontStyleBold);


        pane.add(hoch, 0,0);
        pane.add(height, 1,0);


        pane.add(breit, 0,1);
        pane.add(width, 1,1);

        pane.add(bombi, 0,2);
        pane.add(bombCount, 1,2);

        pane.add(startButton,0,3,2,1);
        hoch.setTextFill(Color.web("#ffffff"));
        breit.setTextFill(Color.web("#ffffff"));
        bombi.setTextFill(Color.web("#ffffff"));

        HBox hBox = new HBox(pane);
        hBox.setAlignment(Pos.CENTER);
        hBox.setMinHeight(500);
        VBox formBox = new VBox(hBox);
        formBox.setAlignment(Pos.BOTTOM_CENTER);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double screenHeight = screenBounds.getHeight();
        double screenWidth = screenBounds.getWidth();

        BackgroundSize mySize = new BackgroundSize(screenWidth,
                screenHeight,
                false,
                false,
                false,
                false);

        Image image = new Image(getClass().getResourceAsStream("/start.png"));

        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                mySize);


//then you set to your node
        formBox.setBackground(new Background(myBI));

        Scene scene = new Scene(formBox, 400, 400);


        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            startGame(stage,
                    Integer.parseInt(height.getText()),
                    Integer.parseInt(width.getText()),
                    Integer.parseInt(bombCount.getText())
            );
        });
        stage.setScene(scene);
        stage.setFullScreen(true);
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
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        String fontStyle = "-fx-font-size:40;-fx-font-family:monospace";
        String fontStyleBold = "-fx-font-weight:bold;"+ fontStyle;
        Button startButton = new Button("Play Again"); // ImageView
        startButton.setStyle(fontStyleBold);
        pane.add(startButton,0,0,2,1);

        HBox hBox = new HBox(pane);
        hBox.setAlignment(Pos.CENTER);
        VBox formBox = new VBox(hBox);
        formBox.setAlignment(Pos.CENTER);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double screenHeight = screenBounds.getHeight();
        double screenWidth = screenBounds.getWidth();

        BackgroundSize mySize = new BackgroundSize(screenWidth,
                screenHeight,
                false,
                false,
                false,
                false);

        Image image = new Image(getClass().getResourceAsStream("/youwin.png"));

        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                mySize);


//then you set to your node
        formBox.setBackground(new Background(myBI));

        Scene scene = new Scene(formBox, 400, 400);

        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            startScreen(stage);
        });
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void youLoose(Stage stage){

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        Button startButton = new Button("Try Again!"); // ImageView
        startButton.setStyle("-fx-font-size:40");
        pane.add(startButton,0,0,2,1);

        HBox hBox = new HBox(pane);
        hBox.setAlignment(Pos.CENTER);
        VBox formBox = new VBox(hBox);
        formBox.setAlignment(Pos.CENTER);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double screenHeight = screenBounds.getHeight();
        double screenWidth = screenBounds.getWidth();

        BackgroundSize mySize = new BackgroundSize(screenWidth,
                screenHeight,
                false,
                false,
                false,
                false);

        Image image = new Image(getClass().getResourceAsStream("/netherfinal.png"));

        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                mySize);


//then you set to your node
        formBox.setBackground(new Background(myBI));

        Scene scene = new Scene(formBox, 400, 400);

        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            startScreen(stage);
        });
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

}






