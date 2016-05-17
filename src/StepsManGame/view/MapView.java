/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Lucas
 */
public class MapView {
    
    private Image background;
    
    ViewsMediator viewsMediator;
    
    public MapView(ViewsMediator viewsMediator){
        this.viewsMediator = viewsMediator;
    }
    
    public void loadResources(){
        ImageIcon bg = new ImageIcon("Resources/backgrounds/bg.png");
        background = bg.getImage();
    }
    
    public void draw(Graphics g){
        g.drawImage(background, 0, 0, viewsMediator);
    }
}
