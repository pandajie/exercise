package com.blockgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * TODO: 请添加描述
 *
 * @author ZHAOJIE644
 * @date 2019-10-25
 * @since 1.0.0
 */
public class GamePanel extends JPanel{

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    GameContext gameContext = new GameContext();

    int x = 0;
    int y = 0;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGameContext(g);
    }
    GamePanel(){
        Background background = gameContext.getBackground();
        this.setPreferredSize(new Dimension(background.getWidth()*Constant.CELL_LENTH,background.getHeight()*Constant.CELL_LENTH));
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    gameContext.left();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    gameContext.right();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    gameContext.rotateRight();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    gameContext.down();
                }
            }



            @Override
            public void keyReleased(KeyEvent e) {

            }});
        //setBorder(new MatteBorder(5,5,5,5,Color.red));
    }

    public void drawGameContext(Graphics g){
        for(int i =0 ;i<gameContext.getBackground().getHeight();i++){
            for(int j =0;j<gameContext.getBackground().getWidth();j++){
                if(gameContext.getBackground().getBack()[i][j] == 1){
                    g.fill3DRect((j)*Constant.CELL_LENTH,(i)*Constant.CELL_LENTH,1*Constant.CELL_LENTH,1*Constant.CELL_LENTH,false);
                }
            }
        }


        Block block = gameContext.getCurBlock();
        if(block!=null) {
            int col = block.getCol();
            int row = block.getRow();
            int[][] b = block.getBlock()[block.getCur()];
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    if (b[i][j] == 1) {
                        g.fill3DRect((col + j) * Constant.CELL_LENTH, (row + i) * Constant.CELL_LENTH, 1 * Constant.CELL_LENTH, 1 * Constant.CELL_LENTH, false);
                    }
                }
            }
        }

    }





    public void run() {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                gameContext.next();
                repaint();
            }
        },0, 200,TimeUnit.MILLISECONDS);
    }


    public void stop(){
        executor.shutdown();
    }

}
