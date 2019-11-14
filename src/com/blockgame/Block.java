package com.blockgame;

import java.io.*;

/**
 *
 * @author ZHAOJIE644
 * @date 2019-10-25
 * @since 1.0.0
 */
public class Block implements Serializable{

    int block[][][];

    private int col;

    private int row;

    private int cur;

    public Block copy(){
        Block deepClone = null;
        try {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
            baos = new ByteArrayOutputStream();

            oos = new ObjectOutputStream(baos);

        oos.writeObject(this);
            bais = new ByteArrayInputStream(baos
                    .toByteArray());
            ois = new ObjectInputStream(bais);
            deepClone = (Block)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deepClone;
    }


    public static Block newRandomBlock(){
        int types = 6;
        int i = (int) (Math.random() * types);
        Block block = null;
        switch (i){
            case 0:
                block = newIblock();
                break;
            case 1:
                block = newLblock();
                break;
            case 2:
                block = newZblock();
                break;
            case 3:
                block  = newTblock();
                break;
            case 4:
                block = newRLblock();
                break;
            case 5:
                block = newRZblock();
                break;
            default:
                block = newTblock();
                break;
        }
        return block;
    }

    public static Block newIblock(){
        Block iblock = new Block();
        iblock.block = new int[2][4][4];
        iblock.block[0]=new int[][]{{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        iblock.block[1]=new int[][]{{0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0}};
        return iblock;
    }

    public static Block newLblock(){
        Block lblock = new Block();
        lblock.block = new int[4][3][3];
        lblock.block[0]=new int[][]{{1,0,0},{1,0,0},{1,1,0}};
        lblock.block[1]=new int[][]{{0,0,0},{0,0,1},{1,1,1}};
        lblock.block[2]=new int[][]{{0,1,1},{0,0,1},{0,0,1}};
        lblock.block[3]=new int[][]{{1,1,1},{1,0,0},{0,0,0}};
        return lblock;
    }
    public static Block newRLblock(){
        Block lblock = new Block();
        lblock.block = new int[4][3][3];
        lblock.block[0]=new int[][]{{1,1,0},{0,1,0},{0,1,0}};
        lblock.block[1]=new int[][]{{0,0,1},{1,1,1},{0,0,0}};
        lblock.block[2]=new int[][]{{0,1,0},{0,1,0},{0,1,1}};
        lblock.block[3]=new int[][]{{0,0,0},{1,1,1},{1,0,0}};
        return lblock;
    }


    public static Block newZblock(){
        Block zblock = new Block();
        zblock.block = new int[2][3][3];
        zblock.block[0]=new int[][]{{1,0,0},{1,1,0},{0,1,0}};
        zblock.block[1]=new int[][]{{0,0,0},{0,1,1},{1,1,0}};
        return zblock;
    }

    public static Block newRZblock(){
        Block zblock = new Block();
        zblock.block = new int[2][3][3];
        zblock.block[0]=new int[][]{{0,1,0},{1,1,0},{1,0,0}};
        zblock.block[1]=new int[][]{{1,1,0},{0,1,1},{0,0,0}};
        return zblock;
    }


    public static Block newTblock(){
        Block zblock = new Block();
        zblock.block = new int[4][3][3];
        zblock.block[0]=new int[][]{{1,1,1},{0,1,0},{0,0,0}};
        zblock.block[1]=new int[][]{{0,0,1},{0,1,1},{0,0,1}};
        zblock.block[2]=new int[][]{{0,0,0},{0,1,0},{1,1,1}};
        zblock.block[3]=new int[][]{{1,0,0},{1,1,0},{1,0,0}};
        return zblock;
    }


    public void left(int num){
        this.col = col - 1;
    }

    public void right(int num){
        this.col = col + 1;
    }

    public void down(int num){
        this.row = row + 1;
    }
    public void up(int num){
        this.row = row - 1;
    }


    public int[][][] getBlock() {
        return block;
    }

    public void setBlock(int[][][] block) {
        this.block = block;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void roateLeft(){
        cur = (cur-1) % block.length;
    }

    public void roateRigth(){
        cur = (cur+1) % block.length;
    }


    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    public static void main(String[] args) {
        Block ib = Block.newIblock();
        System.out.println((ib.block.length));
    }
}