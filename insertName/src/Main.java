/*
 * Authors: Ethan Ling, Kevin Chu
 * Period: 4
 * Date: 4/3/2019
 * Capstone Project
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel implements KeyListener {
    private static final int DRAWING_WIDTH = 800;
    private static final int DRAWING_HEIGHT = 600;
    private final Font FONT = new Font("ComicSans", Font.PLAIN, 20);
    private boolean wKeyPressed, sKeyPressed, aKeyPressed, dKeyPressed,
            upKeyPressed, leftKeyPressed, downKeyPressed, rightKeyPressed;
    private Player player;


    private Main() {
        this.setFocusable(true);
        addKeyListener(this);
        player = new Player(200, 200);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("MongyDefense");

        window.setBounds(300, 100, DRAWING_WIDTH, DRAWING_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Main panel = new Main();
        panel.setBackground(Color.PINK);
        Container c = window.getContentPane();
        c.add(panel);

        //Icon
        ImageIcon img = new ImageIcon("assets/Icon.png");
        window.setIconImage(img.getImage());

        window.setVisible(true);

        panel.run();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), 3 * getHeight() / 4 - 10);
        g.setColor(Color.BLACK);
        g.setFont(FONT);
        g.drawString("Power Ups", getWidth() / 2 - 30, 13 * getHeight() / 16);
        g.drawLine(0, 27 * getHeight() / 32, getWidth(), 27 * getHeight() / 32);

        player.draw(g, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        //Movement
        if (key == KeyEvent.VK_A) {
            aKeyPressed = true;
        }
        if (key == KeyEvent.VK_D) {
            dKeyPressed = true;
        }
        if (key == KeyEvent.VK_W) {
            wKeyPressed = true;
        }
        if (key == KeyEvent.VK_S) {
            sKeyPressed = true;
        }

        //Shooting
        if (key == KeyEvent.VK_UP) {
            upKeyPressed = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            leftKeyPressed = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            downKeyPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightKeyPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        //Movement
        if (key == KeyEvent.VK_A) {
            aKeyPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            dKeyPressed = false;
        }
        if (key == KeyEvent.VK_W) {
            wKeyPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            sKeyPressed = false;
        }

        //Shooting
        if (key == KeyEvent.VK_UP) {
            upKeyPressed = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            leftKeyPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            downKeyPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightKeyPressed = false;
        }

    }


    private void run() {

        // MAKE A CHANGE
        while (true) {
            if (aKeyPressed) {
                player.walkX(-1);
            }
            if (dKeyPressed) {
                player.walkX(1);
            }
            if (wKeyPressed) {
                player.walkY(-1);
            }
            if (sKeyPressed) {
                player.walkY(1);
            }

            player.update();
            checkPlayer();
            // SHOW THE CHANGE
            repaint();


            // WAIT
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void checkPlayer() {
        int x = player.getX() + player.getWidth() / 2;
        int y = player.getY() + player.getHeight() / 2;
        if (x < 0 || x > getWidth() || y < 0 || y > 2 * getHeight() / 3)
            player = new Player(360, 200);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}