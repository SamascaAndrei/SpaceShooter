
import java.awt.*;

public class EnemyBullet extends Sprite{
    public void move()
    {
        this.y=y+4;
        if(this.y>750)
            this.setVisible(false);
    }
    EnemyBullet(int x,int y,Image img)
    {
        this.x=x;
        this.y=y;
        this.visible=true;
        this.image=img;
    }
}
