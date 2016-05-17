/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.logic;

import StepsManGame.systems.PlayerSystem;
import StepsManGame.systems.PlatformSystem;
import StepsManGame.view.ViewsMediator;
import java.awt.Point;

/**
 *
 * @author Lucas
 */
public class Platform
    {

        PlatformSystem platformSystem;

        private PlayerSystem.Players whosPlatform;
        private int seeds;
        private int limit;
        private boolean isInGame;
        private Point position;

        public Platform(){
            
        }
        
        public void initialize(PlatformSystem platformSystem, ViewsMediator viewsMediator){
            
            this.platformSystem = platformSystem;
            whosPlatform = PlayerSystem.Players.NONE;
            position = new Point(0, 0);
            seeds = 0;
            limit = -1;
            viewsMediator.attachNewPlatformViewToPlatform(this);
        }

        public Point getPosition()
        {
            return position;
        }

        public int getSeeds()
        {
            return seeds;
        }

        public void setSeeds(int seeds)
        {
            this.seeds = seeds;
            if (seeds == 0) whosPlatform = PlayerSystem.Players.NONE;
        }

        public PlayerSystem.Players getWhosPlatform()
        {
            return whosPlatform;
        }

        public boolean getIsInGame()
        {
            return isInGame;
        }

        public int getLimit()
        {
            return limit;
        }

        public void setLimit(int y, int x)
        {
            position.x = x;
            position.y = y;

            isInGame = platformSystem.isInGame(x, y);

            if (y == 0 && (x == 0 || x == 8)) limit = 2;
            else if (x == 4 && (y == 0 || y == 4)) limit = 3;
            else if (x == y || 8 - x == y) limit = 4;
            else if (y == 0) limit = 4;
            else limit = 6;
        }

        public void setWhosPlatform(PlayerSystem.Players whosPlatform)
        {
            this.whosPlatform = whosPlatform;
        }

        public boolean plantSeed(PlayerSystem.Players whosPlatform)
        {
            if (this.whosPlatform == whosPlatform)
            {
                seeds++;
                if (seeds >= limit)
                {
                    this.detonate();
                }
                return true;
            }
            return false;
        }

        public void detonate()
        {
            platformSystem.detonate(this);
        }
    }