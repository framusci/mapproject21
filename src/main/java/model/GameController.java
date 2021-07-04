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
import model.utils.Couple;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author franc
 */
public class GameController {

    //Player movement
    public static final int LEFT = 1;
    public static final int RIGHT = -1;
    public static final int FORWARD = 0;
    public static final int BACKWARDS = 2;

    private Room currentRoom;
    private Player player;
    private SaveGame saveGame;
    private Dialogue dialogue;

    private Map<Integer, Boolean> events;
    private Map<String, Room> gameMap; //getStartingRoom; getRoomById;   

    public GameController() {
        player = new Player();
        dialogue = new Dialogue();
        events = new HashMap<>();
        gameMap = new HashMap<>();
    }

    public void addDialogue(int id, String name, String text) {
        dialogue.addDialogue(id, name, text);
    }

    public void setDialogueDatabase(String dbURL, String dbUser, String dbPassword) {
        dialogue.setDatabase(dbURL, dbUser, dbPassword);
    }

    public void setDialogueSeparator(String s) {
        dialogue.setSeparator(s);
    }

    /**
     * Adds the specified event to the event map
     *
     * @param evt
     */
    public void addEvent(int evt) {
        events.put(evt, false);
    }

    /**
     * Adds the specified room to the map. The first element in the array is the
     * starting room.
     *
     * @param room
     */
    public void createRooms(String... room) {
        for (String r : room) {
            gameMap.put(r, new Room(r));
        }
        
        currentRoom = gameMap.get(room[0]);
    }

    /**
     * IMPORTANT: add file extension to image names.
     * @param roomName
     * @param northImg
     * @param westImg
     * @param southImg
     * @param eastImg 
     */
    public void addImages(String roomName, String northImg, String westImg, String southImg, String eastImg) {
        Room tmpRoom = gameMap.get(roomName);
        
        tmpRoom.setAdjacentImage(0, northImg);
        tmpRoom.setAdjacentImage(1, westImg);
        tmpRoom.setAdjacentImage(2, southImg);
        tmpRoom.setAdjacentImage(3, eastImg);
        
        //gameMap.put(roomName, tmpRoom);
    }
    
    public void addRooms(String roomName, String northRoom, String westRoom, String southRoom, String eastRoom){
        Room tmpRoom = gameMap.get(roomName);
        
        tmpRoom.setAdjacentRoom(0, northRoom);
        tmpRoom.setAdjacentRoom(1, westRoom);
        tmpRoom.setAdjacentRoom(2, southRoom);
        tmpRoom.setAdjacentRoom(3, eastRoom);
    }

    public void setPlayerName(String name) {
        player.setName(name);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public List getPlayerInventory() {
        return player.getInventory();
    }

    public void addToInventory(String item) {
        player.takeItem(item);
    }

    public void removeFromInventory(String item) {
        player.leaveItem(item);
    }

    public String getFacingImageFileName() {
        return currentRoom.getImage(player.getFacingDirection());
    }

    public int getFacingDirection() {
        return player.getFacingDirection();
    }

    public Couple<String, ListIterator> loadDialogue(int dl) {
        return dialogue.getDialogue(dl);
    }

    public void turnRight() {
        turn(RIGHT);
    }

    public void turnLeft() {
        turn(LEFT);
    }

    public void walkForward() {
        walk(FORWARD);
    }

    public void walkBackwards() {
        walk(BACKWARDS);
    }

    public void save() {
        saveGame = new SaveGame(player.getName(), player.getFacingDirection(), currentRoom.getName(), player.getInventory());

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
        currentRoom = gameMap.get(saveGame.getRoomName());
        player.setInventory(saveGame.getInventory());
    }

    private void turn(final int direction) {
        player.setFacingDirection(player.getFacingDirection() + direction);
    }

    private void walk(final int direction) {
        if (currentRoom.getAdjacentRoom((player.getFacingDirection()) + direction) != null) {
            currentRoom = gameMap.get(currentRoom.getAdjacentRoom(player.getFacingDirection() + direction));
        }
    }

    public boolean hasHappened(int evt) {
        return events.get(evt);
    }

    public void makeHappen(int evt) {
        events.put(evt, true);
    }
}
