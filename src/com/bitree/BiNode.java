package com.bitree;

/**
 *
 * @author ZHAOJIE644
 * @date 2019-10-29
 * @since 1.0.0
 */
public class BiNode {
    int data;

    int high;

    int index;

    BiNode left;
    BiNode right;

    public BiNode(){

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public BiNode(int data) {
        this.data = data;
    }

    public BiNode getLeft() {
        return left;
    }

    public void setLeft(BiNode left) {
        this.left = left;
    }

    public BiNode getRight() {
        return right;
    }

    public void setRight(BiNode right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
