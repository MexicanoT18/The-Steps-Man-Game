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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
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

    public void attachNewPlayerViewToPlayer(Player player) {
        PlayerView view = new PlayerView(player, this);
        view.loadResources();
        if (player.getPlayer() == PlayerSystem.Players.PLAYER_1) {
            playerView1 = view;
        }
        if (player.getPlayer() == PlayerSystem.Players.PLAYER_2) {
            playerView2 = view;
        }
    }
/*
    public void attachNewMenuViewToMenu(Menu menu) {
        menuView = new MenuView(menu, gameSystem);
        menuView.LoadResources(content);
    }
*/
    public void attachNewPlatformViewToPlatform(Platform platform) {
        PlatformView view = new PlatformView(this, platform);
        view.loadResources();
        platformViews.put(platform, view);
    }

    // Aqui simplesmente percorremos as listas e mostramos as views.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        mapView.draw(g);
        
        for (Map.Entry<Platform, PlatformView> entry : platformViews.entrySet())
        {
            entry.getValue().draw(g);
        }
        
        playerView1.draw(g);
        playerView2.draw(g);
    }

    public Image makeColorTransparent(Image im) {
        ImageFilter filter = new RGBImageFilter() {
            @Override
            public final int filterRGB(int x, int y, int rgb) {
                Color c = new Color(rgb);

                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                int alpha = (red == 34 && green == 177 && blue == 76 ? 0 : 255);
                c = new Color(red, green, blue, alpha);
                
                return c.getRGB();
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
}
