/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.view;

import StepsManGame.logic.Menu;
import StepsManGame.systems.PlayerSystem;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Lucas
 */
public class MenuView {

    private ViewsMediator viewsMediator;
    private Image CONTINUE;
    private Image SPLITSCREEN;
    private Image HOST;
    private Image CLIENT;
    private Image EXIT;
    private Image p1win;
    private Image p2win;
    private Menu attachedMenu;
    private Point menupos;
    private Point winpos;

    public MenuView(Menu menu, ViewsMediator viewsMediator) {
        this.attachedMenu = menu;
        this.viewsMediator = viewsMediator;
    }

    public void loadResources() {
        ImageIcon icon = new ImageIcon("Resources/menus/continue.png");
        CONTINUE = viewsMediator.makeColorTransparent(icon.getImage());
        
        icon = new ImageIcon("Resources/menus/splitscreen.png");
        SPLITSCREEN = viewsMediator.makeColorTransparent(icon.getImage());
        
        icon = new ImageIcon("Resources/menus/host.png");
        HOST = viewsMediator.makeColorTransparent(icon.getImage());
        
        icon = new ImageIcon("Resources/menus/client.png");
        CLIENT = viewsMediator.makeColorTransparent(icon.getImage());
        
        icon = new ImageIcon("Resources/menus/exit.png");
        EXIT = viewsMediator.makeColorTransparent(icon.getImage());
        
        icon = new ImageIcon("Resources/menus/p1win.png");
        p1win = viewsMediator.makeColorTransparent(icon.getImage());
        
        icon = new ImageIcon("Resources/menus/p2win.png");
        p2win = viewsMediator.makeColorTransparent(icon.getImage());
        
        menupos = new Point(40 + 305, viewsMediator.SCREEN_HEIGHT / 2 - 250);
        winpos = new Point(40, viewsMediator.SCREEN_HEIGHT / 2 - 250);
    }

    public void draw(Graphics g) {
        if (attachedMenu.getWinner() == PlayerSystem.Players.PLAYER_1) {
            g.drawImage(p1win, winpos.x, winpos.y, viewsMediator);
        }
        if (attachedMenu.getWinner() == PlayerSystem.Players.PLAYER_2) {
            g.drawImage(p2win, winpos.x, winpos.y, viewsMediator);
        }
        
        if (attachedMenu.getSelection() == Menu.Selection.CONTINUE)
                g.drawImage(CONTINUE, menupos.x, menupos.y, viewsMediator);
        if (attachedMenu.getSelection() == Menu.Selection.SPLIT)
                g.drawImage(SPLITSCREEN, menupos.x, menupos.y, viewsMediator);
        if (attachedMenu.getSelection() == Menu.Selection.HOST)
                g.drawImage(HOST, menupos.x, menupos.y, viewsMediator);
        if (attachedMenu.getSelection() == Menu.Selection.CLIENT)
                g.drawImage(CLIENT, menupos.x, menupos.y, viewsMediator);
        if (attachedMenu.getSelection() == Menu.Selection.EXIT)
                g.drawImage(EXIT, menupos.x, menupos.y, viewsMediator);
    }
}
