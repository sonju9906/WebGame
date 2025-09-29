package game;

import java.awt.*;
import javax.swing.*;

public class Obstacle {
    int x, y, width, height;
    Image image;

    public Obstacle(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;

        // ✅ 이미지 절대 경로로 불러오기
        String path = "D:/sonny/asdf.jpg"; // D 드라이브 sonny 폴더
        ImageIcon icon = new ImageIcon(path);
        image = icon.getImage();

        if (image == null) {
            System.out.println("Obstacle 이미지 로드 실패: " + path);
        }
    }

    public void update() {
        x -= 5; // 이동 속도
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
