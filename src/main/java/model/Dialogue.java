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
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author franc
 */
//Magari rifare con interfaccia? 
class Dialogue {

    private Statement stm;
    private ResultSet rs;
    private Connection conn;
    private String language;
    private Properties dbprops;

    public Dialogue(String dbURL, String user, String password) {
        dbprops = new Properties();

        try {
            conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        dbprops.setProperty("user", user);
        dbprops.setProperty("password", password);
    }

    public Dialogue() {
        dbprops = new Properties();
    }

    public void init() {

        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stm.executeUpdate("create table if not exists Dialoghi (sequence int, roomId int, facingDirection int, ita varchar, eng varchar, npc varchar)");
            stm.executeUpdate("insert into Dialoghi values (1, 3, 2, 'Ciao!', 'Hello!', 'Rudolf')");
            stm.executeUpdate("insert into Dialoghi values (2, 3, 2, 'Torna quando sei un po'' più... ricco!', 'Come back when you''re a little... richer!', 'Rudolf')");
            stm.executeUpdate("insert into Dialoghi values (1, 1, 1,'Perché mi guarda male?', 'Why does he look at me that way?', '$playerName$')");
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    public Dialogue(String language, String dbURL, String user, String password) {
        setLanguage(language);

        dbprops = new Properties();

        try {
            this.conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        dbprops.setProperty("user", user);
        dbprops.setProperty("password", password);
    }

    public String[] getNext() {
        String[] query = {"", ""};
        
        try {
            if (rs.next()) {
                query[0] = rs.getString(getLanguage());
                query[1] = rs.getString("npc");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        
        return query;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDatabase(String dbURL, String user, String password) {
        try {
            conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        dbprops.setProperty("user", user);
        dbprops.setProperty("password", password);
    }

    public String getLanguage() {
        return language;
    }

    public void loadQuery(String query) {
        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
}
