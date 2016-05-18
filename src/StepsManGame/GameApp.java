/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame;

import StepsManGame.systems.GameSystem;
import StepsManGame.systems.OnlineSystem;
import StepsManGame.view.ViewsMediator;
import javax.swing.JFrame;

/**
 *
 * @author Lucas
 */
public class GameApp extends JFrame{
    
    private GameSystem gameSystem;
    private ViewsMediator viewsMediator;
    private OnlineSystem onlineSystem;
    
    public GameApp() {
        
    }
    
    public void run(){
        gameSystem = new GameSystem();
        viewsMediator = new ViewsMediator();
        onlineSystem = new OnlineSystem();
        
        gameSystem.initialize(viewsMediator, onlineSystem);
        viewsMediator.initialize(gameSystem);
        onlineSystem.initialize(gameSystem);
        
        this.add(viewsMediator);        
        setResizable(false);
        pack();
        setTitle("The Steps Man Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        while(true){
            viewsMediator.repaint();
            try{
                Thread.sleep(100);
            } catch (Exception e){
                
            }
            
            int winner = gameSystem.checkWinner();
            if (winner != 0)
                System.out.println ("WINNER IS PLAYER " + winner);
        }
    }
    
}
