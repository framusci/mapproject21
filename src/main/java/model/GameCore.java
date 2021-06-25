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

/**
 *
 * @author franc
 */
public class GameCore implements GameCoreInterface {

    private static final int LEFT = 1;
    private static final int RIGHT = -1;
    private static final int FORWARD = 0;
    private static final int BACKWARDS = 2;

    private Room currentRoom;
    private Player player;
    private Dialogue dialogue;
    private GameMap gameMap;
    private SaveGame saveGame;

    public GameCore() {
        gameMap = new GameMap();
        currentRoom = gameMap.getStartingRoom();
        player = new Player();
        dialogue = new Dialogue();
        dialogue.setDatabase("jdbc:h2:./db/store", "sa", "");
        //dialogue.init();
    }

    @Override
    public void setPlayerName(String name){
        player.setName(name);
    }
    
    @Override
    public List getPlayerInventory(){
        return player.getInventory();
    }
    
    public void loadObservation(){
        dialogue.loadQuery("select " + dialogue.getLanguage() + ", npc from Dialoghi where roomId = " + currentRoom.getId() + " AND facingDirection = " + player.getFacingDirection() + " AND npc = '$playerName$' order by sequence");
    } 
    
    public int getRoomId(){
        return currentRoom.getId();
    }
    
    @Override
    public String[] getObservation(){
        return dialogue.getNext();
    }
    
    @Override
    public void addToInventory(Object item){
        player.takeItem((String)item);
    }
    
    @Override
    public void removeFromInventory(Object item){
        player.leaveItem((String)item);
    }
    
    @Override
    public String getFacingImage() {
        return currentRoom.getImage(player.getFacingDirection());
    }

    @Override
    public int getFacingDirection() {
        return player.getFacingDirection();
    }

    @Override
    public String[] getNextDialogue() {
        return dialogue.getNext();
    }

    @Override
    public String getPlayerName() {
        return player.getName();
    }

    @Override
    public void setDialogueDatabase(String dbURL, String user, String password) {
        dialogue.setDatabase(dbURL, user, password);
    }

    @Override
    public void loadDialogue() {
        dialogue.loadQuery("select " + dialogue.getLanguage() + ", npc from Dialoghi where roomId = " + currentRoom.getId() + " AND facingDirection = " + player.getFacingDirection() + " AND npc != '$playerName$' order by sequence");
    }

    @Override
    public void setDialogueLanguage(String language) {
        dialogue.setLanguage(language);
    }

    @Override
    public String getDialogueLanguage() {
        return dialogue.getLanguage();
    }

    @Override
    public void turnRight() {
        turn(RIGHT);
    }

    @Override
    public void turnLeft() {
        turn(LEFT);
    }

    @Override
    public void walkForward() {
        walk(FORWARD);
    }

    @Override
    public void walkBackwards() {
        walk(BACKWARDS);
    }

    @Override
    public void save() {
        saveGame = new SaveGame(player.getName(), player.getFacingDirection(), currentRoom.getId(), player.getInventory(), dialogue.getLanguage());
        
        try {
            BufferedWriter outputStream = new BufferedWriter(new FileWriter("saveGame.json"));
            outputStream.write(new Gson().toJson(saveGame));
            outputStream.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    @Override
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
        dialogue.setLanguage(saveGame.getLanguage());
    }
    
    private void turn(final int direction) {
        player.setFacingDirection(player.getFacingDirection() + direction);
    }
 
    private void walk(final int direction) {
        if (currentRoom.getAdjacentRoom((player.getFacingDirection()) + direction) != null) {
            currentRoom = currentRoom.getAdjacentRoom(player.getFacingDirection() + direction);
        }
    }
}
