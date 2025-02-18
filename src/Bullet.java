
public class Bullet extends Sprite{

    Constants constants=new Constants();

    public void move()
    {
        this.y-=4;
        if(this.y<0)
            this.setVisible(false);

    }
    Bullet(int x,int y)
    {
        this.x=x;
        this.y=y;
        this.visible=true;
        this.image=constants.laser;
        setY(y);
        setX(x+10);
    }

}
