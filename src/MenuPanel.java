import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    GamePanel g;
    MenuPanel(GamePanel g)
    {
        this.g=g;
        initialize();
    }

    void initialize(){
        this.setPreferredSize(new Dimension(900,750));

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage((new ImageIcon("Resources/mainbg.jpg").getImage()), 0, 0, null);
    }
}
