import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The only frame of the applicaiton. It has 2 panels: MenuPanel and GamePanel.
 */
public class GameFrame extends JFrame {
    JPanel swy;
    Constants constants=new Constants();
    JButton k=new JButton("New Game");
    public GameFrame()
    {
         GamePanel g = new GamePanel();
         swy=new MenuPanel(g);
        swy.setLayout(null);
        k.setBounds(400,200,100,30);
        swy.setPreferredSize(new Dimension(900,750));
       swy.add(k);
       add(swy);
        k.addActionListener(e->add(g));
        k.addActionListener(e->swy.setVisible(false));
        k.addActionListener(e-> g.setVisible(true));
        k.addActionListener(e->this.revalidate());
        swy.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }
            @Override
            public void componentHidden(ComponentEvent e) {
                g.grabFocus();
                g.requestFocusInWindow();
                g.requestFocus();
                g.timer.start();
                g.setVisible(true);
                g.menu.setVisible(false);
            }

        });
        g.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {
             g.timer.stop();
             g.restart();
             swy.setVisible(true);
            }
        });
       setTitle("Space Shooter");
       setIconImage(constants.ship);
       pack();
       setResizable(false);
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
    }
}
