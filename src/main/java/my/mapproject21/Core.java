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

/**
 *
 * @author franc
 */
public class Core {
    private final int LEFT = 1;
    private final int RIGHT = -1;
    private final int FORWARD = 0;
    private final int BACKWARDS = 2;
    
    public Room currentRoom;
    public Player player;
    
    public Core(Room startingRoom, Player player){
        this.currentRoom = startingRoom;
        this.player = player;
    }
    
    private void turn(final int direction){
        this.player.setFacingDirection(this.player.getFacingDirection() + direction);
    }
    
    public void turnRight(){
        this.turn(RIGHT);
    }
    
    public void turnLeft(){
        this.turn(LEFT);
    }
    
    private void walk(final int direction){
        if (this.currentRoom.getAdjacentRoom((player.getFacingDirection()) + direction) != null) {
            this.currentRoom = this.currentRoom.getAdjacentRoom(player.getFacingDirection() + direction);
        }
    }
    
    public void walkForward(){
        this.walk(FORWARD);
    }
    
    public void walkBackwards(){
        this.walk(BACKWARDS);
    }
}
