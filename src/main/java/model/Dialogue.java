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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import model.GameCore.dialogues;

/**
 *
 * @author franc
 */
class Dialogue {

    private Statement stm;
    private ResultSet rs;
    private Connection conn;
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

    public void init(String playerName) {
        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stm.executeUpdate("drop table if exists Dialoghi"); //table if exists fare con controllo file
            stm.executeUpdate("create table if not exists Dialoghi (id int primary key, npc varchar, text varchar)");
            stm.executeUpdate("insert into Dialoghi values (" + dialogues.MERCHANT_FIRST.ordinal() + ", 'Rudolf', 'Olio da lampada, corde, bombe, li vuoi?§Sono tuoi, amico mio, ma solo se te li puoi permettere.§Devi scusarmi, " + playerName + ", ma non ti posso far credito: torna quando sarai un po'' più... ricco!')");
            stm.executeUpdate("insert into Dialoghi values (" + dialogues.MERCHANT_SWORD.ordinal() + ", 'Rudolf', 'Ah, vedo che hai una spada...')");
            stm.executeUpdate("insert into Dialoghi values (" + dialogues.GUARD.ordinal() + ", '???', 'Ehi, cosa guardi?')");
            stm.executeUpdate("insert into Dialoghi values (" + dialogues.JARL_FIRST.ordinal() + ", 'Jarl', 'Uno spettro si aggira per Riverwoord...§Sei la nostra ultima speranza, " + playerName + ".')");
            stm.executeUpdate("insert into Dialoghi values (" + dialogues.KID_FIRST.ordinal() + ", 'Bimbo', '" + playerName + "! " + playerName + "! Ti sfido!§Io invento un numero di quattro cifre (tutte diverse fra loro) e tu lo dovrai indovinare.§Tu mi dirai un numero e io ti dirò quante cifre sono giuste e nella posizione giusta e quante cifre sono giuste ma nella posizione sbagliata.§Attento: hai solo venti tentativi, dopodiché dovrai ricominciare con un altro numero.')");
            stm.executeUpdate("insert into Dialoghi values (" + dialogues.KID_WIN.ordinal() + ", 'Bimbo', 'Sei proprio bravo... eccoti un biscotto!')");
            stm.executeUpdate("insert into Dialoghi values (" + dialogues.KID_LOSE.ordinal() + ", 'Bimbo', 'Eheh! Che scarso! Riproviamo...')");
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    public Dialogue(String language, String dbURL, String user, String password) {
        dbprops = new Properties();

        try {
            this.conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        dbprops.setProperty("user", user);
        dbprops.setProperty("password", password);
    }

    public OrderedPair<String, ListIterator> getDialogue(int dialogueId) {
        
        try {
            rs = stm.executeQuery("select text, npc from Dialoghi where id = " + dialogueId);
            rs.next();
            
            List<String> s = new ArrayList();
            s.addAll(Arrays.asList(rs.getString("text").split("§")));
            
            return new OrderedPair(rs.getString("npc"), s.listIterator());
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        return new OrderedPair(null, null);
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
}
