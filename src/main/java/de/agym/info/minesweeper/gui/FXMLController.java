package de.agym.info.minesweeper.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public Image getBild(String name){
        FileInputStream input0;
        try {
            input0 = new FileInputStream(name);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Pfad nicht gefunden");
        }

        Image image;
        image = new Image(input0);
        return image;
    }
    public Button getButton(int iZeile, int iSpalte){

        Image fackel;
        fackel = getBild("src/main/resources/torch.png");
        Image stein;
        stein = getBild("src/main/resources/stone.jpg");
        Image grass;
        grass= getBild("src/main/resources/grass.jpg");
        Image holz;
        holz = getBild("src/main/resources/wood.png");

        Image image = fackel;

        if(iZeile %3 == 0) {
            image = grass;
        }
        if(iZeile %3 == 1) {
            image = stein;
        }
        if(iZeile %3== 2) {
            image = holz;
        }

        ImageView view = new ImageView(image);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        Button knopftest = new Button("Button"+ iZeile +"/"+ iSpalte);
        knopftest.setGraphic(view);
        return knopftest;
    }
    private void initGridPane(){

        int height = 5;
        int width = 5;
        GridPane gridPane;
        gridPane = raster;

        for(int iSpalte = 0; iSpalte <= width; iSpalte++){
            for(int iZeile = 0; iZeile <= height; iZeile++){
                System.out.println("Button"+ iZeile +"/"+ iSpalte);

                    Button button = getButton(iZeile,iSpalte);
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
