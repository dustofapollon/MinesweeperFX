package de.agym.info.minesweeper.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;

    @FXML
    private Button startGame;

    @FXML
    private GridPane raster;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initLabel();
        initButton();
        initGridPane();
    }

    private void initButton() {
        startGame.setText("Start Game");
    }

    private void initLabel() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Test");
    }
    private void initGridPane(){
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");
        Button button5 = new Button("Button 5");
        Button button6 = new Button("Button 6");

        GridPane gridPane;
        gridPane = raster;
        gridPane.add(button1, 0, 0, 1, 1);
        gridPane.add(button2, 1, 0, 1, 1);
        gridPane.add(button3, 2, 0, 1, 1);
        gridPane.add(button4, 0, 1, 1, 1);
        gridPane.add(button5, 1, 1, 1, 1);
        gridPane.add(button6, 2, 1, 1, 1);
    }
}
