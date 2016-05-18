/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.logic;

import StepsManGame.systems.ExplosionSystem;
import StepsManGame.systems.GameSystem;
import StepsManGame.systems.OnlineSystem;
import StepsManGame.systems.PlatformSystem;
import StepsManGame.systems.PlayerSystem;
import StepsManGame.view.ViewsMediator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class Menu implements ActionListener{
    
    public enum Selection{
        CONTINUE, SPLIT, HOST, CLIENT, EXIT
    }

    private Selection selection;
    private GameSystem gameSystem;
    private PlayerSystem playerSystem;
    private OnlineSystem onlineSystem;
    private PlatformSystem platformSystem;
    private ExplosionSystem explosionSystem;
    private PlayerSystem.Players winner;
    private static MenuTAdapter adapter;

    public Menu() {
        winner = PlayerSystem.Players.NONE;
        selection = Selection.CONTINUE;
    }

    public void initialize(GameSystem gameSystem, ViewsMediator viewsMediator, 
            PlatformSystem platformSystem, PlayerSystem playerSystem, ExplosionSystem explosionSystem,
            OnlineSystem onlineSystem) {
        
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
                case SPLIT:
                    selection = Selection.CONTINUE;
                    break;
                case HOST:
                    selection = Selection.SPLIT;
                    break;
                case CLIENT:
                    selection = Selection.HOST;
                    break;
                case EXIT:
                    selection = Selection.CLIENT;
                    break;
                default:
                    break;
            }
            return;
        }

        else if (key == KeyEvent.VK_DOWN) {
            switch (selection) {
                case CONTINUE:
                    selection = Selection.SPLIT;
                    break;
                case SPLIT:
                    selection = Selection.HOST;
                    break;
                case HOST:
                    selection = Selection.CLIENT;
                    break;
                case CLIENT:
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
                case SPLIT:
                    gameSystem.restart();
                    break;
                case HOST:
                {
                    try {
                        onlineSystem.createServer();
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;
                case CLIENT:
                {
                    try {
                        onlineSystem.createClient();
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
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
