/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.systems;

import StepsManGame.logic.Player;
import StepsManGame.view.ViewsMediator;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lucas
 */
public class PlayerSystem implements ActionListener {
    
    private GameSystem gameSystem;
    private PlatformSystem platformSystem;
    private ExplosionSystem explosionSystem;
    
    private static PlayerTAdapter adapter;
    
    public enum Players{
        PLAYER_1, NONE, PLAYER_2
    }
       
    private Player player1;
    private Player player2;
    
    public PlayerSystem() {
        player1 = new Player();
        player2 = new Player();
    }
    
    public void initialize(ViewsMediator viewsMediator, GameSystem gameSystem, PlatformSystem platformSystem, ExplosionSystem explosionSystem){
        this.gameSystem = gameSystem;
        this.platformSystem = platformSystem;
        this.explosionSystem = explosionSystem;
        
        if (adapter == null){
            adapter = new PlayerTAdapter();
            viewsMediator.addKeyListener(adapter);
        }
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public void processCommands(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (gameSystem.getGameState() != GameSystem.GameState.IN_GAME) return;

        if (key == KeyEvent.VK_ESCAPE) {
            gameSystem.setGameState(GameSystem.GameState.PAUSED);
            return;
        }
        
        if (key == KeyEvent.VK_SPACE){
            if (player1.getTurnState() == Player.TurnState.MY_TURN && platformSystem.plantSeed(player1))switchTurns();
            else if (player2.getTurnState() == Player.TurnState.MY_TURN && platformSystem.plantSeed(player2)) switchTurns();
            return;
        }

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
        
        Point position;
        
        if (player1.getTurnState() == Player.TurnState.MY_TURN){

            position = player1.getPosition();
            
            if (platformSystem.isInGame(position.x + dx, position.y + dy)) {
                successful = true;
            } else if ((dx == -1 || dx == 1) && platformSystem.isInGame(position.x + dx, position.y - 1)) {
                dy = -1;
                successful = true;
            }

            if (successful && !explosionSystem.getUpdatingExplosions()) {
                position.x += dx;
                position.y += dy;
                player1.setPosition(position);
            }
        }
        else if (player2.getTurnState() == Player.TurnState.MY_TURN){

            position = player2.getPosition();
            
            if (platformSystem.isInGame(position.x + dx, position.y + dy)) {
                successful = true;
            } else if ((dx == -1 || dx == 1) && platformSystem.isInGame(position.x + dx, position.y - 1)) {
                dy = -1;
                successful = true;
            }

            if (successful && !explosionSystem.getUpdatingExplosions()) {
                position.x += dx;
                position.y += dy;
                player2.setPosition(position);
            }
        }
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
