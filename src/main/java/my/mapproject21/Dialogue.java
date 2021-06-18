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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;

/**
 *
 * @author franc
 */
public class Dialogue {

    private Statement stm;
    private ResultSet rs;
    private Connection conn;
    private Properties dbprops;
    private String language;

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

    public Dialogue(String language) {
        this.setLanguage(language);
    }

    public Dialogue(String language, String dbURL, String user, String password) {
        this.setLanguage(language);

        this.dbprops = new Properties();

        try {
            this.conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        this.dbprops.setProperty("user", user);
        this.dbprops.setProperty("password", password);
    }

    public String getNext() {
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        return null;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setDatabase(String dbURL, String user, String password) {
        this.dbprops = new Properties();

        try {
            this.conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        this.dbprops.setProperty("user", user);
        this.dbprops.setProperty("password", password);
    }

    public boolean hasNext() {
        try {
            if (rs.next()) {
                rs.previous();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        return false;
    }

    public void loadDialogue(String query) {
        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
}
