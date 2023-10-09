/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll.Computer;

import javafx.scene.layout.GridPane;
import tictactoe.bll.IGameModel;

/**
 * @author Stegger
 */
public class GameBoardComputer implements GameBoardComputerControllable {


    private String player = "";
    private String playerSymbol = "";

    private String[][] gridData;
    private String winner = "";
    int turn = 0;

    //    Returns the current player
    public String getNextPlayer() {
      return player;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     */
    public boolean play(int col, int row) {
        if (isGameOver()) {
            return false;
        }

        System.out.println(gridData[row][col]);

        if (gridData[row][col].isEmpty()) {
            gridData[row][col] = decideSymbol(player, playerSymbol);
        } else {
            return false;
        }
        if (player.equals("Computer")) {
            player = playerSymbol;

        } else {
            player = "Computer";

        }
        printGrid(gridData);


        turn += 1;
        return true;
    }

    public boolean isGameOver() {
        if (checkRowsAndDiagonals(gridData)) {
            return true;
        }
        if (checkColumns(gridData)) {
            return true;
        }
        if (turn == 9) {
            this.winner = "draw";
            return true;
        }
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame() {
        if (playerSymbol.equals("O")) {
            this.player = "Computer";
        } else {
            this.player = "X";
            this.playerSymbol = "X";
        }

        this.winner = "";
        turn = 0;
        this.gridData = null;
    }

    @Override
    public void setCurrentPlayer(String player) {
        this.player = player;
    }

    @Override
    public String getCurrentPlayer() {
        return player;
    }

    @Override
    public void setPlayerSymbol(String symbol) {
        this.playerSymbol = symbol;
    }

    @Override
    public String getPlayerSymbol() {
        return this.playerSymbol;
    }

    @Override
    public void setGridData(String[][] gridData) {
        this.gridData = gridData;
    }

    @Override
    public String[][] getGridData() {
        return this.gridData;
    }

    private boolean checkRowsAndDiagonals(String[][] gridData) {
        boolean endGame = false;
        String[] currentRow = new String[gridData.length];
        String[] mainDiagonal = new String[gridData.length];
        String[] secondDiagonal = new String[gridData.length];
        for (int i = 0; i < gridData.length; i++) {
            if (endGame) {
                break;
            }
            for (int j = 0; j < gridData[0].length; j++) {
                currentRow[j] = gridData[i][j];
                if (i == j) {
                    mainDiagonal[i] = gridData[i][j];
                }
                if (j + i == (gridData.length - 1)) {
                    secondDiagonal[i] = gridData[i][j];
                }
            }
            if (isFullRow(currentRow)) {
                this.winner = decideWinner(currentRow);
                endGame = true;
            }
        }
        if (!endGame) {
            if (isFullRow(mainDiagonal)) {
                printSimpleArr(currentRow);
                this.winner = decideWinner(mainDiagonal);
                endGame = true;
            }
            if (isFullRow(secondDiagonal)) {
                this.winner = decideWinner(secondDiagonal);
                endGame = true;
            }
        }

        return endGame;

    }

    public boolean checkColumns(String[][] board) {
        boolean endGame = false;
        int columns = board[0].length;
        String[] columnValues = new String[board.length];
        for (int i = 0; i < columns; i++) {
            if (endGame) {
                break;
            }
            for (int j = 0; j < board.length; j++) {
                for (int z = 0; z < board[0].length; z++) {
                    if (z == i) {
                        columnValues[j] = board[j][z];
                    }
                }

            }
            if (isFullRow(columnValues)) {
                this.winner = decideWinner(columnValues);
                endGame = true;
            }

        }
        return endGame;
    }

    private boolean isFullRow(String[] row) {
        boolean theSame = false;
        if (isFullX(row) || isFullZero(row)) {
            theSame = true;
        }
        return theSame;
    }

    private boolean isFullZero(String[] toCheck) {
        String zero = "O";
        for (int i = 0; i < toCheck.length; i++) {
            if (!toCheck[i].equals(zero)) {
                return false;
            }
        }
        return true;
    }

    public boolean isFullX(String[] toCheck) {
        String x = "X";
        for (int i = 0; i < toCheck.length; i++) {
            if (!toCheck[i].equals(x)) {
                return false;
            }
        }
        return true;
    }

    private String decideWinner(String[] row) {
        if (row[0].equals("O")) {
            return "O";
        } else if (row[0].equals("X")) {
            return "X";
        }
        return "none";
    }


    private void printGrid(String[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j].equals("") ? "-" : grid[i][j]);
            }
            System.out.println();
        }
    }

    private String decideSymbol(String player, String playerSymbol) {
        String xoro = "";
        if (player.equals("X")) {
            xoro = "X";
        } else if (player.equals("O")) {
            xoro = "O";
        } else if (player.equals("Computer") && playerSymbol.equals("X")) {
            xoro = "O";
        } else if (player.equals("Computer") && playerSymbol.equals("O")) {
            xoro = "X";
        }
        return xoro;
    }

    private void printSimpleArr(String[] row) {
        for (int i = 0; i < row.length; i++) {
            System.out.print(row[i]);
        }
    }
}
