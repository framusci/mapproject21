package engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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

    public Dialogue() {
        dbprops = new Properties();
    }

    public void setDatabase(String dbURL, String dbUser, String dbPassword) {
        try {
            conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        dbprops.setProperty("user", dbUser);
        dbprops.setProperty("password", dbPassword);

        try {
            stm = conn.createStatement();
            stm.executeUpdate("drop table if exists Dialoghi");
            stm.executeUpdate("create table if not exists Dialoghi (id int primary key, text varchar)");
            stm.close();
            
            stm = conn.createStatement();
            stm.executeUpdate("drop table if exists Oggetti");
            stm.executeUpdate("create table if not exists Oggetti (name varchar primary key, text varchar)");
            stm.close();
            
            stm = conn.createStatement();
            stm.executeUpdate("drop table if exists osservazioni");
            stm.executeUpdate("create table if not exists osservazioni (id int primary key, text varchar)");
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public Iterator getDialogue(int dialogueId) {

        try {
            pstm = conn.prepareStatement("SELECT text FROM Dialoghi WHERE id = ?");
            pstm.setInt(1, dialogueId);
            rs = pstm.executeQuery();
            
            rs.next();
            List<String> tmp = Arrays.asList(rs.getString("text").split(separator));
            pstm.close();
            
            return tmp.iterator();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        return null;
    }

    public void addDialogue(int id, String dialogue) {
        try {
            pstm = conn.prepareStatement("INSERT INTO dialoghi VALUES (?,?)");
            pstm.setInt(1, id);
            pstm.setString(2, dialogue);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    public void addItem(String itemName, String description) {
        try {
            pstm = conn.prepareStatement("INSERT INTO oggetti VALUES (?,?)");
            pstm.setString(1, itemName);
            pstm.setString(2, description);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
    
    public String getItem(String itemName){
        String s;
        
        try {
            pstm = conn.prepareStatement("SELECT text FROM oggetti WHERE name = ?");
            pstm.setString(1, itemName);
            rs = pstm.executeQuery();
            rs.next();
            s = rs.getString("text");
            pstm.close();
            
            return s;
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        
        return null;
    }
    
    public String getObservation(int id){
        String s;
        
        try {
            pstm = conn.prepareStatement("SELECT text FROM osservazioni WHERE id = ?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            s = rs.getString("text");
            pstm.close();
            
            return s;
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        
        return null;
    }
    
    public void addObservation(int id, String text){
        try {
            pstm = conn.prepareStatement("INSERT INTO osservazioni VALUES (?,?)");
            pstm.setInt(1, id);
            pstm.setString(2, text);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    public void removeDialogue(int id) {
        try {
            stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM dialoghi WHERE id = " + id);
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    public void removeItem(String itemName) {
        try {
            stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM oggetti WHERE name = " + itemName);
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
}
