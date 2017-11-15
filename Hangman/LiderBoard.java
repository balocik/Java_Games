/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanforkids;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wojciechkuczer
 */
public class LiderBoard {
    public static LiderBoard instance = new LiderBoard();
    private List<Player> playersList = new ArrayList<>();
    
    public LiderBoard(){
        
    }
    
    public void addPlayer(String name){
        playersList.add(new Player(name));
    }
    
    public List<Player> getPlayers(){
        return  playersList;
    }

    
    
}
