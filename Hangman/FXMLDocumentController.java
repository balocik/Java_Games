/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanforkids;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author wojciechkuczer
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private JFXButton btnStartGame;
    
    @FXML
    public LiderBoard players = LiderBoard.instance;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Label label;
    
    @FXML
    private Label lblPlayer1;
    
    @FXML 
    private Label PlayersChart;
    
    @FXML
    private JFXTextField txtEnterPlayer1;
    
    @FXML
    private JFXTextField txtEnterPlayer2;
    
    @FXML
    private Label lblPlayer1Name;
    
    @FXML
    private Label lblPlayer2Name;
    
    @FXML
    private ImageView btnAddPlayer1;
    
    @FXML
    private ImageView btnAddPlayer2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    
    @FXML
    private void addPlayerButtno(MouseEvent event){
        String name = txtEnterPlayer1.getText();
        if(!name.equals("")){
            players.addPlayer(name);
            txtEnterPlayer1.setVisible(false);
            btnAddPlayer1.setVisible(false);
            lblPlayer1Name.setText(txtEnterPlayer1.getText().toUpperCase());
            lblPlayer1Name.setVisible(true);
            showStartButton();
        }
        
    }
    
    @FXML
    private void addPlayer2Buttno(MouseEvent event){
        String name = txtEnterPlayer2.getText();
        if(!name.equals("")){
            players.addPlayer(name);
            txtEnterPlayer2.setVisible(false);
            btnAddPlayer2.setVisible(false);
            lblPlayer2Name.setText(txtEnterPlayer2.getText().toUpperCase()); 
            lblPlayer2Name.setVisible(true);
            showStartButton();
        }
        
    }
    
    @FXML
    private void loadMainGame(ActionEvent event) throws IOException{
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("mainGame.fxml"));
//        rootPane.getChildren().setAll(pane);
       if(!players.getPlayers().isEmpty()){
            FXMLLoader fxmll = new FXMLLoader(getClass().getResource("mainGame.fxml"));
            Parent root = (Parent) fxmll.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root));
            stage2.show();
            Stage stage1 = (Stage) rootPane.getScene().getWindow();
            stage1.close();
        }
    }
    
    
    @FXML
    private void addPlayer1EnterPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            String name = txtEnterPlayer1.getText();
            if(!name.equals("")){
                players.addPlayer(name);
                txtEnterPlayer1.setVisible(false);
                btnAddPlayer1.setVisible(false);
                lblPlayer1Name.setText(txtEnterPlayer1.getText().toUpperCase());
                lblPlayer1Name.setVisible(true);
                showStartButton();
            }
        }
    }
    
    @FXML
    private void addPlayer2EnterPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            String name = txtEnterPlayer2.getText();
            if(!name.equals("")){
                players.addPlayer(name);
                txtEnterPlayer2.setVisible(false);
                btnAddPlayer2.setVisible(false);
                lblPlayer2Name.setText(txtEnterPlayer2.getText().toUpperCase());
                lblPlayer2Name.setVisible(true);
                showStartButton();
            }
        }
    }
    
    @FXML
    public void showStartButton(){
        if(players.getPlayers().size() == 2){
            btnStartGame.setVisible(true);
        }
    }
}
