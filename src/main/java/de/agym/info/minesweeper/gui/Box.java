package de.agym.info.minesweeper.gui;

public class Box {

    // Anfang Attribute
    public Box left_Box = null;
    public Box right_Box = null;
    public Box top_Box = null;
    public Box down_Box = null;
    public boolean visible = false;
    public boolean bomb = false;
    public int value = 0;
    // Ende Attribute

    // Anfang Methoden

    public void apply_value() {
        if (this.left_Box != null) {
            if (left_Box.bomb == true) {
                value = value + 1;
            } // end of if
        } // end of if
        if (this.right_Box != null) {
            if (right_Box.bomb == true) {
                value = value + 1;
            } // end of if
        } // end of if
        if (this.top_Box != null) {
            if (top_Box.bomb == true) {
                value = value + 1;
            } // end of if
        } // end of if
        if (this.down_Box != null) {
            if (down_Box.bomb == true) {
                value = value + 1;
            } // end of if
        } // end of if
        if (this.left_Box != null && this.top_Box != null) {
            if (left_Box.top_Box.bomb == true) {
                value = value + 1;
            } // end of if
        } // end of if
        if (this.left_Box != null && this.down_Box != null) {
            if (left_Box.down_Box.bomb == true) {
                value = value + 1;
            } // end of if
        } // end of if
        if (this.right_Box != null && this.top_Box != null) {
            if (right_Box.top_Box.bomb == true) {
                value = value + 1;
            } // end of if
        } // end of if
        if (this.right_Box != null && this.down_Box != null) {
            if (right_Box.down_Box.bomb == true) {
                value = value + 1;
            } // end of if
        } // end of if

    }

    public static void main(String[] args) {
        System.out.println(" Box ? wird ? gestartet . ");
    }

    // Ende Methoden
} // end of box
