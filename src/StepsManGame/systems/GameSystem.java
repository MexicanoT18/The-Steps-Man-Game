/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.systems;

import StepsManGame.logic.Menu;
import StepsManGame.view.ViewsMediator;
import java.awt.Point;

/**
 *
 * @author Lucas
 */
public class GameSystem {
    
    private ViewsMediator viewsMediator;
    private OnlineSystem onlineSystem;

    private PlayerSystem playerSystem;
    private PlatformSystem platformSystem;
    private ExplosionSystem explosionSystem;
    private Menu menu;

    public enum GameState {
        IN_GAME, PAUSED
    }

    private PlayerSystem.Players winner;
    private GameState gameState;

    public GameSystem() {
        this.playerSystem = new PlayerSystem();
        this.platformSystem = new PlatformSystem();
        this.explosionSystem = new ExplosionSystem();
        this.menu = new Menu();
    }

    public void initialize(ViewsMediator viewsMediator, OnlineSystem onlineSystem) {
        
        gameState = GameState.IN_GAME;
        
        this.onlineSystem = onlineSystem;
        this.viewsMediator = viewsMediator;
        
        this.platformSystem.initialize(viewsMediator, this, playerSystem, explosionSystem);
        this.playerSystem.initialize(viewsMediator, this, platformSystem, explosionSystem, onlineSystem);
        this.explosionSystem.initialize(this, viewsMediator);
        this.menu.initialize(this, viewsMediator, platformSystem, playerSystem, explosionSystem, onlineSystem);

        playerSystem.createPlayer(PlayerSystem.Players.PLAYER_1, viewsMediator);
        playerSystem.createPlayer(PlayerSystem.Players.PLAYER_2, viewsMediator);
    }

    public void restart() {
        gameState = GameState.IN_GAME;
        viewsMediator.restart();
        this.initialize(viewsMediator, onlineSystem);
    }
    
    public PlayerSystem.Players getWinner() {
        return winner;
    }
    
    public void setWinner(PlayerSystem.Players winner) {
        this.winner = winner;
    }
    
    public void setPoint(int player, Point point){
        playerSystem.setPoint(player, point);
    }
    
    public void plantSeed(int player){
        playerSystem.plantSeed(player);
    }
    
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
    
    public int checkWinner(){
        return platformSystem.checkWinner();
    }
}
