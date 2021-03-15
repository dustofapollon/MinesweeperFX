package de.agym.info.minesweeper.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.robot.Robot;
import javafx.stage.Screen;

public class FXMLController implements Initializable {


    private static String markierungsBild = "/markiertfinal.png";
    private static String grassfinal = "/grassfinal.png";
    private static String bg1= "/bg1.gif";
    private static String bg2= "/bg2.gif";
    private static String bg3= "/bg3.gif";
    private static String bg4= "/bg4.gif";
    private static String bg5= "/bg5.gif";
    private static String bg6= "/bg6.gif";

    private static String tnt = "/tntfinal.png";
    private static String stone= "/stonefinal.png";
    private static String stone1 = "/stone1.png";
    private static String stone2 = "/stone2.png";
    private static String stone3 = "/stone3.png";
    private static String stone4 = "/stone4.png";
    private static String stone5 = "/stone5.png";
    private static String stone6 = "/stone6.png";
    private static String stone7 = "/stone7.png";
    private static String stone8 = "/stone8.png";

    private static double screenWidth = 1920;
    private static double screenHeight = 1080;


    private double height = 10;
    private double width = 10;

    @FXML
    private Label label;

    @FXML
    private GridPane raster;

    @FXML
    private StackPane pane;

    private final backend_bone backend;

    public FXMLController(backend_bone backend){
        this.backend = backend;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initLabel();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        screenHeight = screenBounds.getHeight();
        screenWidth = screenBounds.getWidth();
        this.height = backend.getHeight();
        this.width = backend.getWidth();

        initGridPane();
        backGround();
    }

    public void backGround(){
        BackgroundSize mySize = new BackgroundSize(screenWidth,
                screenHeight,
                false,
                false,
                false,
                false);
        BackgroundImage myBI= new BackgroundImage(Util.getBild(bg1),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                mySize);


//then you set to your node
        pane.setBackground(new Background(myBI));
    }

    public EventHandler createEventhandler(int x, int y) {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            boolean markiert = false;
            boolean opened = false;
            public void handle(MouseEvent e) {
                int getWert;
                int range = 8;
                int min= 1;

                getWert = (int)(Math.random() * range) + min;
                System.out.println("Button clicked" + x+ "/"+ y);
                if (e.getSource() instanceof ImageView && e.getButton() == MouseButton.SECONDARY) {
                    ImageView current = (ImageView) e.getSource();
                    if(markiert == false && opened == false) {
                        current.setImage(Util.getBild(markierungsBild));
                        markiert = true;
                    }
                    else if (markiert == true && opened == false){
                        current.setImage(Util.getBild(grassfinal));
                        markiert = false;
                    }
                    return;
                }
                int aktuellX;
                int aktuellY;
                aktuellX = x;
                aktuellY = y;
                Robot robot = new Robot();

                Box current_box = backend.open_Box(aktuellX,aktuellY);
                if (e.getSource() instanceof ImageView && e.getButton() == MouseButton.PRIMARY) {

                    if(current_box.value==0) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone));
                        opened = true;
                            if(aktuellX != 0){
                                robot.mousePress();
                                createEventhandler(aktuellX-1,aktuellY-1);
                               // aktuellX-1,aktuellY-1;
                                opened= true;

                            }
                        if(aktuellY != 0){
                            createEventhandler(aktuellX, aktuellY-1);
                            opened= true;
                        }
                        if(aktuellX != width-1){
                            createEventhandler(aktuellX+1, aktuellY);
                            opened = true;
                        }
                        if(aktuellY !=height-1){
                            createEventhandler(aktuellX-1, aktuellY+1);
                            opened = true;
                        }
                    }
                    if(current_box.value==1) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone1));
                        opened = true;
                    }
                    if(current_box.value==2) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone2));
                        opened = true;
                    }
                    if(current_box.value==3) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone3));
                        opened = true;
                    }
                    if(current_box.value==4) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone4));
                        opened = true;
                    }
                    if(current_box.value==5) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone5));
                        opened = true;
                    }
                    if(current_box.value==6) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone6));
                        opened = true;
                    }
                    if(current_box.value==7) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone7));
                        opened = true;
                    }
                    if(current_box.value==8) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone8));
                        opened = true;
                    }
                    if(current_box.bomb == true){
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(tnt));
                        opened = true;

                    }


                }
            }

        };
        return eventHandler;
    }


    public ImageView getButton(int iZeile, int iSpalte) {

        Image obama;
        obama = Util.getBild("/obama.jpg");
        Image fackel;
        fackel = Util.getBild(markierungsBild);
        Image stein;
        stein = Util.getBild("/stonefinal.png");
        Image grass;
        grass = Util.getBild("/grassfinal.png");
        Image holz;
        holz = Util.getBild("/rahmenfinal.png");

        Image image = obama;

            image = grass;



        ImageView view = new ImageView(image);
        double boxhoehe;
        boxhoehe = boxSize();
        view.setFitHeight(boxhoehe*0.8);
        view.setPreserveRatio(true);
        //VBox knopftest = new VBox(image);

        // knopftest.setGraphic(image);
        return view;
    }
    private double boxSize(){
        return Math.min(screenWidth/width, screenHeight/height);
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
                hBox.setMaxSize(boxSize(), boxSize());
                hBox.setMinSize(boxSize(), boxSize());
                imageBox.addEventFilter(MouseEvent.MOUSE_CLICKED, createEventhandler(iZeile,iSpalte));
                hBox.setAlignment(Pos.CENTER);
                //HBox.setMargin(hBox, new Insets(50, 50, 50, 50));
                gridPane.add(hBox, iSpalte, iZeile, 1, 1);
            }
        }

    }

}

