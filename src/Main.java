//Imports

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;

/**
 * A main method for the game.
 *
 * @author Ethan Ling, Hangyul Yun, John Whitney
 * @version 05/03/2020
 */
public class Main {
    public static final int DRAWING_WIDTH = 1000; //Width of Window
    public static final int DRAWING_HEIGHT = 750; //Height of Window
    private static final Color background = new Color(94, 99, 112);
    private static ImageIcon img = new ImageIcon(Main.class.getResource("/assets/icon.png"));

    //Main Method
    public static void main(String[] args) {
        //Window

        DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();
		canvas.requestFocus();

        window.setBounds(150, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = window.getContentPane();

        window.setMinimumSize(new Dimension(100,100));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setVisible(true);
        window.setIconImage(img.getImage());

    }
}