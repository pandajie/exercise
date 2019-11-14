package com.blockgame;

/**
 * TODO: 请添加描述
 *
 * @author ZHAOJIE644
 * @date 2019-10-25
 * @since 1.0.0
 */
public class Background {

    int[][] back;

    Background(){
        this.height = 40;
        this.width = 10;
        back = new int[this.height][this.width];
    }



    private int height;

    private int width;

    public int[][] getBack() {
        return back;
    }

    public void setBack(int[][] back) {
        this.back = back;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
