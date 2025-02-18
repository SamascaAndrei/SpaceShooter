import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class that loops the game panel.
 */
public class TimeTravel implements ActionListener {

    GamePanel gamePanel;

    TimeTravel(GamePanel gamePanel)
    {
        this.gamePanel=gamePanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       gamePanel.loopGame();
    }
}
