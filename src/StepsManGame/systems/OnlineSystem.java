/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.systems;

/**
 *
 * @author Lucas
 */
public class OnlineSystem {
    
    private GameSystem gameSystem;
    private boolean status;
    
    public OnlineSystem(){
        status = false;
    }
    
    public void setOnline(){
        status = true;
    }
    
    public void createServer(){
        
    }
    
    public void createClient(){
        
    }
    
    public boolean getStatus(){
        return status;
    }
    
    public void initialize(GameSystem gameSystem) {
        this.gameSystem = gameSystem;
    }
}
