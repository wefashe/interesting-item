package org.github.demo;

import javafx.beans.property.SimpleBooleanProperty;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 主测试类
 *
 * @author wenfs
 * @date 2020/5/15 13:23
 */
public class Test {
    public static void main(String[] args) {

        int canvasWidth = 500;
        int canvasHeight = 500;

        int num = 2;
//        int r = 50;
        // 准备球的初始化数据
        Ball[] balls = new Ball[num];
        for (int i = 0; i < num; i++) {
            int r = (int) (Math.random() * 40) + 20;
            int x = (int) (Math.random() * (canvasWidth - 2 * r) + r);
            int y = (int) (Math.random() * (canvasWidth - 2 * r) + r);
            int vx = (int) (Math.random() * 11 - 5);
            int vy = (int) (Math.random() * 11 - 5);
            int m = (int) (Math.random() * 100) + 10;
            Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
            balls[i] = new Ball(x, y, r, m, vx, vy, color);
        }

        EventQueue.invokeLater(() -> {
            SimpleBooleanProperty isMove = new SimpleBooleanProperty(true);
            MyJFrame frame = new MyJFrame("模拟小球碰撞", canvasWidth, canvasHeight);
            // 键盘监听 按空格停止运动
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (' ' == e.getKeyChar()) {
                        isMove.set(!isMove.get());
                    }
                }
            });

            // 鼠标监听 按键绘制实心圆
            frame.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    e.translatePoint(0, -(frame.getBounds().height - canvasWidth));
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        Point point = e.getPoint();
                        for (Ball ball : balls) {
                            if (ball.contain(point.x, point.y)) {
                                ball.isSolid = !ball.isSolid;
                            }
                        }
                    }
                }
            });
            new Thread(() -> {
                while (true) {
                    frame.render(balls);

                    pause(20);

                    if (isMove.get()) {
                        for (Ball ball1 : balls) {
                            for (Ball ball2 : balls) {
                                if (ball1 != ball2) {
                                    if (ball1.contain(ball2)) {
                                        System.out.println("碰撞了");
                                        if (ball1.x >= ball2.x) {
                                            ball1.x++;
                                            while (ball1.contain(ball2)) {
                                                ball1.x++;
                                            }
                                        } else {
                                            ball2.x++;
                                            while (ball1.contain(ball2)) {
                                                ball2.x++;
                                            }
                                        }
                                        ball1.vx = -ball1.vx;
                                        ball1.vy = -ball1.vy;
                                        ball2.vx = -ball2.vx;
                                        ball2.vy = -ball2.vy;
                                    }
                                }
                            }
                            ball1.move(0, 0, canvasWidth, canvasHeight);
                        }
                    }
                }
            }).start();
        });

    }

    /**
     * 刷新间隔
     *
     * @param millis
     */
    private static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
