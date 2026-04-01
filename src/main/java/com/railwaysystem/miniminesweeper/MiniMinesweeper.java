/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.railwaysystem.miniminesweeper;

/**
 *
 * @author Admin
 */
import javax.swing.SwingUtilities;

public class MiniMinesweeper {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
           MainMenuFrame menuFrame = new MainMenuFrame();
            menuFrame.setVisible(true);
        });
    }
}
