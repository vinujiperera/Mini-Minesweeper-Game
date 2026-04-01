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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineSweeperFrame extends JFrame {
    private GameBoard board;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private JLabel minesRemainingLabel;
    private JLabel timerLabel;
    private Timer gameTimer;
    private int secondsElapsed;

    public MineSweeperFrame() {
        board = new GameBoard();
        setTitle("Mini Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setupTopPanel();
        setupGridPanel();
        setupBottomPanel();
        
        pack();
        setLocationRelativeTo(null); 
        setResizable(false);
        
        getContentPane().setBackground(new Color( 234,187,237));
    }

    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));  
        

        minesRemainingLabel = new JLabel("Mines: " + GameBoard.TOTAL_MINES);
        minesRemainingLabel.setFont(new Font("SERIF", Font.BOLD, 18));
        
        statusLabel = new JLabel("Status : Playing", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SERIF", Font.BOLD, 18));
        statusLabel.setForeground(new Color (125,23,101));
        
        timerLabel = new JLabel("Time: 0s", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("SERIF", Font.BOLD, 18));
        
        

        topPanel.add(minesRemainingLabel);
        topPanel.add(statusLabel);
        topPanel.add(timerLabel);
       

        add(topPanel, BorderLayout.NORTH);

        // Timer
        gameTimer = new Timer(1000, e -> {
            secondsElapsed++;
            timerLabel.setText("Time: " + secondsElapsed + "s");
        });
    }
    
    private void setupBottomPanel(){
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder( 20,20,20,20));
       
        
        JButton restartButton = new JButton("RESTART");
        restartButton.setFont(new Font("SERIF", Font.BOLD, 18));
        restartButton.setForeground(new Color(255,125,125));
        restartButton.setBackground(new Color(125,23,101));
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> restartGame());
        
        bottomPanel.add(restartButton);
        add(bottomPanel,BorderLayout.SOUTH);
        
    }

    private void setupGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(GameBoard.ROWS, GameBoard.COLS));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
       
        buttons = new JButton[GameBoard.ROWS][GameBoard.COLS];

        for (int r = 0; r < GameBoard.ROWS; r++) {
            for (int c = 0; c < GameBoard.COLS; c++) {
                buttons[r][c] = new JButton();
                buttons[r][c].setPreferredSize(new Dimension(50, 50));
                buttons[r][c].setFont(new Font("SERIF", Font.BOLD, 18));
                buttons[r][c].setFocusable(false);
                buttons[r][c].setMargin(new java.awt.Insets(0,0,0,0));
                
                final int row = r;
                final int col = c;

                
                buttons[r][c].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (board.isGameOver() || board.isGameWon()) return;

                        if (SwingUtilities.isRightMouseButton(e)) {
                            board.toggleFlag(row, col);
                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            if (board.isFirstClick()) {
                                gameTimer.start(); 
                            }
                            board.revealCell(row, col);
                        }
                        updateUIState();
                    }
                });
                gridPanel.add(buttons[r][c]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    private void restartGame() {
        board.resetBoard();
        gameTimer.stop();
        secondsElapsed = 0;
        timerLabel.setText("Time: 0s");
        statusLabel.setText("Playing");
        statusLabel.setForeground(new Color (125,23,101));
        updateUIState();
    }

    private void updateUIState() {
        int remainingMines = GameBoard.TOTAL_MINES - board.getFlagsPlaced();
        minesRemainingLabel.setText("Mines: " + remainingMines);

        
        for (int r = 0; r < GameBoard.ROWS; r++) {
            for (int c = 0; c < GameBoard.COLS; c++) {
                Cell cell = board.getCell(r, c);
                JButton btn = buttons[r][c];

                btn.setText("");
                btn.setBackground(null); 

                if (cell.isRevealed()) {
                   
                    if (cell.isMine()) {
                        btn.setText("B");
                        btn.setBackground(new Color(174,37,9));
                    } else if (cell.getAdjacentMines() > 0) {
                        btn.setText(String.valueOf(cell.getAdjacentMines()));
                        // Color code numbers
                        if (cell.getAdjacentMines() == 1) btn.setForeground(new Color(2,116,211));
                        else if (cell.getAdjacentMines() == 2) btn.setForeground(new Color(8,129,76));
                        else btn.setForeground(new Color(174,31,64));
                    }
                } else if (cell.isFlagged()) {
                    btn.setText("F");
                    btn.setForeground(new Color(174,37,9));
                } else {
                    btn.setEnabled(true);
                }
            }
        }

        
        if (board.isGameOver()) {
            gameTimer.stop();
            statusLabel.setText("Game Over");
            statusLabel.setForeground(Color.RED);
            revealAllMines();
            JOptionPane.showMessageDialog(this, "You hit a mine! Game Over.", "Game Over", JOptionPane.ERROR_MESSAGE);
        } else if (board.isGameWon()) {
            gameTimer.stop();
            statusLabel.setText("You Win!");
            statusLabel.setForeground(new Color(0, 153, 0));
            JOptionPane.showMessageDialog(this, "Congratulations! You cleared the board in " + secondsElapsed + " seconds.", "You Win!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void revealAllMines() {
        for (int r = 0; r < GameBoard.ROWS; r++) {
            for (int c = 0; c < GameBoard.COLS; c++) {
                if (board.getCell(r, c).isMine()) {
                    buttons[r][c].setText("💣");
                    buttons[r][c].setBackground(new Color(174,37,9));
                }
            }
        }
    }
}
