package org.github.demo;

import javax.swing.*;

/**
 * 窗口
 *
 * @author wenfs
 * @date 2020/5/15 13:12
 */
public class MyJFrame extends JFrame {

    private MyCanvas canvas;

    public MyJFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        // 渲染画布
        this.canvas = new MyCanvas(canvasWidth, canvasHeight);
        setContentPane(canvas);
        pack();

        // 显示窗口
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void render(Ball[] balls) {
        canvas.setBalls(balls);
        repaint();
    }

}
