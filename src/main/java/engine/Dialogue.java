package engine;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

/**
 *
 * @author franc
 */
class Dialogue {

    private ResultSet rs;
    private Connection conn;
    private Properties dbprops;
    private Statement stm;
    private PreparedStatement pstm;
    private String separator;

    public Dialogue(String dbURL, String user, String password, String separator) {
        dbprops = new Properties();
        
        try {
            conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        
        dbprops.setProperty("user", user);
        dbprops.setProperty("password", password);

        try {
            stm = conn.createStatement();
            stm.executeUpdate("create table if not exists Dialoghi (id int primary key, npc varchar, text varchar)");
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        
        this.separator = separator;
    }
    
    public Dialogue(){
        dbprops = new Properties();
    }
    
    public void setDatabase(String dbURL, String dbUser, String dbPassword){
        try {
            conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        
        dbprops.setProperty("user", dbUser);
        dbprops.setProperty("password", dbPassword);
        
        try {
            stm = conn.createStatement();
            stm.executeUpdate("create table if not exists Dialoghi (id int primary key, npc varchar, text varchar)");
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
    
    public void setSeparator(String separator){
        this.separator = separator;
    }

    public Couple<String, ListIterator> getDialogue(int dialogueId) {

        try {
            pstm = conn.prepareStatement("SELECT text, npc FROM Dialoghi WHERE id = ?");
            pstm.setInt(1, dialogueId);
            rs = pstm.executeQuery();
            pstm.close();
            rs.next();

            List<String> s = new ArrayList();
            s.addAll(Arrays.asList(rs.getString("text").split(separator)));

            return new Couple(rs.getString("npc"), s.listIterator());
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        return new Couple(null, null);
    }

    public void addDialogue(int id, String name, String dialogue) {
        try {
            pstm = conn.prepareStatement("INSERT INTO dialoghi VALUES (?,?,?)");
            pstm.setInt(1, id);
            pstm.setString(2, name);
            pstm.setString(3, dialogue);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
    
    public void removeDialogue(int id){
        try {
            stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM dialoghi WHERE id = " + id);
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
}
