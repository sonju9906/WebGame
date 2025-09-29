package game;

import java.awt.*;
import javax.swing.*;

public class Player {
    int x, y, width, height;
    int velocityY = 0;
    boolean onGround = true;
    Image image;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 40;

        // ✅ 이미지 절대 경로로 불러오기
        String path = "D:/sonny/qwer.jpg"; // D 드라이브 sonny 폴더
        ImageIcon icon = new ImageIcon(path);
        image = icon.getImage();

        if (image == null) {
            System.out.println("Player 이미지 로드 실패: " + path);
        }
    }

    public void update() {
        if (!onGround) {
            y += velocityY;
            velocityY += 1; // 중력
            if (y >= 300) {
                y = 300;
                onGround = true;
                velocityY = 0;
            }
        }
    }

    public void jump() {
        if (onGround) {
            velocityY = -15;
            onGround = false;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
