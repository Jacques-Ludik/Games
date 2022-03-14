
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Missile {

    public int x;
    public int y;
    private boolean destroyed;

    public Missile() {

        initMissile();
    }

    private void initMissile() {

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

        y -= 0.01;

    }


    private void resetState() {

        x = 280;
        y = 350;
    }

}
