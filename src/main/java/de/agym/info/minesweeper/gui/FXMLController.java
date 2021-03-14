package de.agym.info.minesweeper.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FXMLController implements Initializable {


    private static String markierungsBild = "src/main/resources/markiertfinal.png";
    private static String stone1 = "src/main/resources/stone1.png";
    private static String stone2 = "src/main/resources/stone2.png";
    private static String stone3 = "src/main/resources/stone3.png";
    private static String stone4 = "src/main/resources/stone4.png";
    private static String stone5 = "src/main/resources/stone5.png";
    private static String stone6 = "src/main/resources/stone6.png";
    private static String stone7 = "src/main/resources/stone7.png";
    private static String stone8 = "src/main/resources/stone8.png";

    private static int screenWidth = 600;
    private static int screenHeight = 400;

    private static int height = 10;
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
    public void createBackground(){
        HBox background = new HBox(Double.parseDouble(markierungsBild));
        background.setMaxSize(screenWidth, screenHeight);
        background.setMinSize(screenWidth, screenHeight);
    }
    public EventHandler createEventhandler(int x, int y) {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                int getWert;
                int range = 8;
                int min= 1;
                getWert = (int)(Math.random() * range) + min;
                System.out.println("Button clicked" + x+ "/"+ y);
                if (e.getSource() instanceof ImageView && e.getButton() == MouseButton.SECONDARY) {
                    ImageView current = (ImageView) e.getSource();
                    current.setImage(getBild(markierungsBild));


                }
                if (e.getSource() instanceof ImageView && e.getButton() == MouseButton.PRIMARY) {
                    if(getWert==1) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(getBild(stone1));
                    }
                    if(getWert==2) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(getBild(stone2));
                    }
                    if(getWert==3) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(getBild(stone3));
                    }
                    if(getWert==4) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(getBild(stone4));
                    }
                    if(getWert==5) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(getBild(stone5));
                    }
                    if(getWert==6) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(getBild(stone6));
                    }
                    if(getWert==7) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(getBild(stone7));
                    }
                    if(getWert==8) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(getBild(stone8));
                    }


                }
            }

        };
        return eventHandler;
    }


    public Image getBild(String name) {
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

    public ImageView getButton(int iZeile, int iSpalte) {

        Image obama;
        obama = getBild("src/main/resources/obama.jpg");
        Image fackel;
        fackel = getBild(markierungsBild);
        Image stein;
        stein = getBild("src/main/resources/stonefinal.png");
        Image grass;
        grass = getBild("src/main/resources/grassfinal.png");
        Image holz;
        holz = getBild("src/main/resources/rahmenfinal.png");

        Image image = obama;

            image = grass;



        ImageView view = new ImageView(image);
        int boxhoehe;
        boxhoehe = Math.min(screenHeight/height, screenWidth/width);
        view.setFitHeight(boxhoehe*0.8);
        //view.setFitWidth(600/width);
        //view.fitWidthProperty().bind(.widthProperty());
        //img.fitHeightProperty().bind(btn.heightProperty());
        view.setPreserveRatio(true);
        //VBox knopftest = new VBox(image);

        // knopftest.setGraphic(image);
        return view;
    }

    private void initGridPane() {
//Input für Feldgröße

        GridPane gridPane;
        gridPane = raster;

        for (int iSpalte = 0; iSpalte < width; iSpalte++) {
            for (int iZeile = 0; iZeile < height; iZeile++) {
                System.out.println("Button" + iZeile + "/" + iSpalte);

                ImageView imageBox = getButton(iZeile, iSpalte);
                HBox hBox = new HBox(imageBox);
                hBox.setMaxSize(screenWidth/width, screenHeight/height);
                hBox.setMinSize(screenWidth/width, screenHeight/height);
                imageBox.addEventFilter(MouseEvent.MOUSE_CLICKED, createEventhandler(iZeile,iSpalte));
                hBox.setAlignment(Pos.BASELINE_CENTER);
                //HBox.setMargin(hBox, new Insets(50, 50, 50, 50));
                gridPane.add(hBox, iSpalte, iZeile, 1, 1);
            }
        }

    }
}
