package tictactoe.bll.Computer;

public interface GameBoardComputerControllable {
    public String getNextPlayer();
    public boolean play(int col, int row);
    public boolean isGameOver();
    public String getWinner();
    public void newGame();
    public void setCurrentPlayer(String player);
    public String getCurrentPlayer();
    public void setPlayerSymbol(String string);
    public String getPlayerSymbol();
    public void setGridData(String[][] gridData);
    public String[][] getGridData();
}
