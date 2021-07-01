package model;

/*
 * Copyright (C) 2021 franc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author franc
 */
public class GameCore {

    private static final int LEFT = 1;
    private static final int RIGHT = -1;
    private static final int FORWARD = 0;
    private static final int BACKWARDS = 2;
    
    public static final String WIN = MinigameJabberServer.WIN_PHRASE;
    public static final String LOSE = MinigameJabberServer.LOSE_PHRASE;
    
    public static final String KID_INTERACTION = "43";
    public static final String GUARD_INTERACTION = "11";
    public static final String MERCHANT_INTERACTION = "32";

    private Room currentRoom;
    private Player player;
    private Dialogue dialogue;
    private GameMap gameMap;
    private SaveGame saveGame;
    private MinigameJabberClient playerClient;
    private MinigameJabberServer server;

    public GameCore() {
        gameMap = new GameMap();
        currentRoom = gameMap.getStartingRoom();
        player = new Player();
        dialogue = new Dialogue("jdbc:h2:./db/store", "sa", "");
        playerClient = new MinigameJabberClient();
        server = new MinigameJabberServer();
    }

    public String getEvent() {
        return String.valueOf(currentRoom.getId()) + String.valueOf(player.getFacingDirection());
    }

    public void startMiniGame() {
        server.start();
        playerClient.run();
    }

    public void guessGame(String s) {
        playerClient.attempt(s);
    }

    public String getGameResult() {
        return playerClient.getResult();
    }

    public enum dialogues {
        MERCHANT_FIRST, MERCHANT_SWORD, GUARD, KID_FIRST, KID_LOSE, KID_WIN, JARL_FIRST, JARL_END,
    }

    public void setPlayerName(String name) {
        player.setName(name);
    }

    public List getPlayerInventory() {
        return player.getInventory();
    }

    public int getRoomId() {
        return currentRoom.getId();
    }

    public void addToInventory(String item) {
        player.takeItem(item);
    }

    public void removeFromInventory(String item) {
        player.leaveItem(item);
    }

    public String getFacingImageFileName() {
        return currentRoom.getImage();
    }

    public int getFacingDirection() {
        return player.getFacingDirection();
    }

    public void setDialogueDatabase(String dbURL, String user, String password) {
        dialogue.setDatabase(dbURL, user, password);
    }

    public OrderedPair<String, ListIterator> loadDialogue(dialogues dl) {
        return dialogue.getDialogue(dl.ordinal());
    }

    public void turnRight() {
        currentRoom.nextPanel();
    }

    public void turnLeft() {
        currentRoom.previousPanel();
    }

    public void walkForward() {
        if(currentRoom.getForward() != null){
            currentRoom = currentRoom.getForward();
        }
    }

    public void save() {
        saveGame = new SaveGame(player.getName(), player.getFacingDirection(), currentRoom.getId(), player.getInventory());

        try {
            BufferedWriter outputStream = new BufferedWriter(new FileWriter("saveGame.json"));
            outputStream.write(new Gson().toJson(saveGame));
            outputStream.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    public void load() {
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader("saveGame.json"));
            saveGame = new Gson().fromJson(inputStream, SaveGame.class);
            inputStream.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        player.setName(saveGame.getPlayerName());
        player.setFacingDirection(saveGame.getFacingDirection());
        currentRoom = gameMap.getRoomById(saveGame.getRoomId());
        player.setInventory(saveGame.getInventory());
    }
}
