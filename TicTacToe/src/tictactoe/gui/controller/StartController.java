package tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class StartController implements Initializable {
    @FXML
    private VBox startPage;
    @FXML
    private Button pvc;
    @FXML
    private Button pvp;
    @FXML
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startPage.getStyleClass().add("start");
        pvc.getStyleClass().add("pvc");
        pvp.getStyleClass().add("pvp");

    }


    public void loadPlayerWorld(ActionEvent event) throws IOException {
        // stage = (Stage)((Scene)(((Node)event.getSource()).getScene())).getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/TicTacView.fxml"));
        Parent root = loader.load();
        Scene playerGame = new Scene(root);
        stage = new Stage();
        stage.setScene(playerGame);
        stage.show();
    }
}
