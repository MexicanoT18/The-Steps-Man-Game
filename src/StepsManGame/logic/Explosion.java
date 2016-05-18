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
        if (stack_counter > 50) return;
        stack_counter++;
        
        Point pos = platform.getPosition();
        
        for (Platform adj : platform.getAdjacent()){
            Point pos2 = adj.getPosition();
            adj.plantSeed(platform.getWhosPlatform());
        }
        
        platform.setSeeds(0);
        
        stack_counter--;
    }
    
}
