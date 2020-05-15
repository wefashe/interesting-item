package org.github.demo;

import java.awt.*;

/**
 * 球模型
 *
 * @author wenfs
 * @date 2020/5/15 11:39
 */
public class Ball {

    /**
     * 球心的位置
     */
    public int x, y;
    /**
     * 球的半径和质量
     */
    public final int r, m;

    /**
     * 球在x与y方向的速度
     */
    public int vx, vy;

    /**
     * 球的颜色
     */
    public final Color color;

    /**
     * 球是否是实心
     */
    public boolean isSolid = false;

    public Ball(int x, int y, int r, int m, int vx, int vy, Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.m = m;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
    }

    /**
     * 位置是否在球内
     *
     * @param x
     * @param y
     * @return
     */
    public boolean contain(int x, int y) {
        return this.contain(x, y, 0);
    }

    /**
     * 是否跟别的球相碰撞
     *
     * @param ball
     * @return
     */
    public boolean contain(Ball ball) {
        return this.contain(ball.x, ball.y, ball.r);
    }

    /**
     * 勾股定理 a²+b²=c²
     *
     * @param x
     * @param y
     * @param r
     * @return
     */
    private boolean contain(int x, int y, int r) {
        return Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) <= Math.pow(this.r - r, 2);
    }

    public void move(int minx, int miny, int maxx, int maxy) {
        // 碰壁
        if (x - r <= minx || x + r >= maxx || x - r + vx <= minx || x + r + vx >= maxx) {
            vx = -vx;
        }
        if (y - r <= miny || y + r >= maxy || y - r + vy <= miny || y + r + vy >= maxy) {
            vy = -vy;
        }

        // 移动
        x += vx;
        y += vy;
    }

}
