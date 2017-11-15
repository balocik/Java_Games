/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanforkids;

import com.jfoenix.controls.JFXTextField;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author wojciechkuczer
 */
public class MainGameController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    public LiderBoard players = LiderBoard.instance;
    
    @FXML
    private Label lblPlayersList;
    
    @FXML
    private Label lblPlayersList1;
    
    @FXML
    private Label lblPlayersList2;
    
    @FXML
    private ImageView A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
    
    @FXML
    private ImageView[] imageViewArray;
    
    @FXML
    ColorAdjust colorAdjust = new ColorAdjust();
    
    @FXML
    ImageView imageBackground;
    
    @FXML
    ImageView imgHangman;
    
    @FXML
    Image hangmanImg;
    
    @FXML
    ImageView addWord;
    
    @FXML
    private Label lblMisteryWord;
    
    @FXML
    private final int maxWrong = 7;
    
    @FXML
    String[] imgArray;
    
    @FXML
    private int counter = 0;
    
    @FXML
    Scanner in = new Scanner(System.in);
    
    @FXML
    private JFXTextField txtWordToGuessInput;
    
    @FXML
    private String misteryWord;
    
    @FXML
    private String dots;
    
    @FXML
    private ImageView imgGameOver;
    
    @FXML
    private ImageView imgWinner;
    
    @FXML
    private Label btnRestart;
    
    @FXML
    private ImageView imgPlayer1Turn;
    
    @FXML
    private ImageView imgPlayer2Turn;
    
    @FXML
    private int playerSwitch = 0;
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playersList(); 
        this.imgArray = new String[]{"images/0.png", "images/1.png", "images/2.png", "images/3.png", "images/4.png", "images/5.png", "images/6.png", "images/7.png"};    
        switchPlayers();
        disableLetters();
    }
    
    @FXML
    public ImageView[] imageViewArrays(){
        imageViewArray = new ImageView[26];
        imageViewArray[0] = A;
        imageViewArray[1] = B;
        imageViewArray[2] = C;
        imageViewArray[3] = D;
        imageViewArray[4] = E;
        imageViewArray[5] = F;
        imageViewArray[6] = G;
        imageViewArray[7] = H;
        imageViewArray[8] = I;
        imageViewArray[9] = J;
        imageViewArray[10] = K;
        imageViewArray[11] = L;
        imageViewArray[12] = M;
        imageViewArray[13] = N;
        imageViewArray[14] = O;
        imageViewArray[15] = P;
        imageViewArray[16] = Q;
        imageViewArray[17] = R;
        imageViewArray[18] = S;
        imageViewArray[19] = T;
        imageViewArray[20] = U;
        imageViewArray[21] = V;
        imageViewArray[22] = W;
        imageViewArray[23] = X;
        imageViewArray[24] = Y;
        imageViewArray[25] = Z;
        return imageViewArray;
    }
    
    @FXML
    public void changeHangmanImage(){
        InputStream is = getClass().getResourceAsStream(imgArray[counter]);
        hangmanImg = new Image(is);
        imgHangman.setImage(hangmanImg);
    }
    
    @FXML
    public void glowLetterEffect(MouseEvent event){
        try{
            String id = event.getPickResult().getIntersectedNode().getId();
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            if(alphabet.contains(id)){
                colorAdjust.setBrightness(0.0);
                event.getPickResult().getIntersectedNode().setEffect(new ColorAdjust(0, 0, 1.0, 0));
          
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    @FXML
    public void unglowEffect(MouseEvent event){
                colorAdjust.setBrightness(0.0);
                for(ImageView imageView : imageViewArrays()){
                    imageView.setEffect(colorAdjust);
                }
    }
    
    @FXML
    public void misteryWord(){
        misteryWord = txtWordToGuessInput.getText();
        misteryWord = misteryWord.toUpperCase();
        dots = "";
        for(int i = 0; i<misteryWord.length(); i++){
            dots = dots + "*";
        }
        lblMisteryWord.setText(dots);
        txtWordToGuessInput.clear();
        txtWordToGuessInput.setVisible(false);
        addWord.setVisible(false);
        ableLetters();
    }
    
    
    @FXML
    public void checkLetter(MouseEvent event){
        String id = event.getPickResult().getIntersectedNode().getId();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(!misteryWord.equals(dots)){
            if(misteryWord.contains(id) && (!misteryWord.equals(dots))){
                char character = id.charAt(0);
                for(int i = 0; i < misteryWord.length(); i++){
                    if(misteryWord.charAt(i) == character){
                        list.add(i);
                    }
                }
                event.getPickResult().getIntersectedNode().setVisible(false);
                String[] dotsArray = dots.split("");
                for(Integer number : list){
                    dotsArray[number] = id;
                }
                String revield = "";
                for(String s : dotsArray){
                    revield = revield + s;
                }
                dots = revield;
                lblMisteryWord.setText(revield);
                gameWon();
            }else{
                counter += 1;
                if(counter <= 7){
                    event.getPickResult().getIntersectedNode().setVisible(false);               
                    lblMisteryWord.setText(dots);
                    changeHangmanImage();
                    if(counter == 7){
                        gameOver();
                    }
                }
            }
        }       
    }
    
    @FXML
    public void gameOver(){
          for(ImageView imageView : imageViewArrays()){
              imageView.setVisible(false);
          }
        imgGameOver.setVisible(true);
        imgWinner.setVisible(false);
        lblMisteryWord.setText(misteryWord); 
        switchPlayers();
        counter = 0;
    }
    
    @FXML
    public void gameWon(){
        if(misteryWord.equals(dots)){
            for(ImageView imageView : imageViewArrays()){
              imageView.setVisible(false);
          }
            imgWinner.setVisible(true);
            addPoint();
            playersList();
            switchPlayers();
            counter = 0;
        }
        
    }
    
    @FXML
    public void playersList(){
        Player player1 = players.getPlayers().get(0);
        String p1 = player1.getPlayerName() + "\t" + player1.getPoints().toString();
        lblPlayersList.setText(p1);
        
        Player player2 = players.getPlayers().get(1);
        String p2 = player2.getPlayerName() + "\t" + player2.getPoints().toString();
        lblPlayersList1.setText(p2);
    }
    
    @FXML
    public void restartGame(MouseEvent event){
        for(ImageView imageView : imageViewArrays()){
              imageView.setVisible(true);
          }        
        imgGameOver.setVisible(false);
        imgWinner.setVisible(false);
        counter = 0;
        changeHangmanImage();
        txtWordToGuessInput.setVisible(true);
        addWord.setVisible(true);
        disableLetters();
        lblMisteryWord.setText("");
        
    }
    
    @FXML
    public void giveUp(MouseEvent event){
        restartGame(event);
        gameOver();
        txtWordToGuessInput.setVisible(false);
        addWord.setVisible(false);
    }
    
    @FXML
    public void switchPlayers(){
        playerSwitch += 1;
        if(playerSwitch % 2 == 1){
            imgPlayer1Turn.setVisible(true);
            imgPlayer2Turn.setVisible(false);
        }
        
        if(playerSwitch % 2 == 0){
            imgPlayer2Turn.setVisible(true);
            imgPlayer1Turn.setVisible(false);
        }
    }
    
    @FXML
    public void addPoint(){
        if(playerSwitch % 2 == 1){
            players.getPlayers().get(0).addPoint();
        }
        
        if(playerSwitch % 2 == 0){
            players.getPlayers().get(1).addPoint();
        }
    }
    
    @FXML
    public void closeGame(MouseEvent event){
        System.exit(0);
    }
    
    @FXML
    public void disableLetters(){
        for(ImageView letter : imageViewArrays()){
            letter.setDisable(true);
        }
    }
    
    @FXML
    public void ableLetters(){
        for(ImageView letter : imageViewArrays()){
            letter.setDisable(false);
        }
    }
   
}
