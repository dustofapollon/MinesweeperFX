package de.agym.info.minesweeper.gui;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Util {

    //Bild Klasse
    //sorgt dafür, dass der Dateipfad kürzer ist und die Dateien nur im resources Ordner sein müssen

    public static Image getBild(String name) {
        URL resource = Util.class.getResource(name);
        Objects.requireNonNull(resource, "nicht gefunden " + name);
        try {
            return new Image(resource.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
