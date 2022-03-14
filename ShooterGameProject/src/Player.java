import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player  {

    private int dx;
    public int x;
    public int y;
    public Player() {

        initPaddle();
    }

    private void initPaddle() {

      //  loadImage();
      //  getImageDimensions();

        resetState();
    }

  /*  private void loadImage() {

        var ii = new ImageIcon("src/resources/paddle.png");
        image = ii.getImage();
    }
*/
    void move() {

        x += dx;

        if (x <= 25) {

            x = 25;
        }

        if (x >= 575) {

            x = 575;
        }
    }

    void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == 65) {

            dx = -1;
        }

        if (key == 68) {

            dx = 1;
        }
    }

    void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == 65) {

            dx = 0;
        }

        if (key == 68) {

            dx = 0;
        }

    }

    private void resetState() {

        x = 280;
        y = 350;
    }
}
