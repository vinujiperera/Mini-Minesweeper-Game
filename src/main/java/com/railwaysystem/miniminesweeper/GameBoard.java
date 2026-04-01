/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.railwaysystem.miniminesweeper;

/**
 *
 * @author Admin
 */
import java.util.Random;

public class GameBoard {
    public static final int ROWS = 8;
    public static final int COLS = 8;
    public static final int TOTAL_MINES = 10;

    private Cell[][] grid;
    private boolean gameOver;
    private boolean gameWon;
    private int revealedCellsCount;
    private int flagsPlaced;
    private boolean firstClick;

    public GameBoard() {
        resetBoard();
    }

    public void resetBoard() {
        grid = new Cell[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
        gameOver = false;
        gameWon = false;
        revealedCellsCount = 0;
        flagsPlaced = 0;
        firstClick = true;
    }

    
    private void placeMines(int safeRow, int safeCol) {
        Random rand = new Random();
        int minesPlaced = 0;

        while (minesPlaced < TOTAL_MINES) {
            int r = rand.nextInt(ROWS);
            int c = rand.nextInt(COLS);

            
            if (!grid[r][c].isMine() && (r != safeRow || c != safeCol)) {
                grid[r][c].setMine(true);
                minesPlaced++;
            }
        }
        calculateAdjacencies();
    }

    private void calculateAdjacencies() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c].isMine()) continue;

                int mineCount = 0;
                
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int nr = r + dr;
                        int nc = c + dc;
                        if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS && grid[nr][nc].isMine()) {
                            mineCount++;
                        }
                    }
                }
                grid[r][c].setAdjacentMines(mineCount);
            }
        }
    }

    public void revealCell(int r, int c) {
        if (gameOver || gameWon || grid[r][c].isRevealed() || grid[r][c].isFlagged()) return;

       
        if (firstClick) {
            placeMines(r, c);
            firstClick = false;
        }

        Cell cell = grid[r][c];
        cell.setRevealed(true);

        if (cell.isMine()) {
            gameOver = true;
            return;
        }

        revealedCellsCount++;

        
        if (cell.getAdjacentMines() == 0) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int nr = r + dr;
                    int nc = c + dc;
                    if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS) {
                        revealCell(nr, nc);
                    }
                }
            }
        }
        checkWinCondition();
    }

    public void toggleFlag(int r, int c) {
        if (gameOver || gameWon || grid[r][c].isRevealed()) return;

        Cell cell = grid[r][c];
        cell.setFlagged(!cell.isFlagged());
        flagsPlaced += cell.isFlagged() ? 1 : -1;
    }

    private void checkWinCondition() {
        int nonMineCells = (ROWS * COLS) - TOTAL_MINES;
        if (revealedCellsCount == nonMineCells) {
            gameWon = true;
        }
    }

    public Cell getCell(int r, int c) { return grid[r][c]; }
    public boolean isGameOver() { return gameOver; }
    public boolean isGameWon() { return gameWon; }
    public int getFlagsPlaced() { return flagsPlaced; }
    public boolean isFirstClick() { return firstClick; }
}