import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Ship extends Sprite {
    Constants constants=new Constants();
    int hp;
    List<Bullet> bullets=new ArrayList<>();
    List<Integer>keys=new ArrayList<>();
    boolean right=false;
    boolean left=false;
    boolean up=false;
    boolean down=false;
    boolean shoot=false;

    public Ship() {
        initialize();
    }

    void initialize()
    {
        image =constants.ship;
        setX(430);
        setY(650);
        hp=100;
    }

    public void move()
    {

           dx=dy=0;
         if(right&&up)
         {
             dx=2;
             dy=-2;
         }
         else if(right&&down)
             {
                 dx=2;
                 dy=2;
             }
             else if(left&&up)
             {
                 dx=-2;
                 dy=-2;
             }
             else if(left&&down)
             {
                 dx=-2;
                 dy=2;
             }
             else if(up)
             {
                 dy=-2;
             }
             else if(down)
             {
                 dy=2;
             }
             else if(left) {
                 dx = -2;
             }
             else if(right) {
                 dx = 2;
             }
             x=x+dx;
             y=y+dy;
        if(x<0)
            x=0;
        if(x>868)
            x=868;
        if(y<0)
            y=0;
        if(y>722)
            y=722;
    }

    public void keyPressed(KeyEvent e)
    {
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_LEFT)
            keys.add(key);
            //dx=-2;
        if(key==KeyEvent.VK_RIGHT)
            keys.add(key);
            //dx=+2;
        if(key==KeyEvent.VK_UP)
            keys.add(key);
            //dy=-2;
        if(key==KeyEvent.VK_DOWN)
            keys.add(key);
            //dy=+2;
        if(key==KeyEvent.VK_SPACE)
            keys.add(key);
    }
    public void keyReleased(KeyEvent e)
    {
        int key=e.getKeyCode();
            for(int i=keys.size()-1;i>=0;i--)
            {
                if(keys.get(i)==key)
                    keys.remove(keys.get(i));
            }
    }
}
