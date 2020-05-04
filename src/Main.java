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

/**
 *  A main method for the game.
 * @author Ethan Ling, Hangyul Yun
 * @version 05/03/2020
 *
 */
public class Main extends JPanel implements ActionListener, KeyListener, MouseListener {

    // ==FIELDS==
    private static final int DRAWING_WIDTH = 1000; //Width of Window
    private static final int DRAWING_HEIGHT = 750; //Height of Window
    private static final Color background = new Color(94, 99, 112);

    private final Font font = new Font("ComicSans", Font.PLAIN, 20);

    private int health = 100;
    private int score = 0;
    private int highScore;
    
    private PlayerCharacter player = new PlayerCharacter(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 2, new ImageIcon(Main.class.getResource("/assets/Icon.png")), health, null);

    //Main Method
    public static void main(String[] args) {
        ImageIcon img = new ImageIcon(Main.class.getResource("/assets/Icon.png"));

        //Window
        JFrame window = new JFrame("Spaceteroids");
        window.setBounds(150, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
        window.setExtendedState(window.getExtendedState() | JFrame  .MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel
        Main panel = new Main();
        panel.setBackground(background);
        Container c = window.getContentPane();
        c.add(panel);
        window.setIconImage(img.getImage());
        window.setVisible(true);
    }

    //Paints everything
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.lightGray);
        g.fillRect((int) (getWidth()*7.5/9),0, (getWidth()),getHeight());

        g.setColor(Color.black);
        g.setFont(font);
        g.drawString("Score: " + score, (int) (getWidth()*7.7/9),getHeight()/9);
        g.drawString("High Score: " + highScore, (int) (getWidth()*7.7/9),getHeight()*2/9);
        g.drawString("Health: " + health, (int) (getWidth()*7.7/9),getHeight()*5/9);

        player.draw(this, g);
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