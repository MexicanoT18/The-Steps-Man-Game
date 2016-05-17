/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.logic;

import StepsManGame.systems.PlayerSystem;
import StepsManGame.view.PlatformSystem;
import java.awt.Point;

/**
 *
 * @author Lucas
 */
public class Player
    {
        // Tenta entender ai. A parte mais dificil é entender a constante de tempo
        // tb é bom prestar atenção na relação da classe player com o bombSystem

        // ------- CONSTANTES DE TEMPO ---------
        // -------------------------------------

        // ------- CONSTANTES DE ESTADO --------
        public int STATE_MENU = 0;
        public int STATE_MY_TURN  = 1;
        public int STATE_HIS_TURN = 2;
        // -------------------------------------

        // ------------- ATRIBUTOS -------------
        private int state;
        private int player;
        private Point position;
        // -------------------------------------

        // ----------- REFERENCIAS -------------
        private InputDevice inputDevice;
        private PlayerSystem playerSystem;
        private PlatformSystem platformSystem;
        private GameSystem gameSystem;
        // -------------------------------------

        // ----------- CONSTRUTORES ------------
        public Player(InputDevice inputDevice, int player, PlayerSystem playerSystem, PlatformSystem platformSystem, GameSystem gameSystem)
        {
            this.playerSystem = playerSystem;
            this.platformSystem = platformSystem;
            this.inputDevice = inputDevice;
            this.gameSystem = gameSystem;
            this.player = player;
            if (player == PlayerSystem.PLAYER_1)
            {
                state = STATE_MY_TURN;
                position = new Point(0, 0);
            }
            if (player == PlayerSystem.PLAYER_2)
            {
                state = STATE_HIS_TURN;
                position = new Point(8,0);
            }
        }
        // -------------------------------------

        // -------- GETTER AND SETTER ----------
        public int getState()
        {
            return state;
        }

        public void setState(int state)
        {
            this.state = state;
        }

        public int getPlayer()
        {
            return player;
        }

        public Point getPosition()
        {
            return position;
        }
        // -------------------------------------

        // -------------- MÉTODOS --------------

        public boolean processCommands(GameTime gameTime)
        {
            if (inputDevice == null)
                throw new NullPointerException("O método de input não foi iniciado.");

            CommandTable commandTable = inputDevice.processInput();

            boolean successful = false;
            int dx = 0, dy = 0;

            // Process Movement Input
            
            if (commandTable.plantSeed == CommandState.Released && !platformSystem.getUpdatingExlosions())
            {
                return platformSystem.plantSeed(this);
            }

            if (commandTable.pause == CommandState.Released)
            {
                gameSystem.setGameState(GameSystem.PAUSED);
            }

            if (commandTable.moveUp == CommandState.Released)
            {
                dy = -1;
            }

            if (commandTable.moveDown == CommandState.Released)
            {
                dy = 1;
            }
            if (commandTable.moveLeft == CommandState.Released)
            {
                dx = -1;
            }
            if (commandTable.moveRight == CommandState.Released)
            {
                dx = 1;
            }

            if (platformSystem.isInGame(position.x + dx, position.y + dy))
            {
                successful = true;
            }
            else if ((dx == -1 || dx==1) && platformSystem.isInGame(position.x + dx, position.y -1))
            {
                dy = -1;
                successful = true;
            }

            if (successful && !platformSystem.getUpdatingExlosions())
            {
                position.x += dx;
                position.y += dy;
            }

            return false;
        }

        public void update(GameTime gameTime)
        {
        }
        // -------------------------------------

    }