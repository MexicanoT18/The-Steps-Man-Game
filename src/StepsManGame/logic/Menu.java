/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.logic;

import StepsManGame.systems.ExplosionSystem;
import StepsManGame.systems.GameSystem;
import StepsManGame.systems.PlatformSystem;
import StepsManGame.systems.PlayerSystem;
import StepsManGame.view.ViewsMediator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lucas
 */
public class Menu implements ActionListener{
    
    public enum Selection{
        CONTINUE, RESTART, EXIT
    }

    private Selection selection;
    GameSystem gameSystem;
    PlayerSystem playerSystem;
    PlatformSystem platformSystem;
    ExplosionSystem explosionSystem;
    private PlayerSystem.Players winner;
    static MenuTAdapter adapter;

    public Menu() {
        winner = PlayerSystem.Players.NONE;
        selection = Selection.CONTINUE;
    }

    public void initialize(GameSystem gameSystem, ViewsMediator viewsMediator, 
            PlatformSystem platformSystem, PlayerSystem playerSystem, ExplosionSystem explosionSystem) {
        
        winner = PlayerSystem.Players.NONE;
        selection = Selection.CONTINUE;
        
        this.gameSystem = gameSystem;
        this.explosionSystem = explosionSystem;
        this.platformSystem = platformSystem;
        this.playerSystem = playerSystem;
        
        if (adapter == null){
            adapter = new MenuTAdapter();
            viewsMediator.addKeyListener(adapter);
        }
        viewsMediator.attachNewMenuViewToMenu(this);
    }

    public Selection getSelection() {
        return selection;
    }

    public void setWinner(PlayerSystem.Players winner) {
        this.winner = winner;
    }

    public PlayerSystem.Players getWinner() {
        return winner;
    }

    public void processCommands(KeyEvent e) {

        if (gameSystem.getGameState() != GameSystem.GameState.PAUSED) return;
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP) {
            switch (selection) {
                case CONTINUE:
                    selection = Selection.EXIT;
                    break;
                case RESTART:
                    selection = Selection.CONTINUE;
                    break;
                case EXIT:
                    selection = Selection.RESTART;
                    break;
                default:
                    break;
            }
            return;
        }

        else if (key == KeyEvent.VK_DOWN) {
            switch (selection) {
                case CONTINUE:
                    selection = Selection.RESTART;
                    break;
                case RESTART:
                    selection = Selection.EXIT;
                    break;
                case EXIT:
                    selection = Selection.CONTINUE;
                    break;
                default:
                    break;
            }
            return;
        }
        
        else if (key == KeyEvent.VK_SPACE){
            switch (selection) {
                case CONTINUE:
                    gameSystem.setGameState(GameSystem.GameState.IN_GAME);
                    break;
                case RESTART:
                    gameSystem.restart();
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            return;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    private class MenuTAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            processCommands(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }
    }
}
