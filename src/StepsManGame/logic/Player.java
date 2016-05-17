/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.logic;

import StepsManGame.systems.PlayerSystem;
import StepsManGame.view.ViewsMediator;
import StepsManGame.systems.ExplosionSystem;
import StepsManGame.systems.GameSystem;
import StepsManGame.systems.PlatformSystem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lucas
 */
public class Player implements ActionListener {
    
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
        
        viewsMediator.addKeyListener(new PlayerTAdapter());
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public boolean processCommands(KeyEvent e) {

        if (playerSystem.getGameState() != GameSystem.GameState.IN_GAME) return false;
        
        int key = e.getKeyCode();

        boolean successful = false;
        int dx = 0, dy = 0;
        
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
        
        if (key == KeyEvent.VK_SPACE){
            return platformSystem.plantSeed(this);
        }

        if (key == KeyEvent.VK_ESCAPE) {
            playerSystem.setGameState(GameSystem.GameState.PAUSED);
        }

        if (platformSystem.isInGame(position.x + dx, position.y + dy)) {
            successful = true;
        } else if ((dx == -1 || dx == 1) && platformSystem.isInGame(position.x + dx, position.y - 1)) {
            dy = -1;
            successful = true;
        }

        if (successful && !explosionSystem.getUpdatingExplosions()) {
            position.x += dx;
            position.y += dy;
            playerSystem.switchTurns();
        }
        
        return false;
    }
    
    private class PlayerTAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            processCommands(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }
    }

}
