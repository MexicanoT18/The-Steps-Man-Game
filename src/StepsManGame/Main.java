/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame;

/**
 *
 * @author Lucas
 */
public class Main {
    
    static GameApp app;
    
    public static void main(String[] args){
        System.out.println("Jogo inicializado");
        
        app = new GameApp();
        app.run();
    }
    
}
