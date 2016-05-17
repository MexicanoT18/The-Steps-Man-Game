/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.view;

import StepsManGame.logic.Player;
import StepsManGame.systems.PlayerSystem;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Lucas
 */
public class PlayerView{
    
    ViewsMediator viewsMediator;
    Player attachedPlayer;
    
    private Image player;

    private Point[][] positions;
    
    public PlayerView(Player player, ViewsMediator viewsMediator){
        this.attachedPlayer = player;
        this.viewsMediator = viewsMediator;
    }
    
    public void loadResources(){
        ImageIcon icon = new ImageIcon("Resources/tiles/error.png");
        if (attachedPlayer.getPlayer() == PlayerSystem.Players.PLAYER_1)
            icon = new ImageIcon("Resources/tiles/player1.png");;
        if (attachedPlayer.getPlayer() == PlayerSystem.Players.PLAYER_2)
            icon = new ImageIcon("Resources/tiles/player2.png");
        player = viewsMediator.makeColorTransparent(icon.getImage());
        
        positions = new Point[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                positions[i][j] = new Point(0, 0);
            }
        }
        positions[0][0] = new Point(103, 302);
        positions[0][1] = new Point(200, 247);
        positions[0][2] = new Point(295, 192);
        positions[0][3] = new Point(389, 138);
        positions[0][4] = new Point(483, 83);
        positions[0][5] = new Point(578, 139);
        positions[0][6] = new Point(674, 193);
        positions[0][7] = new Point(769, 249);
        positions[0][8] = new Point(863, 304);
        positions[1][1] = new Point(202, 357);
        positions[1][2] = new Point(295, 301);
        positions[1][3] = new Point(391, 247);
        positions[1][4] = new Point(485, 193);
        positions[1][5] = new Point(579, 247);
        positions[1][6] = new Point(674, 302);
        positions[1][7] = new Point(770, 358);
        positions[2][2] = new Point(297, 412);
        positions[2][3] = new Point(391, 356);
        positions[2][4] = new Point(485, 302);
        positions[2][5] = new Point(580, 358);
        positions[2][6] = new Point(674, 414);
        positions[3][3] = new Point(390, 467);
        positions[3][4] = new Point(485, 411);
        positions[3][5] = new Point(581, 468);
        positions[4][4] = new Point(485, 521);
        
    }
    
    public void draw(Graphics g) {
        Point position = positions[attachedPlayer.getPosition().y][attachedPlayer.getPosition().x];
        Point center = new Point(player.getWidth(viewsMediator) / 2, player.getHeight(viewsMediator) / 2);
        
        if (attachedPlayer.getTurnState() == Player.TurnState.MY_TURN)
            g.drawImage(player, position.x - center.x, position.y - center.y, viewsMediator);
    }
    
}
