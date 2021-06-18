package my.mapproject21;

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
public class GameCore {

    private final int LEFT = 1;
    private final int RIGHT = -1;
    private final int FORWARD = 0;
    private final int BACKWARDS = 2;

    private Room currentRoom;
    private Player player;

    public GameCore(Room startingRoom, Player player) {
        this.currentRoom = startingRoom;
        this.player = player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    private void turn(final int direction) {
        this.player.setFacingDirection(this.player.getFacingDirection() + direction);
    }

    public void turnRight() {
        this.turn(RIGHT);
    }

    public void turnLeft() {
        this.turn(LEFT);
    }

    private void walk(final int direction) {
        if (this.currentRoom.getAdjacentRoom((player.getFacingDirection()) + direction) != null) {
            this.currentRoom = this.currentRoom.getAdjacentRoom(player.getFacingDirection() + direction);
        }
    }

    public void walkForward() {
        this.walk(FORWARD);
    }

    public void walkBackwards() {
        this.walk(BACKWARDS);
    }
}
