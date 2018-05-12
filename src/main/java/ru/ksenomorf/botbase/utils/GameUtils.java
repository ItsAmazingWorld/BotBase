/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.utils;

import net.dv8tion.jda.core.entities.Game;

public class GameUtils {
    /**
     * Returns GameType accuse of a String
     * @param gameType String GameType
     * @return GameType
     */
    public static Game.GameType getGameType(String gameType){
        Game.GameType gameType1;
        switch(gameType.toLowerCase()){
            case "playing": {
                gameType1 = Game.GameType.DEFAULT;
                break;
            }case "watching": {
                gameType1 = Game.GameType.WATCHING;
                break;
            }case "listening": {
                gameType1 = Game.GameType.LISTENING;
                break;
            }default: {
                gameType1 = Game.GameType.DEFAULT;
            }
        }
        return gameType1;
    }
}
