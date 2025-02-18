
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AlienShip extends Sprite{
    boolean wayX;
    boolean wayY;
    int hp;
    Rectangle r;
    List<EnemyBullet> bullets=new ArrayList<>();


    AlienShip(int x,int y,int sizex,int sizey,Image img,int hp)
    {
        this.setVisible(true);
        this.hp=hp;
        image=img;
        this.x=x;
        this.y=y;
        wayX=true;
        r=new Rectangle(x,y,sizex,sizey);
    }
    public void move(){
        if(wayX)
        setX(x+2);
        else
            setX(x-2);
        if(x>700)
            wayX=false;
        if(x<0)
            wayX=true;
        if(wayY)
            y=y+2;
        else
            y=y-2;
        if(y<0)
            wayY=true;
        if(y>200)
            wayY=false;
    }
    void move2()
    {
        if(y<0) {
            y = y + 1;
            return;
        }
        if(x>420)
            wayX=false;
        if(x<0)
            wayX=true;
        if(wayX)
            x=x+2;
        else
            x=x-2;
    }
}
