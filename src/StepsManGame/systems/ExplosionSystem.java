/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.systems;

import StepsManGame.logic.Explosion;
import StepsManGame.view.ViewsMediator;
import StepsManGame.logic.Platform;

/**
 *
 * @author Lucas
 */
public class ExplosionSystem {
    
    GameSystem gameSystem;
    
    private boolean updatingExplosions;
    
    public void initialize(GameSystem gameSystem, ViewsMediator viewsMediator){
        this.gameSystem = gameSystem;
        this.updatingExplosions = false;
    }
    
    public void detonate(Platform platform){
        Thread thread = new Thread (new Explosion(platform));
        thread.start();
    }
    
    public boolean getUpdatingExplosions(){
        return updatingExplosions;
    }
}
