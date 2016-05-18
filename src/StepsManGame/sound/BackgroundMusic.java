/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.sound;

import StepsManGame.view.ViewsMediator;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Lucas
 */
public class BackgroundMusic {
    
    private ViewsMediator viewsMediator;
    private AudioStream st1;
    private AudioStream st2;
    private final int numSongs = 1;
    
    public BackgroundMusic(ViewsMediator viewsMediator){
        this.viewsMediator = viewsMediator;
    }
    
    public void loadResources(){
        
        InputStream in;
        try{
            in = new FileInputStream("Resources/music/st1.wav");
            st1 = new AudioStream(in);
        } catch (Exception e){
            System.out.println("Não pode inicializar música 1");
        }
/*
        try{
            in = new FileInputStream("Resources/music/st2.wav");
            st2 = new AudioStream(in);
        } catch (Exception e){
            System.out.println("Não pode inicializar música 2");
        }
        */
    }
    
    public void play(){
                
        //if (AudioPlayer.player.isAlive()) return;
        
        Random rn = new Random();
        int randomNum = 1 + (rn.nextInt() % numSongs);
        
        switch(randomNum){
            case 1:
                AudioPlayer.player.start(st1);
                break;
            /*case 2:
                AudioPlayer.player.start(st2);
                break;*/
            default:
                break;
        }
        
    }
    
    
}
