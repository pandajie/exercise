package com.bitree;

/**
 *
 * @author ZHAOJIE644
 * @date 2019-11-5
 * @since 1.0.0
 */
public class RedBlackTreeNode extends BiNode {
    /**
     * 默认节点为红色
     */
    public static final int COLOR_BLACK = 1;

    public static final int COLOR_RED = 0;

    private int color = 0;

    private RedBlackTreeNode parent;

    private RedBlackTreeNode left;

    private RedBlackTreeNode right;


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static int getColorBlack() {
        return COLOR_BLACK;
    }

    public static int getColorRed() {
        return COLOR_RED;
    }

    public RedBlackTreeNode getParent() {
        return parent;
    }

    public void setParent(RedBlackTreeNode parent) {
        this.parent = parent;
    }

    @Override
    public RedBlackTreeNode getLeft() {
        return left;
    }

    public void setLeft(RedBlackTreeNode left) {
        this.left = left;
    }

    @Override
    public RedBlackTreeNode getRight() {
        return right;
    }

    public void setRight(RedBlackTreeNode right) {
        this.right = right;
    }
}
