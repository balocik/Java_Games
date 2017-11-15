/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanforkids;

//import java.util.HashMap;
//import java.util.Map;

/**
 * Created by wojciechkuczer on 07/11/2017.
 */

public class Player {
    private String name;
    private int points;

    public Player(String name){
        this.name = name;
        this.points = 0; 
    }

//    public void addPlayer(String name){
//        pointChart.put(name, 0);
//    }
//
//    public void addPoint(String name){
//        pointChart.put(name, pointChart.get(name) + 1);
//    }
//    
//    public Map<String, Integer> getScore(){
//        return pointChart;
//    }
    
    public String getPlayerName(){
        return name;
    }
    
    public Integer getPoints(){
        return points;
    }
    
    public void addPoint(){
        points += 1;
    }
    
    
}
