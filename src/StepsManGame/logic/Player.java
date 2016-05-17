/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.logic;

import StepsManGame.systems.PlayerSystem;
import StepsManGame.view.ViewsMediator;
import StepsManGame.systems.ExplosionSystem;
import StepsManGame.systems.PlatformSystem;
import java.awt.Point;

/**
 *
 * @author Lucas
 */
public class Player{
    
    public enum TurnState{
        MENU, MY_TURN, HIS_TURN
    }
    
    private TurnState turnState;
    private PlayerSystem.Players player;
    private Point position;
    
    private PlayerSystem playerSystem;
    private PlatformSystem platformSystem;
    private ExplosionSystem explosionSystem;
    
    public Player(){
        
    }
    
    public void initialize(PlayerSystem.Players player, PlayerSystem playerSystem,
            ViewsMediator viewsMediator, PlatformSystem platformSystem, ExplosionSystem explosionSystem) {
        
        this.playerSystem = playerSystem;
        this.player = player;
        this.platformSystem = platformSystem;
        this.explosionSystem = explosionSystem;
        
        if (player == PlayerSystem.Players.PLAYER_1) {
            turnState = TurnState.MY_TURN;
            position = new Point(0, 0);
        }
        if (player == PlayerSystem.Players.PLAYER_2) {
            turnState = TurnState.HIS_TURN;
            position = new Point(8, 0);
        }
        
        viewsMediator.attachNewPlayerViewToPlayer(this);
    }
    
    public TurnState getTurnState() {
        return turnState;
    }

    public void setState(TurnState turnState) {
        this.turnState = turnState;
    }

    public PlayerSystem.Players getPlayer() {
        return player;
    }

    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point position){
        this.position = position;
    }

}
