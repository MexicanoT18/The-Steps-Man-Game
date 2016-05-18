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
    private boolean hosting;
    
    public OnlineSystem(){
        status = false;
        hosting = false;
    }
    
    public void createServer(){
        status = true;
        hosting = true;
        
    }
    
    public void createClient(){
        status = true;
        hosting = false;
        
    }
    
    public boolean getStatus(){
        return status;
    }
    
    public boolean isHost(){
        return hosting;
    }
    
    public void initialize(GameSystem gameSystem) {
        this.gameSystem = gameSystem;
    }
}
