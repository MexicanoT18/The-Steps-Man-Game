/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.logic;

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
        System.out.println ("Boom at " + pos.x + " " + pos.y);
    }
    
}
