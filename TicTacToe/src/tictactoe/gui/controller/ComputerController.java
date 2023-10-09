package tictactoe.gui.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tictactoe.bll.Computer.GameBoardComputer;
import tictactoe.bll.Computer.GameBoardComputerControllable;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ComputerController implements Initializable {
    private String playerSymbol= "";
    private String computerLevel= "";
    @FXML
    private Button btnNewGame;
    @FXML
    private GridPane gridPane;
    private GameBoardComputerControllable game;

    @FXML
    private Label lblPlayer;
    private static final String TXT_PLAYER = "Player: ";

    public void handleButtonAction(ActionEvent event) {
        if(game.getPlayerSymbol().isEmpty()){
            game.setPlayerSymbol(playerSymbol);
            game.setCurrentPlayer(playerSymbol);
        }
        if(game.getGridData()==null){
            game.setGridData(setGridData(gridPane));
        }
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ?  0: row;
            int c = (col == null) ? 0 : col;
            String player = game.getNextPlayer();
            if (game.play(c, r))
            {
                if (game.isGameOver())
                {
                    Button btn = (Button) event.getSource();
                    btn.setText(decideSymbol(player,playerSymbol));
                    setPlayer();
                    displayWinner(game.getWinner());
                }
                else
                {
                   Button btn = (Button) event.getSource();
                    btn.setText(decideSymbol(player,playerSymbol));
                    setPlayer();
                }
            }else{
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());

        }

    }
    public void handleComputerStart(){
      game.setCurrentPlayer(playerSymbol);
      game.setPlayerSymbol(playerSymbol);
      int rowComputer = getRandomNumber(3);
      int columnComputer = getRandomNumber(3);
      List<Node> nodes = gridPane.getChildren();
      for(Node node: nodes){
          int row =   gridPane.getRowIndex(node)!=null?gridPane.getRowIndex(node):0;
          int column = gridPane.getColumnIndex(node)!=null?gridPane.getColumnIndex(node):0;
          if(rowComputer==row && columnComputer==column){
              ((Button)node).setText("X");
          }
      }
        game.setGridData(setGridData(gridPane));

    }

   @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoardComputer();
        game.setGridData(setGridData(gridPane));

    }



    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + "\""+ game.getNextPlayer()+"\"");
    }

    private void displayWinner(String winner)
    {
        String message = "";
        switch (winner)
        {
            case "draw":
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String getComputerLevel() {
        return computerLevel;
    }

    public void setComputerLevel(String computerLevel) {
        this.computerLevel = computerLevel;
    }


    public Label getLblPlayer() {
        return lblPlayer;
    }

    public void setLblPlayer(String text) {
        this.lblPlayer.setText(TXT_PLAYER+ " " + "\""+text+"\"" );
    }

    private int getRandomNumber (int bound){
        Random random =new Random();
        return random.nextInt(bound);
    }

    private String[][] setGridData(GridPane grisPane){
        String[][] gridData = new String[3][3];
        List<Node> nodes = gridPane.getChildren();

        for(Node node: nodes){
            int row =   gridPane.getRowIndex(node)!=null?gridPane.getRowIndex(node):0;
            int column = gridPane.getColumnIndex(node)!=null?gridPane.getColumnIndex(node):0;
            gridData[row][column]= ((Button)node).getText();
        }
        return gridData;
    }
    private String decideSymbol(String player,String playerSymbol ){
        String xoro="";
        if(player.equals("X")){
            xoro = "X";
        }else if(player.equals("O")){
            xoro = "O";
        }else if(player.equals("Computer")&&playerSymbol.equals("X")){
            xoro="O";
        }else if(player.equals("Computer")&&playerSymbol.equals("O")){
            xoro="X";
        }
        return xoro;
    }
}
