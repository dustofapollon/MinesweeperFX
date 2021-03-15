package de.agym.info.minesweeper.gui;

public class backend_bone {

    // Anfang Attribute
    public  Box first_Box;
    public  Box current_Box;
    private Box last_Box;
    private Box up1_Box;
    private Box up2_Box;
    private final int width;
    private final int height;
    private Runnable looseCallback;
    // Ende Attribute

    // Anfang Methoden
    public backend_bone(int width, int height, int bombs) {
        this.width = width;
        this.height = height;
        System.out.println("width = " + width + ", height = " + height + ", bombs = " + bombs);
        first_Box = new Box();
        current_Box = first_Box;
        last_Box = first_Box;
        /* Left Top Corner generate to Right Top Corner*/
        for (int i = 0; i < width - 1; i++) {
            current_Box.right_Box = new Box();
            last_Box = current_Box;
            current_Box = current_Box.right_Box;
            current_Box.left_Box = last_Box;
        } // end of for
        up1_Box = first_Box;
        /* Spalten generieren*/
        for (int i = 0; i < width; i++) {
            current_Box = up1_Box;
            for (int j = 0; j < height - 1; j++) {
                current_Box.down_Box = new Box();
                last_Box = current_Box;
                current_Box = current_Box.down_Box;
                current_Box.top_Box = last_Box;
            } // end of for
            up1_Box = up1_Box.right_Box;
        } // end of for
        up1_Box = first_Box;
        up2_Box = first_Box.right_Box;
        /*Spalten miteinaner verknüpfen*/
        for (int i = 0; i < width - 1; i++) {
            last_Box = up1_Box.down_Box;
            current_Box = up2_Box.down_Box;
            for (int j = 0; j < height - 1; j++) {
                last_Box.right_Box = current_Box;
                current_Box.left_Box = last_Box;
                current_Box = current_Box.down_Box;
                last_Box = last_Box.down_Box;
            } // end of for
            up1_Box = up2_Box;
            up2_Box = up2_Box.right_Box;
        } // end of for
        System.out.println(first_Box);
        set_bombs(height, width, bombs);
        apply_values(width, height);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Backend Finished");
    }

    public static void main(String[] args) {
        System.out.println(" Backend_Bone ? wird ? gestartet . ");
    }

    public void get_output(int width, int height) {
        last_Box = this.first_Box;
        current_Box = this.first_Box;
        System.out.println(" ");
        for (int i = 0; i <= height - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                if (current_Box.bomb == true) {
                    System.out.print(" X ");
                } // end of if
                else {
                    System.out.print(" " + Integer.toString(current_Box.value) + " ");
                }
                current_Box = current_Box.right_Box;
            } // end of for
            System.out.println(" ");
            last_Box = last_Box.down_Box;
            current_Box = last_Box;
        } // end of for
    }

    public Box get_Box(int x, int y) {
        current_Box = first_Box;
        for (int i = 0; i < x; i++) {
            current_Box = current_Box.right_Box;
        } // end of for
        for (int i = 0; i < y; i++) {
            current_Box = current_Box.down_Box;
        } // end of for
        if (current_Box.bomb && looseCallback != null){
            looseCallback.run();
        }
        return current_Box;
    }

    public void apply_values(int width, int height) {
        last_Box = this.first_Box;
        current_Box = this.first_Box;
        for (int i = 0; i <= height - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                current_Box.apply_value();
                current_Box = current_Box.right_Box;
            } // end of for
            last_Box = last_Box.down_Box;
            current_Box = last_Box;
        }
    }

    public void set_bombs(int height, int width, int bombs) {
        int bomben = bombs -1;
        while (bomben > 0) {
            System.out.println(height);
            int y = (int)(Math.random() * height);
            int x = (int)(Math.random() * width) + 0;
            System.out.println(x);
            Box boxen = get_Box(x, y);
            if (boxen.bomb == false) {
                System.out.println("! ! ! JA ! ! !");
                boxen.bomb = true;
                bomben = bomben - 1;
            }else{
                System.out.println("! ! ! NEIN ! ! !");
            } // end of if

        }
        get_Box(3,4).bomb = true;
        // Ende Methoden
    } // end of backend_bone

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void onLoose(Runnable looseCallback) {
        this.looseCallback = looseCallback;
    }
}


