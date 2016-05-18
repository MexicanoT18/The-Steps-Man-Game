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
        this.playerSystem = playerSystem;
        this.explosionSystem = explosionSystem;
        platforms = new Platform[5][9];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                platforms[i][j] = new Platform();
                platforms[i][j].initialize(this, viewsMediator);
                platforms[i][j].setLimit(i, j);
            }
        }
        
        for (int i=0; i<5; i++){
            for (int j=0; j<9; j++){
                int[] ai_l4 = {-1, -1, 0, 0, 1, 1};
                int[] aj_l4 = {-1, 0, -1, 1, 0, 1};
                int[] ai_e4 = {-1, -1, -1, 0, 0, 1};
                int[] aj_e4 = {-1, 0, 1, -1, 1, 0};
                int[] ai_b4 = {-1, -1, 0, 0, 1, 1};
                int[] aj_b4 = {0, 1, -1, 1, -1, 0};
                
                int[] ai, aj;
                
                if (j < 4){ai = ai_l4; aj = aj_l4;}
                else if (j == 4){ai = ai_e4; aj = aj_e4;}
                else {ai = ai_b4; aj = aj_b4;}
                
                for (int k=0; k<6; k++){
                    int pi = i + ai[k], pj = j + aj[k];
                    if (isInGame(pj, pi))
                        platforms[i][j].addAdjacentPlatform(platforms[pi][pj]);
                }
            }
        }
        platforms[0][0].setWhosPlatform(PlayerSystem.Players.PLAYER_1);
        platforms[0][0].plantSeed(PlayerSystem.Players.PLAYER_1);
        platforms[0][8].setWhosPlatform(PlayerSystem.Players.PLAYER_2);
        platforms[0][8].plantSeed(PlayerSystem.Players.PLAYER_2);
        
        //if (isInGame(7, 1)) System.out.println ("ESTA CERTO!");
        //else System.out.println ("ESTA ERRADO!");
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
        platform.setSeeds(1);
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
