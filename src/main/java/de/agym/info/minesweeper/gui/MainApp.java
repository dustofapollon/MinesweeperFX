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

    //Start Methode
    //Erstellt Startscreen und nennt das Fenster "MineSweeper"

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MineSweeper");
        startScreen(stage);

    }

    //legt fest, wie der Startscreeb erstellt wird

    private void startScreen(Stage stage) {

        //erstellt pane des Typs Gridpane und setzt einen Rand mit Hgap und Vgap

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);


        //Erzeugt Variablen "fontStyle" und "fontStyleBold" welche mit einem String belegt werden, welcher den Text für Schrifteigenschaften beinhaltet

        String fontStyle = "-fx-font-size:40;-fx-font-family:monospace";
        String fontStyleBold = "-fx-font-weight:bold;"+ fontStyle;

        //Erschafft ein Textfeld und legt die Schrift,Höhe,Breite und Schriftstyle fest

        TextField height = new TextField("0");
        height.setPrefSize(150,30);
        height.setStyle(fontStyle);

        //Erschafft ein Textfeld und legt die Schrift,Höhe,Breite und Schriftstyle fest

        TextField width = new TextField("0");
        width.setPrefSize(150 ,30);
        width.setStyle(fontStyle);

        //Erschafft ein Textfeld und legt die Schrift,Höhe,Breite und Schriftstyle fest

        TextField bombCount = new TextField("0");
        bombCount.setPrefSize(150,30);
        bombCount.setStyle(fontStyle);

        //Erschafft Start
        // Button
        Button startButton = new Button("START GAME"); // ImageView
        startButton.setStyle(fontStyleBold);

        //Erzeugt die Schrift für die Textfelder oben

        Label hoch = new Label("Height");
        hoch.setStyle(fontStyleBold);
        Label breit = new Label("Width");
        breit.setStyle(fontStyleBold);
        Label bombi = new Label("Bombs");
        bombi.setStyle(fontStyleBold);

        //Fügt die Schrift und die Textfelder dem Pane hinzu

        pane.add(hoch, 0,0);
        pane.add(height, 1,0);

        //Fügt die Schrift und die Textfelder dem Pane hinzu

        pane.add(breit, 0,1);
        pane.add(width, 1,1);

        //Fügt die Schrift und die Textfelder dem Pane hinzu

        pane.add(bombi, 0,2);
        pane.add(bombCount, 1,2);

        //Fügt die Schrift und die Textfelder dem Pane hinzu

        pane.add(startButton,0,3,2,1);

        //Ändert die Schriftfartbe der Texte

        hoch.setTextFill(Color.web("#ffffff"));
        breit.setTextFill(Color.web("#ffffff"));
        bombi.setTextFill(Color.web("#ffffff"));

        //Erzeigt eine Hbox und eine Vbox mit dem Namen formBox, welche beide den Rahmen für die Buttons und den Text bilden

        HBox hBox = new HBox(pane);
        hBox.setAlignment(Pos.CENTER);
        hBox.setMinHeight(500);
        VBox formBox = new VBox(hBox);
        formBox.setAlignment(Pos.BOTTOM_CENTER);

        //Findet die Bildschirmgröße heraus

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double screenHeight = screenBounds.getHeight();
        double screenWidth = screenBounds.getWidth();

        //Setzt die Bildschirmgröße für das Programm fest

        BackgroundSize mySize = new BackgroundSize(screenWidth,
                screenHeight,
                false,
                false,
                false,
                false);

        //Hintergrundbild wird erzeugt und festgelegt

        Image image = new Image(getClass().getResourceAsStream("/start.png"));

        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                mySize);

        //Erzeugt den Start button und das damit  verbundene Mouseclick Event

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

    //Methode startGame
    //Initialisiert das Spiel

    private void startGame(Stage stage, int height, int width, int bombs) {

        //Backend wird mit den Werten aus den Textfeldern erzeugt

        backend_bone backend = new backend_bone(height, width, bombs);

        //Controller wird geladen

        FXMLLoader loader = new FXMLLoader();

        FXMLController controller = new FXMLController(backend);
        loader.setController(controller);
        loader.setLocation(getClass().getResource("scene.fxml"));

        //Wenn beim Backend die Methode onLoose ausgeführt wird, wird zur Loose Stage gewechselt

        backend.onLoose(() -> youLoose(stage));

        //Wenn beim Backend die Methode onWin ausgeführt wird, wird zur Win Stage gewechselt


        backend.onWin(() -> youWin(stage));

        //Formatierung und Alignment der Scene

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

    //Win Screen a.k.a. WinStage

    private void youWin(Stage stage){

        //Erstellt neues Gridpane indem ein Play Again button integriert wird

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        String fontStyle = "-fx-font-size:40;-fx-font-family:monospace";
        String fontStyleBold = "-fx-font-weight:bold;"+ fontStyle;
        Button startButton = new Button("Play Again");
        startButton.setStyle(fontStyleBold);
        pane.add(startButton,0,0,2,1);

        //HBox und VBox sorgen für Alignment des Play Again Buttons

        HBox hBox = new HBox(pane);
        hBox.setAlignment(Pos.CENTER);
        VBox formBox = new VBox(hBox);
        formBox.setAlignment(Pos.CENTER);

        //Wie schon obene wird für diesen Screen erneut die Größe des Bildschirms ermittelt und die Stage darauf angepasst

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double screenHeight = screenBounds.getHeight();
        double screenWidth = screenBounds.getWidth();

        BackgroundSize mySize = new BackgroundSize(screenWidth,
                screenHeight,
                false,
                false,
                false,
                false);

        //Der Hintergrund wird festgelegt

        Image image = new Image(getClass().getResourceAsStream("/youwin.png"));

        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                mySize);


        //Wenn auf den Play Again Button gedrückt wird, wird wieder auf die erste Scene, bzw den Startscreen, gewechselt
        formBox.setBackground(new Background(myBI));

        Scene scene = new Scene(formBox, 400, 400);

        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            startScreen(stage);
        });
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    //Loose Screen a.k.a. LooseStage

    private void youLoose(Stage stage){

        //Erstellt neues Gridpane indem ein Try Again button integriert wird

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        Button startButton = new Button("Try Again!"); // ImageView
        startButton.setStyle("-fx-font-size:40");
        pane.add(startButton,0,0,2,1);

        //HBox und VBox sorgen für Alignment des Try Again Buttons


        HBox hBox = new HBox(pane);
        hBox.setAlignment(Pos.CENTER);
        VBox formBox = new VBox(hBox);
        formBox.setAlignment(Pos.CENTER);

        //Wie schon obene wird für diesen Screen erneut die Größe des Bildschirms ermittelt und die Stage darauf angepasst

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double screenHeight = screenBounds.getHeight();
        double screenWidth = screenBounds.getWidth();

        BackgroundSize mySize = new BackgroundSize(screenWidth,
                screenHeight,
                false,
                false,
                false,
                false);

        //Der Hintergrund wird festgelegt

        Image image = new Image(getClass().getResourceAsStream("/netherfinal.png"));

        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                mySize);



        formBox.setBackground(new Background(myBI));

        Scene scene = new Scene(formBox, 400, 400);

        //Wenn auf den Play Again Button gedrückt wird, wird wieder auf die erste Scene, bzw den Startscreen, gewechselt

        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            startScreen(stage);
        });
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }


//Main Methode fürt einfach alles aus
    
    public static void main(String[] args) {
        launch(args);
    }

}






