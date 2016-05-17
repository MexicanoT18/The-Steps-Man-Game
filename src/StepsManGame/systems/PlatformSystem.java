/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.systems;
import StepsManGame.logic.Platform;
import StepsManGame.logic.Player;
import StepsManGame.systems.GameSystem;
import StepsManGame.view.ViewsMediator;

/**
 *
 * @author Lucas
 */
public class PlatformSystem {

    Platform [][] platforms;
    GameSystem gameSystem;
    ExplosionSystem explosionSystem;
    PlayerSystem playerSystem;

    public PlatformSystem() {
        
    }
    
    public void initialize(ViewsMediator viewsMediator, GameSystem gameSystem, PlayerSystem playerSystem, ExplosionSystem explosionSystem) {
        this.gameSystem = gameSystem;
        platforms = new Platform[5][9];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                platforms[i][j] = new Platform();
                platforms[i][j].initialize(this, viewsMediator);
                platforms[i][j].setLimit(i, j);
            }
        }
        platforms[0][0].setWhosPlatform(PlayerSystem.Players.PLAYER_1);
        platforms[0][0].plantSeed(PlayerSystem.Players.PLAYER_1);
        platforms[0][8].setWhosPlatform(PlayerSystem.Players.PLAYER_2);
        platforms[0][8].plantSeed(PlayerSystem.Players.PLAYER_2);
    }
    
    public boolean isInGame(int x, int y) {
        if (y >= 0 && y < 5 && x >= 0 && x < 9) {
            if (x < 5 && x >= y) {
                return true;
            } else if (x >= 5 && 8 - x >= y) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void detonate(Platform platform) {
        explosionSystem.detonate(platform);
    }

    public PlayerSystem.Players other(PlayerSystem.Players whosPlatform) {
        if (whosPlatform == PlayerSystem.Players.PLAYER_1) {
            return PlayerSystem.Players.PLAYER_2;
        }
        if (whosPlatform == PlayerSystem.Players.PLAYER_2) {
            return PlayerSystem.Players.PLAYER_1;
        } else {
            return PlayerSystem.Players.NONE;
        }
    }

    public boolean plantSeed(Player player) {
        return platforms[player.getPosition().y][player.getPosition().x].plantSeed(player.getPlayer());
    }

    public Platform[][] getPlatforms() {
        return platforms;
    }
}
