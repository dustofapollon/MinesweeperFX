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

    //Instanziierung von Variablen die mit Bilddateipfaden belegt werden für leichteres zuweisen von Grafiken

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

    //Instanziierung von Variablen, die die Bildschirmgröße zugewiesen bekommen

    private static double screenWidth = 1920;
    private static double screenHeight = 1080;

    //Erzeugung der Height(Zeilen) und Width(Spalten) Variablen für das Raster

    private double height = 10;
    private double width = 10;



    //Erzeugung des Gridpanes (Hauptobertfläche)

    @FXML
    private GridPane raster;

    //Erzeugung des Stackpanes

    @FXML
    private StackPane pane;

    //Erzeugung eines Backend-Objektes

    private final backend_bone backend;

    //Konstruktor der Klasse mit this Anweisung für Backend-Objekt

    public FXMLController(backend_bone backend){

        this.backend = backend;
    }

    //Initialisierung der Grafik

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initLabel();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        //Screenhöhe und Breite werden auf die des benutzen Bildschirms angepasst

        screenHeight = screenBounds.getHeight();
        screenWidth = screenBounds.getWidth();
        this.height = backend.getHeight();
        this.width = backend.getWidth();

        initGridPane();
        backGround();
    }

    //Erzeugung des Hintergrundbilds

    public void backGround(){
        BackgroundSize mySize = new BackgroundSize(screenWidth,
                screenHeight,
                false,
                false,
                false,
                false);

        //Backgroundimage bg1 wird ausgewählt und das Bild auf die Bildschirmgröße angepasst, ohne es zu doppeln, sondern durch
        //proportionale Verzerrung

        BackgroundImage myBI= new BackgroundImage(Util.getBild(bg1),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                mySize);


    //Hinzufügen des Gewollten Hintergrundes zum Pane

        pane.setBackground(new Background(myBI));
    }

    //EventHandler erzeugt ein Event, in diesem Fall für einen Mausklick, und benötigt x und y Koordinate der Maus

    public EventHandler createEventhandler(int x, int y) {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

            //Markiert und Opened sind wichtig für das setzen einer Flagge, falls man eine Bombe irgendwo vermutet

            boolean markiert = false;
            boolean opened = false;

            public void handle(MouseEvent e) {

                System.out.println("Button clicked" + x+ "/"+ y);

                //Wenn die Quelle des Klicks dem Typ ImageView angehört und die rechte Maustaste gedrückt wird, dann wird die
                //Variable current des Typs ImageView mit der Quelle belegt

                if (e.getSource() instanceof ImageView && e.getButton() == MouseButton.SECONDARY) {
                    ImageView current = (ImageView) e.getSource();

                    //Wenn das Bild nicht markiert ist und nicht geöffnet wurde, dann ersetze das Grassbild durch das Markierungsbild (Grass mit Flagge)
                    //und setze "Markiert" auf true

                    if(markiert == false && opened == false) {
                        current.setImage(Util.getBild(markierungsBild));
                        markiert = true;
                    }
                    //Wenn das Obere nicht zutrifft und das Feld Markiert ist und nicht geöffnet ist, dann setze das Bild von dem Markierungsbild auf
                    //das Grassbild. Somit werden Markierungen rückgängig gemacht.

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

                //Wenn die Quelle des Klicks dem Typ ImageView angehört und die linke Maustaste gedrückt wird, führe die Funktionen unten aus

                Box current_box = backend.open_Box(aktuellX,aktuellY);
                if (e.getSource() instanceof ImageView && e.getButton() == MouseButton.PRIMARY) {

                    //Wenn der Wert der Box die gerade angeklickt wurde null beträgt, dann besetz das Feld mit dem Bild des Steines ohne Zahl und
                    //setze die Variable "opened" auf true

                    if(current_box.value==0) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone));
                        opened = true;

                        //Für Erweiterung:

                           /* if(aktuellX != 0){

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
                        }*/
                    }

                    //Wenn der Wert der Box die gerade angeklickt wurde 1 beträgt, dann besetz das Feld mit dem Bild des Steines mit der 1 und
                    //setze die Variable "opened" auf true

                    if(current_box.value==1) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone1));
                        opened = true;
                    }

                    //Wenn der Wert der Box die gerade angeklickt wurde 2 beträgt, dann besetz das Feld mit dem Bild des Steines mit der 2 und
                    //setze die Variable "opened" auf true

                    if(current_box.value==2) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone2));
                        opened = true;
                    }

                    //Wenn der Wert der Box die gerade angeklickt wurde 3 beträgt, dann besetz das Feld mit dem Bild des Steines mit der 3 und
                    //setze die Variable "opened" auf true


                    if(current_box.value==3) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone3));
                        opened = true;
                    }

                    //Wenn der Wert der Box die gerade angeklickt wurde 4 beträgt, dann besetz das Feld mit dem Bild des Steines mit der 4 und
                    //setze die Variable "opened" auf true


                    if(current_box.value==4) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone4));
                        opened = true;
                    }

                    //Wenn der Wert der Box die gerade angeklickt wurde 5 beträgt, dann besetz das Feld mit dem Bild des Steines mit der 5 und
                    //setze die Variable "opened" auf true


                    if(current_box.value==5) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone5));
                        opened = true;
                    }

                    //Wenn der Wert der Box die gerade angeklickt wurde 6 beträgt, dann besetz das Feld mit dem Bild des Steines mit der 6 und
                    //setze die Variable "opened" auf true


                    if(current_box.value==6) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone6));
                        opened = true;
                    }

                    //Wenn der Wert der Box die gerade angeklickt wurde 7 beträgt, dann besetz das Feld mit dem Bild des Steines mit der 7 und
                    //setze die Variable "opened" auf true


                    if(current_box.value==7) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone7));
                        opened = true;
                    }

                    //Wenn der Wert der Box die gerade angeklickt wurde 8 beträgt, dann besetz das Feld mit dem Bild des Steines mit der 8 und
                    //setze die Variable "opened" auf true


                    if(current_box.value==8) {
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(stone8));
                        opened = true;
                    }
                    //Wenn auf dem aktuellen Boxfeld eine Bombe liegt, dann setz die Grafik zum TNT Bild und setze "opened" auf true
                    if(current_box.bomb == true){
                        ImageView current = (ImageView) e.getSource();
                        current.setImage(Util.getBild(tnt));
                        opened = true;

                    }


                }
            }

        };

        //Gib das Objekt eventHandler des typ EventHandler zurück

        return eventHandler;
    }

    //Erstelle die öffentliche getter Methode "getButton" welche einen Wert des Typ ImageView zurückgeben soll
    //und einen Integer Wert für die Zeile und die Spalte braucht

    public ImageView getButton(int iZeile, int iSpalte) {

        //Variableninstanziierung mit Bilddateipfaden

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


        //Erstellung eines Bildes, welches zurückgegeben wird

        ImageView view = new ImageView(image);
        double boxhoehe;
        boxhoehe = boxSize();
        view.setFitHeight(boxhoehe*0.8);
        view.setPreserveRatio(true);
        //VBox knopftest = new VBox(image);

        // knopftest.setGraphic(image);
        return view;
    }

    //Die Größe der Box, welche das Bild darstellt, wird ausgerechnet indem Bildschirmbreite/Spaltenanzahl und
    //Bildschirmhöhe/Zeilenanzahl gerechnet wird. Damit kann dann die ideale Größe der Boxen errechnet werden

    private double boxSize(){
        return Math.min(screenWidth/width, screenHeight/height);
    }

    //HAUPTFUNKTION : GRIPANE
    //Raster des Spiels

    private void initGridPane() {

        //Input für Feldgröße

        GridPane gridPane;
        gridPane = raster;

        //Erstellung des Feldes, indem eine Spalte aufgemacht wird, danach alle Zeilen runtergegangene werden und dann die nächste
        //Spalte aufgemacht wird und dies bis das Feld komplett ist. Dabei wird in jedes Feld eine hbox mit dem oben festgelegten image belegt
        //und das Mousevent mit Referenz zum Eventhandler angehangen


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

