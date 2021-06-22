package core;

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
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author franc
 */
public class GameCore implements Serializable, GameCoreInterface {

    private static final int LEFT = 1;
    private static final int RIGHT = -1;
    private static final int FORWARD = 0;
    private static final int BACKWARDS = 2;

    private Room currentRoom;
    private Player player;
    private Dialogue dialogue;
    private Map gameMap;

    public GameCore() {
        gameMap = new Map();
        currentRoom = gameMap.getStartingRoom();
        player = new Player();
        dialogue = new Dialogue();
    }

    @Override
    public String getFacingImage() {
        return currentRoom.getImage(getFacingDirection());
    }

    @Override
    public int getFacingDirection() {
        return player.getFacingDirection();
    }

    @Override
    public String getNextDialogue() {
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
    public boolean hasNextDialogue() {
        return dialogue.hasNext();
    }

    @Override
    public void loadDialogue(String query) {
        dialogue.loadDialogue(query);
    }

    @Override
    public void setDialogueLanguage(String language) {
        dialogue.setLanguage(language);
    }

    @Override
    public String getDialogueLanguage() {
        return dialogue.getLanguage();
    }

    private void turn(final int direction) {
        player.setFacingDirection(player.getFacingDirection() + direction);
    }

    @Override
    public void turnRight() {
        turn(RIGHT);
    }

    @Override
    public void turnLeft() {
        turn(LEFT);
    }

    private void walk(final int direction) {
        if (currentRoom.getAdjacentRoom((player.getFacingDirection()) + direction) != null) {
            currentRoom = currentRoom.getAdjacentRoom(player.getFacingDirection() + direction);
        }
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
        try {
            FileOutputStream fileOut = new FileOutputStream("saveGame.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in saveGame.ser");
        } catch (IOException i) {
            //Rivedere catch
            i.printStackTrace();
        }
    }

    @Override
    public GameCore load() {
        try {
            FileInputStream fileIn = new FileInputStream("saveGame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            GameCore gc = (GameCore) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Serialized data loaded");
            return gc;
        } catch (IOException i) {
            //Rivedere questi catch
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Core class not found");
            c.printStackTrace();
            return null;
        }
    }
}
