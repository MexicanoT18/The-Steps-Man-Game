/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.view;

import StepsManGame.logic.*;
import StepsManGame.sound.BackgroundMusic;
import StepsManGame.systems.GameSystem;
import StepsManGame.systems.PlayerSystem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Lucas
 */
public class ViewsMediator extends JPanel{

    MapView mapView;
    PlayerView playerView1;
    PlayerView playerView2;
    Map<Platform, PlatformView> platformViews;
    MenuView menuView;
    BackgroundMusic bgm;
    
    GameSystem gameSystem;
    private PlayerSystem.Players whosTurn;
    
    private final int SCREEN_WIDTH = 965;
    private final int SCREEN_HEIGHT = 611;
    
    public ViewsMediator() {
        platformViews = new HashMap<>();
        bgm = new BackgroundMusic(this);
        mapView = new MapView(this);
    }
    
    public void initialize(GameSystem gameSystem) {
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        
        this.gameSystem = gameSystem;
        mapView.loadResources();
    }
    
    public void restart() {
        platformViews = new HashMap<>();
    }

    private List<PlatformView> getPlatformViews() {
        return new ArrayList<PlatformView>(platformViews.values());
    }

    /*public void attachNewPlayerViewToPlayer(Player player) {
        PlayerView view = new PlayerView(player, this);
        view.LoadResources();
        if (player.getPlayer() == PlayerSystem.Players.PLAYER_1) {
            playerView1 = view;
        }
        if (player.getPlayer() == PlayerSystem.Players.PLAYER_2) {
            playerView2 = view;
        }
    }

    public void attachNewMenuViewToMenu(Menu menu) {
        menuView = new MenuView(menu, gameSystem);
        menuView.LoadResources(content);
    }

    public void attachNewPlatformViewToPlatform(Platform platform) {
        PlatformView view = new PlatformView(platform);
        view.LoadResources(content);
        platformViews[platform] = view;
    }

    // Aqui simplesmente percorremos as listas e mostramos as views.*/
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        mapView.draw(g);
    }
}
