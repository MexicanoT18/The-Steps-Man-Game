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
    
    GameSystem gameSystem;
    
    public OnlineSystem(){
        
    }
    
    public void initialize(GameSystem gameSystem) {
        this.gameSystem = gameSystem;
    }
}
