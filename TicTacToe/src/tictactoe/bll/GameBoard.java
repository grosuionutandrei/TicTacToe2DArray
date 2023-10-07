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
        System.out.println(board.length - 1);
        boolean play = false;

        if (isGameOver()) {
            return false;
        }

        if (board[row][col] < 0) {
            board[row][col] = currentPlayer;
            System.out.println(board[row][col] + " data initialized");

            play = true;
        } else if (board[row][col] >= 0) {
            return false;
        }

        if (currentPlayer == 0) {
            currentPlayer = 1;
        } else if (currentPlayer == 1) {
            currentPlayer = 0;
        }


        //TODO Implement this method
        return play;
    }

    public boolean isGameOver() {
        //TODO Implement this method

        if (checkWinnerRowsAndDiagonals(board)) {
            return true;
        }
        if (checkColumns(board)){
            System.out.println(getWinner() + " winner");
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
        //TODO Implement this method
        return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame() {
        //TODO Implement this method
        this.currentPlayer = 0;
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
                    System.out.println(data[i][j] + " maindiagonal" + i);
                    mainDiagonal[i] = data[i][j];
                }
                if (j + i == (data.length - 1)) {
                    secondDiagonal[i] = data[i][j];
                }
            }
            if (isFullRow(currentRow)) {
                System.out.println(decideWinner(currentRow) + "We have a winner");
                this.winner = decideWinner(currentRow);
                endGame = true;
            }
            System.out.println(printArr(currentRow) + " current row" + " " + i);

            currentRow = new int[data.length];


        }
        System.out.println(endGame);
        if(isFullRow(mainDiagonal)){
            System.out.println(decideWinner(currentRow) + "We have a winner");
            this.winner = decideWinner(mainDiagonal);
            endGame = true;

        }
        if(isFullRow(secondDiagonal)){
            System.out.println(decideWinner(currentRow) + "We have a winner");
            this.winner = decideWinner(secondDiagonal);
            endGame = true;

        }




        return endGame;
    }


    private String printArr(int[] arr) {
        String temp = "";
        for (int i = 0; i < arr.length; i++) {
            temp += String.valueOf(arr[i]);
        }
        return temp;
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
        int[] columns = {0,1,2} ;
        int [] columnValues = new int[board.length];
        for(int i = 0;i<columns.length;i++){
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
               System.out.println( printArr(columnValues)+" column " + i);
               System.out.println(decideWinner(columnValues)+ "winner");
               this.winner=decideWinner(columnValues);
               endGame=true;
           }

        }
       return  endGame;
    }

}
