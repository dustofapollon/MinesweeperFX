package de.agym.info.minesweeper.gui;

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


    private static String markierungsBild = "src/main/resources/flagge (2).png";


    private static int height = 5;
    private static int width = 10;

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
               if( e.getSource() instanceof Button) {
                   Button klickKnopf = (Button) e.getSource();
                   ImageView flagge = getBild(markierungsBild);

                   klickKnopf.setGraphic(flagge);

               }
            }

        };
        return eventHandler;
    }


    public ImageView getBild(String name){
        FileInputStream input0;
        try {
            input0 = new FileInputStream(name);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Pfad nicht gefunden");
        }

        Image image;
        image = new Image(input0);
        ImageView view = new ImageView(image);
        //view.setFitHeight(400/height);;
        //view.setFitWidth(600/width);
        //view.fitWidthProperty().bind(.widthProperty());
        //img.fitHeightProperty().bind(btn.heightProperty());
        view.setPreserveRatio(true);
        return view;
    }
    public Button getButton(int iZeile, int iSpalte){

        ImageView obama;
        obama = getBild("src/main/resources/obama.jpg");
        ImageView fackel;
        fackel = getBild(markierungsBild);
        ImageView stein;
        stein = getBild("src/main/resources/stone.jpg");
        ImageView grass;
        grass= getBild("src/main/resources/grass.jpg");
        ImageView holz;
        holz = getBild("src/main/resources/wood.png");

        ImageView image = obama;

        if(iZeile %3 == 0) {
            image = grass;
        }
        if(iZeile %3 == 1) {
            image = stein;
        }
        if(iZeile %3== 2) {
            image = holz;
        }


        Button knopftest = new Button("Button"+ iZeile +"/"+ iSpalte);
        knopftest.setGraphic(image);
        return knopftest;
    }
    private void initGridPane(){
//Input für Feldgröße

        GridPane gridPane;
        gridPane = raster;

        for(int iSpalte = 0; iSpalte < width; iSpalte++){
            for(int iZeile = 0; iZeile < height; iZeile++){
                System.out.println("Button"+ iZeile +"/"+ iSpalte);

                    Button button = getButton(iZeile,iSpalte);
                    button.addEventFilter(MouseEvent.MOUSE_CLICKED, createEventhandler());
                        button.setMaxSize(600/width,400/height);
                        button.setMinSize(600/width,400/height);

                gridPane.add(button, iSpalte, iZeile, 1, 1);
            }
        }

    }
}
