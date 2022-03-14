import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel {

    private Timer timer;
    private Timer fireTimer;
    private String message = "";
    ArrayList<Missile> missiles = new ArrayList<Missile>();
    ArrayList<Enemy> enemy = new ArrayList<Enemy>();
    //private Enemy[] enemy;
    private Player player;
    //private Brick[] bricks;
    private boolean inGame = true;
    private int splitseconds;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(600, 400));

        gameInit();

    }

    private void gameInit() {

        player = new Player();


        int k = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 5; j++) {
                enemy.add(new Enemy());
                enemy.get(k).x = j * 60 + 165;
                enemy.get(k).y = i * 45 + 30;

                k++;
            }
        }
        splitseconds = 0;
        timer = new Timer(10, new GameCycle());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {

            drawObjects(g2d);
        } else {

            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {

     //   g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
      //          ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawRect(player.x,player.y,50,40);
        for (int i = 0; i < missiles.size();i++) {
            if (missiles.get(i) != null) {
                g2d.drawRect(missiles.get(i).x, missiles.get(i).y, 6, 12);
            }
        }

        for (int i = 0; i < enemy.size(); i++) {
                g2d.drawRect(enemy.get(i).x,enemy.get(i).y,45,40);
        }
      //  g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
      //          paddle.getImageWidth(), paddle.getImageHeight(), this);

    }

    void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == 32 && splitseconds > 120) {
            missiles.add(new Missile());
            missiles.get(missiles.size()-1).x = player.x+25;
            missiles.get(missiles.size()-1).y = 350;
            splitseconds = 0;
        }

    }


    private void gameFinished(Graphics2D g2d) {

        var font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message,
                (580 - fontMetrics.stringWidth(message)) / 2,
                400 / 2);
    }

    public class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
            Board.this.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);
        }
    }

    public class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            splitseconds++;
            doGameCycle();
        }
    }

    private void doGameCycle() {

        for (int i = 0; i < missiles.size();i++) {
            if (missiles.get(i) != null) {
                missiles.get(i).move();
            }
        }
        player.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {

        inGame = false;
        timer.stop();
    }

    private void checkCollision() {
        for (int i = 0; i < missiles.size(); i++){
            if (missiles.get(i).y < 10){
                missiles.remove(i);
            }
        }

            if (enemy.size() == 0) {
                message = "Victory";
                stopGame();
            }



        for (int i = 0; i < enemy.size(); i++) {
            for (int j = 0; j < missiles.size(); j++) {
                if ((java.lang.Math.abs(missiles.get(j).x - enemy.get(i).x) <= 35) && (java.lang.Math.abs(missiles.get(j).y - enemy.get(i).y) <= 35)) {
                    enemy.remove(i);
                    missiles.remove(j);
                }
            }
        }
    }



}






/*

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static void main(String[] args) {

        Shooter s = new Shooter();
        JFrame f = new JFrame();
        f.add(s);
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Shooter");

        class AL extends JPanel implements ActionListener{
            Timer t = new Timer(5, this);
            public void actionPerformed(ActionEvent e) {
                setFocusable(true);
                setFocusTraversalKeysEnabled(false);
                repaint();
            }
        }

        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                int keyCode = event.getKeyCode();
                switch (keyCode) {
                    case 65:
                        s.x -= 3;
                        break;
                    case 68:
                        s.x += 3;
                        break;
                    case 66:
                        Missile m = new Missile();
                        f.add(m);
                        m.x = s.x;
                        m.y = s.y-20;

                }
            }

            @Override
            public void keyReleased(KeyEvent event) {

            }

            @Override
            public void keyTyped(KeyEvent event) {

            }

        };

        f.addKeyListener(listener);
        f.setVisible(true);


    }
}
*/




/*
    class Keyevent implements KeyListener{

        public void gameKeyPressed(int keyCode) {
            switch (keyCode) {
                case 65:
                    System.out.println("To the left");
                    break;
                case 68:
                    System.out.println("To the right");
                    break;
            }
        }
      //  public void keyPressed(java.awt.event.KeyEvent e) {
      //      gameKeyPressed(e.getKeyCode());

       // }
        public void keyTyped(java.awt.event.KeyEvent e){
            gameKeyPressed(e.getKeyCode());
        }
    }
}*/
