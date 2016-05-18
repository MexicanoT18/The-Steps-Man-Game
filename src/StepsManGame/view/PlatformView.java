/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.view;

import StepsManGame.logic.Platform;
import StepsManGame.systems.PlayerSystem;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Lucas
 */
public class PlatformView {

    private Image point1player1;
    private Image point2player1;
    private Image point3player1;
    private Image point4player1;
    private Image point5player1;
    private Image point1player2;
    private Image point2player2;
    private Image point3player2;
    private Image point4player2;
    private Image point5player2;
    private Image boom1;
    private Image boom2;
    private Image error;

    private Point[][] positions;

    private ViewsMediator viewsMediator;
    private Platform attachedPlatform;

    public PlatformView(ViewsMediator viewsMediator, Platform platform) {
        this.viewsMediator = viewsMediator;
        attachedPlatform = platform;
    }

    public void loadResources() {
        ImageIcon icon = new ImageIcon("Resources/tiles/1point1player.png");
        point1player1 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/2point1player.png");
        point2player1 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/3point1player.png");
        point3player1 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/4point1player.png");
        point4player1 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/5point1player.png");
        point5player1 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/1point2player.png");
        point1player2 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/2point2player.png");
        point2player2 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/3point2player.png");
        point3player2 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/4point2player.png");
        point4player2 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/5point2player.png");
        point5player2 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/boom1.png");
        boom1 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/boom2.png");
        boom2 = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("Resources/tiles/error.png");
        error = viewsMediator.makeColorTransparent(icon.getImage());

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
        Point position = positions[attachedPlatform.getPosition().y][attachedPlatform.getPosition().x];
        Point center = new Point(point1player1.getWidth(viewsMediator) / 2, point1player1.getHeight(viewsMediator) / 2);

        if (attachedPlatform.getWhosPlatform() == PlayerSystem.Players.NONE && attachedPlatform.getSeeds() != 0) {
            g.drawImage(error, position.x - center.x, position.y - center.y, viewsMediator);
        }

        if (attachedPlatform.getWhosPlatform() == PlayerSystem.Players.PLAYER_1) {
            if (attachedPlatform.getSeeds() >= attachedPlatform.getLimit()) {
                g.drawImage(boom1, position.x - center.x, position.y - center.y, viewsMediator);
                return;
            }
            switch (attachedPlatform.getSeeds()) {
                case 1:
                    g.drawImage(point1player1, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                case 2:
                    g.drawImage(point2player1, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                case 3:
                    g.drawImage(point3player1, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                case 4:
                    g.drawImage(point4player1, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                case 5:
                    g.drawImage(point5player1, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                default:
                    break;
            }
        }
        if (attachedPlatform.getWhosPlatform() == PlayerSystem.Players.PLAYER_2) {
            if (attachedPlatform.getSeeds() >= attachedPlatform.getLimit()) {
                g.drawImage(boom2, position.x - center.x, position.y - center.y, viewsMediator);
                return;
            }
            switch (attachedPlatform.getSeeds()) {
                case 1:
                    g.drawImage(point1player2, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                case 2:
                    g.drawImage(point2player2, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                case 3:
                    g.drawImage(point3player2, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                case 4:
                    g.drawImage(point4player2, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                case 5:
                    g.drawImage(point5player2, position.x - center.x, position.y - center.y, viewsMediator);
                    break;
                default:
                    break;
            }
        }
    }
}
