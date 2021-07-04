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
package engine;

/**
 *
 * @author franc
 */
public class GamePanel {
    private String image;
    private String next;
    
    public GamePanel(String image){
        this.image = image;
    }
    
    public GamePanel(String image, String next){
        this(image);
        this.next = next;
    }

    public String getImage() {
        return image;
    }

    public String getNext() {
        return next;
    }
}
