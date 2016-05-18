/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.logic;

import StepsManGame.systems.PlayerSystem;
import java.awt.Point;

/**
 *
 * @author Lucas
 */
public class Explosion implements Runnable{
    
    private Platform platform;
    
    public Explosion (Platform platform){
        this.platform = platform;
    }
    
    public void run(){
        Point pos = platform.getPosition();
        //System.out.println ("Boom at " + pos.x + " " + pos.y);
        
        //System.out.println (platform.getAdjacent().size());
        
        for (Platform adj : platform.getAdjacent()){
            Point pos2 = adj.getPosition();
            System.out.println ("FOI PRA " + pos2.x + " " + pos2.y);
            adj.plantSeed(platform.getWhosPlatform());
        }
    }
    
}
