/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.view;

import StepsManGame.logic.Player;

/**
 *
 * @author Lucas
 */
public class PlayerView{
    
    ViewsMediator viewsMediator;
    Player attachedPlayer;
    
    public PlayerView(Player player, ViewsMediator viewsMediator){
        this.attachedPlayer = player;
        this.viewsMediator = viewsMediator;
    }
    
    public void loadResources(){
        
    }
    
}
