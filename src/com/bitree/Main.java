package com.bitree;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        JFrame jf = new JFrame();
        //
        RedBlackTreePanel panel = new RedBlackTreePanel();
        jf.getContentPane().add(panel, BorderLayout.CENTER);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        panel.setFocusable(true);
        jf.pack();
    }
}
