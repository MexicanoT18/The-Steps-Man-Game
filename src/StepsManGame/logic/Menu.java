/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.logic;

/**
 *
 * @author Lucas
 */
public class Menu
    {
        public final int CONTINUE = 0;
        public final int RESTART = 1;
        public final int EXIT = 2;

        private int selection;
        InputDevice inputDevice;
        GameSystem gameSystem;
        private int winner;

        public Menu(InputDevice inputDevice, GameSystem gameSystem)
        {
            this.inputDevice = inputDevice;
            this.gameSystem = gameSystem;
            winner = PlayerSystem.NONE;
            selection = CONTINUE;
        }

        public int getSelection()
        {
            return selection;
        }

        public void setWinner(int winner)
        {
            this.winner = winner;
        }

        public int getWinner()
        {
            return winner;
        }

        public void update(GameTime gameTime)
        {
            if (inputDevice == null)
                throw new NullPointerException("O método de input não foi iniciado.");

            CommandTable commandTable = inputDevice.processInput();

            if (commandTable.moveUp == CommandState.Released)
            {
                selection--;
                if (selection < 0) selection = 2;
            }
            if (commandTable.moveDown == CommandState.Released)
            {
                selection++;
                if (selection > 2) selection = 0;
            }
            if (commandTable.plantSeed == CommandState.Released)
            {
                switch (selection)
                {
                    case CONTINUE:
                        gameSystem.setGameState(GameSystem.IN_GAME);
                        break;
                    case RESTART:
                        gameSystem.restart();
                        break;
                    case EXIT:
                        Environment.Exit(0);
                        break;
                    default: break;
                }
            }
        }
    }
