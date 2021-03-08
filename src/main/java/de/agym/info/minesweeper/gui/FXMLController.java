package de.agym.info.minesweeper.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;



    @FXML
    private GridPane raster;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // initLabel();
        initGridPane();
    }
    public EventHandler createEventhandler() {
        EventHandler<MouseEvent> eventHandler= new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                System.out.println("Button clicked" + e.getSource());
            }

        };
        return eventHandler;
    }

    /*private void initLabel() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Test");
    } */
    private void initGridPane(){

        int height = 5;
        int width = 5;
        GridPane gridPane;
        gridPane = raster;

        for(int iSpalte = 0; iSpalte <= width; iSpalte++){
            for(int iZeile = 0; iZeile <= height; iZeile++){
                System.out.println("Button"+ iZeile +"/"+ iSpalte);
                    Button button = new Button("Button"+ iZeile +"/"+ iSpalte);
                    button.addEventFilter(MouseEvent.MOUSE_CLICKED, createEventhandler());
                    button.setMaxSize(100,100);
                    button.setMinSize(100,100);
                gridPane.add(button, iSpalte, iZeile, 1, 1);
            }
        }
        /*
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");
        Button button5 = new Button("Button 5");
        Button button6 = new Button("Button 6");

         */




    }
}
