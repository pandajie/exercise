package com.blockgame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        JFrame jf = new JFrame();
        //
        GamePanel panel = new GamePanel();
        jf.getContentPane().add(panel, BorderLayout.CENTER);
        panel.run();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  jf.setResizable(false);
        jf.setVisible(true);
        panel.setFocusable(true);
        jf.pack();
    }
}
