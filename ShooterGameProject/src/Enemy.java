import javax.swing.ImageIcon;

public class Enemy{

   // private boolean destroyed;
    public int x, y;
    public Enemy() {

        initEnemy(x, y);
    }

    private void initEnemy(int x, int y) {

        this.x = 100;
        this.y = 100;

    //    destroyed = false;

    //    loadImage();
    //    getImageDimensions();
    }

   /* private void loadImage() {

        var ii = new ImageIcon("src/resources/brick.png");
        image = ii.getImage();
    }*/
}
