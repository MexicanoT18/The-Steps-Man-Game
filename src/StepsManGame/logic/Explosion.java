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
    private static int stack_counter = 0;
    
    public Explosion (Platform platform){
        this.platform = platform;
    }
    
    public void run(){
        Point pos = platform.getPosition();
        PlayerSystem.Players player = platform.getWhosPlatform();
        
        if (stack_counter > 50) return;
        if (player == PlayerSystem.Players.NONE){
            //System.out.println ("ERROR");
            //System.out.println (pos.x + " " + pos.y);
            return;
        }
        stack_counter++;
        
        platform.setSeeds(0);
        
        for (Platform adj : platform.getAdjacent()){
            Point pos2 = adj.getPosition();
            
            adj.setWhosPlatform(player);
            adj.plantSeed(player);
            //adj.setSeeds(adj.getSeeds() + 1);
            
            //if (adj.getSeeds() >= adj.getLimit())
                //adj.detonate();
        }
        
        stack_counter--;
    }
    
}
