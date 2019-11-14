package com.bitree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * TODO: 请添加描述
 *
 * @author ZHAOJIE644
 * @date 2019-10-29
 * @since 1.0.0
 */
public class BiTreePanel extends JPanel {

    public static final double lineHeight = 10;

    public static final double width = 5;

    public static final double spacing = 1;

    public static final int  E = 10;

    public int h  = 0;

    public BiNodeOval[] biNodeOvalFull ;

    private BiTree biTree = BiTree.defaultTree2();

    ArrayList<BiNode> biNodeList = new ArrayList<BiNode>();

    ArrayList<Line> lines = new ArrayList<Line>();

    public void solve() {
        /**
         * 遍历节点
         */
        Deque<BiNode> queue = new LinkedList<BiNode>();
        queue.push(biTree.getRoot());
        while (!queue.isEmpty()) {
            BiNode node = queue.poll();
            if(node.getHigh()>h){
                h = node.getHigh();
            }
            biNodeList.add(node);
            if(node.getLeft()!=null){
                BiNode left = node.getLeft();
                left.setHigh(node.getHigh()+1);
                left.setIndex(2*node.getIndex()+1);
                queue.push(left);
            }
            if(node.getRight()!=null){
                BiNode right = node.getRight();
                right.setHigh(node.getHigh()+1);
                right.setIndex(2*node.getIndex()+2);
                queue.push(right);
            }
        }
        calulatePosition();
    }



    public void calulatePosition(){
        /**
         * 构造完全二叉树
         */
        biNodeOvalFull = new BiNodeOval[1<<(h+1)];
        for (BiNode biNode : biNodeList) {
            int index = biNode.getIndex();
            BiNodeOval oval = new BiNodeOval();
            oval.setBiNode(biNode);
            biNodeOvalFull[index] = oval;
        }
        int ch = h;
        while(ch>=0) {
            for (int i = (1 << ch) - 1; i <= (1 << (ch + 1)) - 2; i++) {
                int offset = i - (1 << ch) + 1;
                if (ch == h) {
                    //最底层的节点均匀间隔space*E
                    int x = (int) (offset * (width + spacing) * E);
                    int y = (int) ((ch) * (width + lineHeight) * E);
                    if (biNodeOvalFull[i] != null) {
                        biNodeOvalFull[i].setX(x);
                        biNodeOvalFull[i].setY(y);
                    } else {
                        BiNodeOval oval = new BiNodeOval();
                        oval.setX(x);
                        oval.setY(y);
                        biNodeOvalFull[i] = oval;
                    }
                } else {
                    int x = (biNodeOvalFull[i * 2 + 1].getX() + biNodeOvalFull[i * 2 + 2].getX()) / 2;
                    int y = biNodeOvalFull[i * 2 + 1].getY() - (int) (lineHeight * E);

                    if (biNodeOvalFull[i * 2 + 1].getBiNode() != null) {
                        //左边加条线
                        Line line = new Line();
                        line.setX1((int) (x + 0.5 * width * E));
                        line.setY1((int) (y + width * E));
                        line.setX2((int) (biNodeOvalFull[i * 2 + 1].getX() + 0.5 * width * E));
                        line.setY2((int) (biNodeOvalFull[i * 2 + 1].getY()));
                        lines.add(line);
                    }

                    if (biNodeOvalFull[i * 2 + 2].getBiNode() != null) {
                        //右边加条线
                        Line line = new Line();
                        line.setX1((int) (x + 0.5 * width * E));
                        line.setY1((int) (y + width * E));
                        line.setX2((int) (biNodeOvalFull[i * 2 + 2].getX() + 0.5 * width * E));
                        line.setY2((int) (biNodeOvalFull[i * 2 + 2].getY()));
                        lines.add(line);
                    }
                    if (biNodeOvalFull[i] != null) {
                        biNodeOvalFull[i].setX(x);
                        biNodeOvalFull[i].setY(y);
                    } else {
                        BiNodeOval oval = new BiNodeOval();
                        oval.setX(x);
                        oval.setY(y);
                        biNodeOvalFull[i] = oval;
                    }
                }
            }
            ch--;
        }
    }


    public BiTreePanel(){
        biTree = BiTree.defaultTree2();
        this.solve();
        this.setPreferredSize(new Dimension((int)(Math.pow(2,h)*(width+spacing)*E),800));
    }

    @Override
    public void paint(Graphics g) {
        if (biNodeOvalFull == null) {
            solve();
        } else{
            for (int i = 0; i < biNodeOvalFull.length-1; i++) {
                if(biNodeOvalFull[i] != null && biNodeOvalFull[i].getBiNode()!=null) {
                    g.fillOval(biNodeOvalFull[i].getX(), biNodeOvalFull[i].getY(), (int) width * E, (int) width * E);
                    g.drawString(String.valueOf(biNodeOvalFull[i].getBiNode().getData()),(int) (biNodeOvalFull[i].getX()+0.4*width * E),(int)(biNodeOvalFull[i].getY()+0.6*width * E));
                }
            }

            for(int i =0;i<lines.size();i++){
                Line line = lines.get(i);
                g.drawLine(line.getX1(),line.getY1(),line.getX2(),line.getY2());
            }
        }
    }
}
