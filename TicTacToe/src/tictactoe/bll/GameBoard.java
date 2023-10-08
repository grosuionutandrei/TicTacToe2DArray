/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

/**
 * @author Stegger
 */
public class GameBoard implements IGameModel {
    private int currentPlayer;
    private int winner = -1;
    private int turn;

    private int[][] board = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer() {
        return currentPlayer;

    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row) {

        System.out.println(turn);
        boolean play = false;


            if (isGameOver()) {
                return false;
            }

        if (board[row][col] < 0) {
            board[row][col] = currentPlayer;
            play = true;
        } else if (board[row][col] >= 0) {
            return false;
        }

        if (currentPlayer == 0) {
            currentPlayer = 1;
        } else if (currentPlayer == 1) {
            currentPlayer = 0;
        }
        turn+=1;
        return play;
    }

    public boolean isGameOver() {
        if (checkWinnerRowsAndDiagonals(board)) {
            return true;
        }
        if (checkColumns(board)){
            return true;
        }
        if(turn==9){
            return true;
        }
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame() {
        this.currentPlayer = 0;
        this.turn=0;
        resetBoard(this.board);
    }

    private boolean checkWinnerRowsAndDiagonals(int[][] data) {
        boolean endGame = false;
        int[] currentRow = new int[data.length];
        int[] mainDiagonal = new int[data.length];
        int[] secondDiagonal = new int[data.length];



        for (int i = 0; i < data.length; i++) {
            if (endGame) {
                break;
            }
            for (int j = 0; j < data[0].length; j++) {
                currentRow[j] = data[i][j];
                if (i == j) {
                    mainDiagonal[i] = data[i][j];
                }
                if (j + i == (data.length - 1)) {
                    secondDiagonal[i] = data[i][j];
                }
            }
            if (isFullRow(currentRow)) {
                this.winner = decideWinner(currentRow);
                endGame = true;
            }

        }
        if(isFullRow(mainDiagonal)){
            this.winner = decideWinner(mainDiagonal);
            endGame = true;

        }
        if(isFullRow(secondDiagonal)){
            this.winner = decideWinner(secondDiagonal);
            endGame = true;

        }




        return endGame;
    }



    private boolean isFullRow(int[] row) {
        boolean theSame = false;
        if(isFullOne(row)||isFullZero(row)){
            theSame=true;
        }
        return theSame;
    }

    private boolean isFullZero(int[] toCheck) {
        int zero = 0;
        for (int i = 0; i < toCheck.length; i++) {
            if (toCheck[i] != zero) {
                return false;
            }
        }
        return true;
    }

    public boolean isFullOne(int[] toCheck) {
    int one= 1;
    for(int i = 0;i<toCheck.length;i++){
        if(toCheck[i]!=one){
            return false;
        }
    }
    return true;
    }

    private int decideWinner(int[] row) {
        if (row[0] == 0) {
            return 0;
        } else if (row[0] == 1) {
            return 1;
        }
        return -1;
    }

    private void resetBoard(int[][] arr) {
        int reset = -1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = reset;
            }
        }
    }


    public int[][] getBoard() {
        return board;
    }

    public boolean checkColumns(int[][] board){
        boolean endGame = false;
        int columns = board[0].length ;
        int [] columnValues = new int[board.length];
        for(int i = 0;i<columns;i++){
            if(endGame){
                break;
            }
            for(int j = 0; j<board.length;j++){
                for(int z = 0;z<board[0].length;z++){
                   if(z==i){
                       columnValues[j]=board[j][z];
                   }
                }

            }
           if(isFullRow(columnValues)){
               this.winner=decideWinner(columnValues);
               endGame=true;
           }

        }
       return  endGame;
    }

}
