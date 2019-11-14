package com.blockgame;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author ZHAOJIE644
 * @date 2019-10-25
 * @since 1.0.0
 */
public class GameContext {

    private Background background = new Background();

    private Block curBlock = Block.newRandomBlock();

    private Block nextBlock = Block.newRandomBlock();

    public static final int STATE_DOWN = 1;

    /**
     * 消除
     */
    public static final int STATE_CLEAR = 2;

    /**
     * 重新移动
     */
    public static final int STATE_REMOVE_BACK = 3;

    public int curState = STATE_DOWN;

    public boolean nextBlockFlag = true;

    public void next(){

        switch (curState) {
            case STATE_DOWN:
                boolean db = this.down();
                if (!db) {
                    fillBackgroud();
                    //genNextBlock();
                    //行满了判断是否能够消除
                    List<Integer> clearLines = backClearLine();
                    if (clearLines.size() > 0) {
                        curState = STATE_CLEAR;
                    }else{
                        genNextBlock();
                    }
                }
                break;
            case STATE_CLEAR:
                if (backClearLine().size() > 0) {
                    backClear();
                } else{
                    curState = STATE_REMOVE_BACK;
                }
                break;
            case STATE_REMOVE_BACK:
                if(backRemoveLine().size()>0){
                    backRemove();
                }else{
                    curState = STATE_DOWN;
                    genNextBlock();
                }
                break;
            default:
                break;
        }
    }


    public void backClear(){
        List<Integer> clearLines = backClearLine() ;
        for (Integer clearLine : clearLines) {
            for(int j = 0;j<background.getWidth();j++) {
                background.getBack()[clearLine][j] = 0;
            }
        }
    }


    public void backRemove(){
       int line = background.getHeight()-1;
       int count = background.getHeight();
        while(line >=0 && count >=0){
            boolean flag = false;
            count -- ;
            for(int j = 0;j<background.getWidth();j++){
                if(background.getBack()[line][j] == 1){
                    flag =true;
                }
            }
            if(flag){
                line --;
            }else {
                for (int i = line; i >= 0; i--) {
                    for (int j = 0; j < background.getWidth(); j++) {
                        if (i - 1 >= 0) {
                            background.getBack()[i][j] = background.getBack()[i - 1][j];
                        } else {
                            background.getBack()[i][j] = 0;
                        }
                    }
                }
            }
        }
    }
    public List<Integer> backRemoveLine(){
        List<Integer> result = new ArrayList<Integer>();
        boolean empty = true;
        int startLine = -1;
        for(int i =0 ;i<background.getHeight();i++){
            boolean flag = true;
            for(int j = 0;j<background.getWidth();j++){
                if(background.getBack()[i][j] == 1){
                    flag =false;
                    startLine = i;
                }
            }
            if(startLine >= 0 && flag){
                result.add(i);
            }
        }
            return result;
    }




    public List<Integer> backClearLine(){
        List<Integer> result = new ArrayList<Integer>();
        for(int i =0 ;i<background.getHeight();i++){
            boolean flag = true;
            for(int j = 0;j<background.getWidth();j++){
                if(background.getBack()[i][j] == 0){
                    flag =false;
                }
            }
            if(flag){
                result.add(i);
            }
        }
        return result;
    }


    public void left(){
        Block copy = curBlock.copy();
        int[][] b = copy.getBlock()[copy.getCur()];
        boolean flag = true;
        copy.left(1);
        for(int i = 0;i<b.length;i++){
            for(int j = 0 ;j<b.length;j++){
                if(b[i][j] == 1){
                    if(copy.getRow()+i>= this.background.getHeight() ||copy.getRow() <0 ){
                        flag = false;
                        break;
                     }

                    if(copy.getCol()+j>= this.background.getWidth() ||copy.getCol() +j <0 ){
                        flag = false;
                        break;
                    }

                    //尝试下降一格，如果各自被占用，则退回
                    if(background.getBack()[copy.getRow()+i][copy.getCol()+j] == 1){
                        flag = false;
                        break;
                    }

                }
            }
        }
        if(flag) {
            curBlock.left(1);
        }
    }


    public void right(){
        Block copy = curBlock.copy();
        int[][] b = copy.getBlock()[copy.getCur()];
        boolean flag = true;
        copy.right(1);
        for(int i = 0;i<b.length;i++){
            for(int j = 0 ;j<b.length;j++){
                if(b[i][j] == 1){
                    if(copy.getRow()+i>= this.background.getHeight() ||copy.getRow()+i <0 ){
                        flag = false;
                        break;
                    }

                    if(copy.getCol()+j>= this.background.getWidth() ||copy.getCol() +j <0 ){
                        flag = false;
                        break;
                    }

                    //尝试下降一格，如果各自被占用，则退回
                    if(background.getBack()[copy.getRow()+i][copy.getCol()+j] == 1){
                        flag = false;
                        break;
                    }

                }
            }
        }
        if(flag) {
            curBlock.right(1);
        }
    }


    public void rotateLeft(){
        Block copy = curBlock.copy();
        int[][] b = copy.getBlock()[copy.getCur()];
        boolean flag = true;
        copy.roateLeft();
        for(int i = 0;i<b.length;i++){
            for(int j = 0 ;j<b.length;j++){
                if(b[i][j] == 1){
                    if(copy.getRow()+i>= this.background.getHeight() ||copy.getRow()+i <0 ){
                        flag = false;
                        break;
                    }

                    if(copy.getCol()+j>= this.background.getWidth() ||copy.getCol() +j <0 ){
                        flag = false;
                        break;
                    }

                    //尝试下降一格，如果各自被占用，则退回
                    if(background.getBack()[copy.getRow()+i][copy.getCol()+j] == 1){
                        flag = false;
                        break;
                    }

                }
            }
        }
        if(flag) {
            curBlock.roateLeft();
        }
    }

    public void rotateRight(){
        Block copy = curBlock.copy();
        boolean flag = true;
        copy.roateRigth();
        int[][] b = copy.getBlock()[copy.getCur()];
        for(int i = 0;i<b.length;i++){
            for(int j = 0 ;j<b.length;j++){
                if(b[i][j] == 1){
                    if(copy.getRow()+i>= this.background.getHeight() ||copy.getRow()+i <0 ){
                        flag = false;
                        break;
                    }

                    if(copy.getCol()+j>= this.background.getWidth() ||copy.getCol() +j <0 ){
                        flag = false;
                        break;
                    }

                    //尝试下降一格，如果各自被占用，则退回
                    if(background.getBack()[copy.getRow()+i][copy.getCol()+j] == 1){
                        flag = false;
                        break;
                    }

                }
            }
        }
        if(flag) {
            curBlock.roateRigth();
        }
    }


    public boolean down(){
        Block copy = curBlock.copy();
        int[][] b = copy.getBlock()[copy.getCur()];
        boolean flag = true;
        copy.down(1);
        for(int i = 0;i<b.length;i++){
            for(int j = 0 ;j<b.length;j++){
                if(b[i][j] == 1){
                    if(copy.getRow()+i>= this.background.getHeight() ||copy.getRow()+i <0 ){
                        flag = false;
                        break;
                    }

                    if(copy.getCol()+j>= this.background.getWidth() ||copy.getCol() +j <0 ){
                        flag = false;
                        break;
                    }

                    //尝试下降一格，如果各自被占用，则退回
                    if(background.getBack()[copy.getRow()+i][copy.getCol()+j] == 1){
                        flag = false;
                        break;
                    }

                }
            }
        }
        if(flag) {
            curBlock.down(1);
        }
        return flag;
    }


    public void clear(){

    }


    public void fillBackgroud(){
        int[][] b = curBlock.getBlock()[curBlock.getCur()];
        for(int i = 0;i<b.length;i++){
            for(int j = 0 ;j<b.length;j++){
                if(b[i][j] == 1){
                    background.getBack()[curBlock.getRow()+i][curBlock.getCol()+j] = 1;
                }
            }
        }
        nextBlockFlag = true;
    }

    public void genNextBlock(){
        curBlock = Block.newRandomBlock();
    }



    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Block getCurBlock() {
        return curBlock;
    }

    public void setCurBlock(Block curBlock) {
        this.curBlock = curBlock;
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(Block nextBlock) {
        this.nextBlock = nextBlock;
    }
}
