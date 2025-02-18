import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GamePanel extends JPanel {
    Constants constants=new Constants();
    Timer timer=new Timer(5,new TimeTravel(this));
    Ship ship=new Ship();
    Bullet bullet=new Bullet(0,0);
    List<AlienShip> enemyList =new ArrayList<>();
    JLabel label=new JLabel();
    int transitionTime =1000000;

    int killCount =0;
    JButton menu=new JButton("Main Menu");
    int gameState =0;
    int time=1;
    int y=-1250;
     public GamePanel()
     {
         this.setVisible(false);
         initialize();
         setPreferredSize(new Dimension(900,750));
         addKeyListener(new KeyboardListener(this));
         setFocusable(true);
     }
    void restart()
    {
        ship=new Ship();
        for(AlienShip a:enemyList)
            if(a.bullets.size()>0)
            {
                a.bullets.subList(0,a.bullets.size()).clear();
            }
        if (enemyList.size() > 0) {
            enemyList.subList(0, enemyList.size()).clear();
        }
        time=1;
        gameState =0;
        killCount=0;
        transitionTime=100000;
    }
    void initialize()
    {
        label.setBounds(50,700,200,30);
        this.add(label);
        label.setFont(new Font("Arial",Font.BOLD,20));
        label.setForeground(Color.RED);
        this.setLayout(null);
        menu.setBounds(400,650,100,30);
        this.add(menu);
        menu.setVisible(false);
        menu.addActionListener(e-> {
            this.setVisible(false);
            this.restart();
        });
    }
    void drawEnemy(Graphics g)
    {
        for(AlienShip enemy: enemyList)
        if(enemy.isVisible())
        g.drawImage(enemy.getImage(),enemy.getX(),enemy.getY(),this);
    }
    void drawPlayer(Graphics g)
    {
        if(ship.isVisible())
        g.drawImage(ship.getImage(),ship.getX(),ship.getY(),this);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(y==1900)
            y=0;
        if(y>=0)
            g.drawImage((new ImageIcon("Resources/BigBackground.png").getImage()), 0, -1900 + y, null);
            g.drawImage((new ImageIcon("Resources/BigBackground.png").getImage()), 0, y++, null);
        doDrawing(g);
    }
   void drawBullet(Graphics g)
   {
       for(Bullet b:ship.bullets)
       {
           g.drawImage(b.getImage(),b.getX(),b.getY(),this);
       }

   }
   void gameOver(Graphics g)
   {
           menu.setVisible(true);
       g.drawImage(new ImageIcon("Resources/GameOver.png").getImage(),200,200,this);
   }
   void victory(Graphics g)
   {
       g.drawImage(new ImageIcon("Resources/vic1.png").getImage(),150,175,this);
       menu.setVisible(true);
   }
   void drawExplosion(Graphics g)
   {
           g.drawImage(new ImageIcon("Resources/explosion.png").getImage(),ship.x,ship.y,this);
   }
   void drawEnemyBullet(Graphics g)
   {
       for(AlienShip enemy: enemyList)
       for(EnemyBullet e:enemy.bullets)
       {
           g.drawImage(e.getImage(),e.getX(),e.getY(),this);
       }

   }
   void drawWarning(Graphics g)
   {
       g.drawImage(new ImageIcon("Resources/warning.png").getImage(),0,100,this);
   }

    /**
     * This method draws all objects on the frame.
     * @param g
     */
    void doDrawing(Graphics g)
    {
        drawPlayer(g);
        drawBullet(g);
        drawEnemy(g);
        drawEnemyBullet(g);
        if(time- transitionTime >0&&time- transitionTime <500)
            drawWarning(g);
        if(gameState ==-1)
        drawExplosion(g);
        if(gameState ==-1) {
            gameOver(g);
        }
        if(gameState ==2)
        {
            victory(g);
        }
        label.setText("Ship HP:"+ship.hp);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * This method spawns enemies on screen.
     */
    public void spawn()
    {
        Random rand=new Random();
        int x=rand.nextInt(900);
        if(time%200==0&&gameState==0&&enemyList.size()<10) {
            enemyList.add(new AlienShip(x,0,39,49,constants.enemy1,10));
        }
        if(time-transitionTime==450)
        {
            enemyList.add(new AlienShip(300,-400,478,280,constants.boss,500));
        }
    }

    /**
     * The game has 4 game states.This method changes them when necessary.
     */
    void setGameState()
    {
        if(killCount==51)
            gameState=2;
        if(killCount ==50 && gameState ==0) {
            gameState = 1;
            enemyList.subList(0,enemyList.size()).clear();
            transitionTime =time;
        }
        if(ship.hp<=0)
            gameState =-1;
    }

    /**
     * This method makes each object on screen move.
     */
    void objectsMove()
    {
        this.ship.move();
        this.bullet.move();
        if(time%3==0)
            for(AlienShip enemy: enemyList) {
                if(enemy.r.getWidth()>100)
                    enemy.move2();
                else
                    enemy.move();
            }
        for(AlienShip enemy: enemyList)
            for(EnemyBullet e:enemy.bullets) {
                e.move();
            }
        for(Bullet b:ship.bullets) {
            b.move();
        }
    }

    /**
     * This method adds bullets and removes them when needed.
     */
    public void bulletManagement()
    {
        if(ship.shoot&&time%10==0)
            ship.bullets.add(new Bullet(this.ship.getX(),this.ship.getY()));
        for(AlienShip enemy: enemyList)
            if (!(enemy.r.getWidth() > 100)) {
                if (time % 100 == 0 && enemy.isVisible()) {
                    enemy.bullets.add(new EnemyBullet(enemy.getX() + (int) enemy.r.getWidth() / 2, enemy.getY() + (int) enemy.r.getHeight(), constants.bulletstar));
                }
            }
            else {
                if (time % 50 == 0 && enemy.isVisible())
                {
                    enemy.bullets.add(new EnemyBullet(enemy.getX() + (int) enemy.r.getWidth() / 2-20, enemy.getY() + (int) enemy.r.getHeight(), constants.bullet));
                    enemy.bullets.add(new EnemyBullet(enemy.getX() + (int) enemy.r.getWidth() / 2+20, enemy.getY() + (int) enemy.r.getHeight(), constants.bullet));
                    enemy.bullets.add(new EnemyBullet(enemy.getX() + (int) enemy.r.getWidth() / 2, enemy.getY() + (int) enemy.r.getHeight() , constants.bullet));
                    enemy.bullets.add(new EnemyBullet(enemy.getX() + (int) enemy.r.getWidth() / 2+150, enemy.getY() + (int) enemy.r.getHeight()-20, constants.bullet));
                    enemy.bullets.add(new EnemyBullet(enemy.getX() + (int) enemy.r.getWidth() / 2-150, enemy.getY() + (int) enemy.r.getHeight()-20, constants.bullet));
                }
            }
        for(AlienShip enemy: enemyList)
            for(int i=enemy.bullets.size()-1;i>=0;i--)
            {
                if(!enemy.bullets.get(i).isVisible())
                    enemy.bullets.remove(i);
            }
        for(int i=ship.bullets.size()-1;i>=0;i--)
        {
            if(!ship.bullets.get(i).isVisible())
                ship.bullets.remove(i);
        }
    }

    /**
     * This method handles the unit collision in game.
     */
    public void collisionDetect()
    {
        for(AlienShip enemy: enemyList)
            enemy.r=new Rectangle(enemy.x,enemy.y,(int)enemy.r.getWidth(),(int)enemy.r.getHeight());
        Rectangle r=new Rectangle(ship.x,ship.y,32,28);
        for(AlienShip enemy: enemyList)
            for(EnemyBullet e:enemy.bullets) {
                if (r.intersects(e.x, e.y, 23, 20)) {
                    e.setVisible(false);
                    ship.hp--;
                }
            }

        for(AlienShip enemy: enemyList)
            if(enemy.isVisible())
                for(Bullet b: ship.bullets)
                    if(enemy.r.intersects(b.x,b.y,12,22))
                    {
                        b.setVisible(false);
                        enemy.hp--;
                    }
    }

    /**
     * This method updates everything in the game everytime the Timer updates(5ms).
     */
    public void update()
    {
        if(gameState ==-1)
            return;
        time++;
        setGameState();
        spawn();

        ship.shoot=false;
        ship.left=ship.right=ship.down=ship.up=false;
        for(int i:ship.keys)
        {
            if(i==KeyEvent.VK_LEFT)
                ship.left=true;
            if(i==KeyEvent.VK_RIGHT)
                ship.right=true;
            if(i==KeyEvent.VK_UP)
                ship.up=true;
            if(i==KeyEvent.VK_DOWN)
                ship.down=true;
            if(i==KeyEvent.VK_SPACE)
                ship.shoot=true;
        }
        objectsMove();
        bulletManagement();
        collisionDetect();

           int dif=enemyList.size();
        enemyList.removeIf(enemy -> enemy.hp <= 0);
        killCount = killCount +dif-enemyList.size();

    }
    public void loopGame()
    {
            update();
            repaint();
    }
    public void keyReleased(KeyEvent e)
    {
      ship.keyReleased(e);
    }
    public void keyPressed(KeyEvent e)
    {
        ship.keyPressed(e);

       int key=e.getKeyCode();
        if(key==KeyEvent.VK_SPACE)
                ship.keys.add(key);
    }
}
