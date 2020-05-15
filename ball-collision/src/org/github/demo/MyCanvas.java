package org.github.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * 画布
 *
 * @author wenfs
 * @date 2020/5/15 13:15
 */
public class MyCanvas extends JPanel {

    private Ball[] balls;

    public MyCanvas(int canvasWidth, int canvasHeight) {
        // 开启双缓存画布
        super(true);
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    public void setBalls(Ball[] balls) {
        this.balls = balls;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // 抗锯齿
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // 具体绘制圆
        for (Ball ball : balls) {
            drawCircle(g2d, ball);
        }
    }

    /**
     * 绘制圆
     *
     * @param g2d
     * @param ball 圆的对象
     */
    public void drawCircle(Graphics2D g2d, Ball ball) {
        g2d.setColor(ball.color);
        if (ball.isSolid) {
            fillCirCle(g2d, ball.x, ball.y, ball.r);
        } else {
            strokeCirCle(g2d, ball.x, ball.y, ball.r);
        }
    }

    /**
     * 绘制空心圆
     *
     * @param g2d
     * @param x   圆心x位置
     * @param y   圆心y位置
     * @param r   圆心r半径
     */
    private void strokeCirCle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D e2d = new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(e2d);
    }

    /**
     * 绘制实心圆
     *
     * @param g2d
     * @param x   圆心x位置
     * @param y   圆心y位置
     * @param r   圆心r半径
     */
    private void fillCirCle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D e2d = new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(e2d);
    }
}
