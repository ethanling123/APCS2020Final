//Imports

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/*
 * Authors: Ethan Ling, Kevin Chu
 * Period: 4
 * Date: 5/28/2019
 * MonkeyDefence Capstone Project
 * Class: Main
 */

public class Main extends JPanel implements ActionListener, KeyListener, MouseListener {

    // ==FIELDS==
    private static final int DRAWING_WIDTH = 1000; //Width of Window
    private static final int DRAWING_HEIGHT = 750; //Height of Window

    private final Font FONT = new Font("ComicSans", Font.PLAIN, 20);

    private Color background = new Color(86, 176, 0);


    //Main Method
    public static void main(String[] args) {

        ImageIcon img = new ImageIcon(Main.class.getResource("/assets/Icon.png"));

        //Window
        JFrame window = new JFrame("Spaceteroids");
        window.setBounds(150, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel
        Main panel = new Main();
        panel.setBackground(Color.PINK);
        Container c = window.getContentPane();
        c.add(panel);
        window.setIconImage(img.getImage());
        window.setVisible(true);
    }

    //Paints everything
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}