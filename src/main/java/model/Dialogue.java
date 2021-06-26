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
            stm.executeUpdate("drop table if exists Dialoghi");
            stm.executeUpdate("create table Dialoghi (roomId int, facingDirection int, npc varchar, condition varchar, ita varchar, eng varchar)");
            stm.executeUpdate("insert into Dialoghi values (3, 2, 'Rudolf', null, 'Olio da lampada, corde, bombe, li vuoi?\nSono tuoi, amico mio, ma solo se te li puoi permettere.\nDevi scusarmi, $playerName, ma non ti posso far credito: torna quando sarai un po'' più... ricco!','Lamp oil, rope, bombs, you want it?\nIt''s yours, my friend, as long as you have enough money.\nI''m sorry, I can''t give credit: come back when you''re a little... richer!')");
            stm.executeUpdate("insert into Dialoghi values (3, 2, 'Rudolf', 'sword', 'Ah, vedo che hai una spada...', 'Oh, I see you''ve got a sword...')");
            stm.executeUpdate("insert into Dialoghi values (1, 1, '???', null, 'Ehi, cosa guardi?', 'Hey, what are you looking at?')");
            stm.executeUpdate("insert into Dialoghi values (2, 1, 'Jarl', null, 'Uno spettro si aggira per Riverwoord...\nSei la nostra ultima speranza, $playerName.', 'A spectre is haunting Riverwood...\nYou''re our last hope, $playerName.')");
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

    public String[] getDialogue() {
        String[] query = {"", ""};

        try {
            rs.next();
            query[0] = rs.getString(getLanguage());
            query[1] = rs.getString("npc");
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
