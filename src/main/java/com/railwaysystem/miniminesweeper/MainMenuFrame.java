/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.railwaysystem.miniminesweeper;

/**
 *
 * @author Admin
 */
import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame{
    public MainMenuFrame(){
        setTitle("Mini Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(new BorderLayout());
        
        setLocationRelativeTo(null);
        setResizable(false);
        
        JLabel Title = new JLabel("MINESWEEPER",SwingConstants.CENTER);
        Title.setFont(new Font("ALGERIAN",Font.BOLD, 48));
        Title.setForeground(new Color(108,13,96));
        add(Title,BorderLayout.CENTER);
        
        JButton b1 = new JButton("PLAY");
        b1.setFont(new Font("SERIF",Font.BOLD,20));
        b1.setForeground(new Color(255,125,125));
        b1.setBackground(new Color(125,23,101));
        add(b1,BorderLayout.SOUTH);
        
        b1.setFocusable(false);
        
        b1.addActionListener(e->{
            this.dispose();
            MineSweeperFrame frame = new MineSweeperFrame();
            frame.setVisible(true);
        });
        
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 20, 50));
            
        buttonPanel.add(b1,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
            
            
      }
    }
    
    

