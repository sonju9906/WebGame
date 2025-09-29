package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L; // Serializable 경고 제거

    Timer timer;
    Player player;
    ArrayList<Obstacle> obstacles;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);

        player = new Player(50, 300);
        obstacles = new ArrayList<>();

        // 게임 진행 Timer
        timer = new Timer(20, this); // 20ms마다 액션
        timer.start();

        // 장애물 생성(2초마다)
        new Thread(() -> {
            while (true) {
                try { Thread.sleep(2000); } 
                catch (InterruptedException e) { e.printStackTrace(); }
                obstacles.add(new Obstacle(500, 300, 40, 40));
            }
        }).start();
    }

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        for (Obstacle o : obstacles) {
            o.draw(g);
        }
    }

    
    public void actionPerformed(ActionEvent e) {
        player.update();
        ArrayList<Obstacle> toRemove = new ArrayList<>();
        for (Obstacle o : obstacles) {
            o.update();

            if (o.x + o.width < 0) toRemove.add(o);

            if (player.getBounds().intersects(o.getBounds())) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "게임 오버!");
                System.exit(0);
            }
        }
        obstacles.removeAll(toRemove);
        repaint();
    }

    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) player.jump();
    }

     public void keyReleased(KeyEvent e) {}
     public void keyTyped(KeyEvent e) {}
}
