/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.systems;

import StepsManGame.logic.Player;
import StepsManGame.view.ViewsMediator;

/**
 *
 * @author Lucas
 */
public class PlayerSystem {
    
    GameSystem gameSystem;
    PlatformSystem platformSystem;
    ExplosionSystem explosionSystem;
    
    public enum Players{
        PLAYER_1, NONE, PLAYER_2
    }
       
    private Player player1;
    private Player player2;
    
    public PlayerSystem() {
        player1 = new Player();
        player2 = new Player();
    }
    
    public void initialize(GameSystem gameSystem, PlatformSystem platformSystem, ExplosionSystem explosionSystem){
        this.gameSystem = gameSystem;
        this.platformSystem = platformSystem;
        this.explosionSystem = explosionSystem;
    }
    
    public GameSystem.GameState getGameState() {
        return gameSystem.getGameState();
    }
    
    public void setGameState(GameSystem.GameState gameState) {
        gameSystem.setGameState(gameState);
    }
    
    public void switchTurns() {
        if (player1.getTurnState() == Player.TurnState.MY_TURN) {
            player1.setState(Player.TurnState.HIS_TURN);
            player2.setState(Player.TurnState.MY_TURN);
        } else if (player2.getTurnState() == Player.TurnState.MY_TURN) {
            player2.setState(Player.TurnState.HIS_TURN);
            player1.setState(Player.TurnState.MY_TURN);
        }
    }

    public void createPlayer(Players who, ViewsMediator viewsMediator) {
        
        Player player = new Player();
        player.initialize(who, this, viewsMediator, platformSystem, explosionSystem);
        
        if (who == Players.PLAYER_1) {
            player1 = player;
        } else if (who == Players.PLAYER_2) {
            player2 = player;
        }
    }
}
