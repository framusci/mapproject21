package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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
    }
    
    public void init(){
        try {
            
            stm = conn.createStatement();
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS dialoghi (id INT PRIMARY KEY, text VARCHAR)");
            stm.close();
            
            stm = conn.createStatement();
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS oggetti (name VARCHAR PRIMARY KEY, text VARCHAR)");
            stm.close();
            
            stm = conn.createStatement();
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS osservazioni (id INT PRIMARY KEY, text VARCHAR)");
            stm.close();
            
            stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM dialoghi");
            stm.close();
            
            stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM oggetti");
            stm.close();
            
            stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM osservazioni");
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public List getDialogue(int dialogueId) {

        try {
            pstm = conn.prepareStatement("SELECT text FROM dialoghi WHERE id = ?");
            pstm.setInt(1, dialogueId);
            rs = pstm.executeQuery();
            
            rs.next();
            List<String> dialogue = Arrays.asList(rs.getString("text").split(separator));
            pstm.close();
            
            return dialogue;
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
}
