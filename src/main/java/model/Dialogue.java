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

/**
 * Gestisce il database dei dialoghi.
 * Permette la creazione del database, l'aggiunta e la lettura dei dialoghi.
 */
class Dialogue {

    private ResultSet rs;
    private Connection conn;
    private Properties dbprops;
    private Statement stm;
    private PreparedStatement pstm;
    private String separator;

    /**
     * Costruisce il database in cui sono memorizzati i dialoghi con i parametri specificati.
     * 
     * @param dbURL L'URL del database.
     * @param dbUser Lo username del database.
     * @param dbPassword La password del database.
     * @param separator Il separatore dei dialoghi.
     */
    public Dialogue(String dbURL, String dbUser, String dbPassword, String separator) {
        this();
        this.setDatabase(dbURL, dbUser, dbPassword);
        this.separator = separator;
    }

    /**
     * Costruisce un database vuoto.
     */
    public Dialogue() {
        dbprops = new Properties();
    }

    /**
     * Imposta il database in cui sono memorizzati i dialoghi.
     * 
     * @param dbURL L'URL del database JDBC.
     * @param dbUser Lo username del database.
     * @param dbPassword La password del database.
     */
    public void setDatabase(String dbURL, String dbUser, String dbPassword) {
        try {
            conn = DriverManager.getConnection(dbURL, dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        dbprops.setProperty("user", dbUser);
        dbprops.setProperty("password", dbPassword);
    }

    /**
     * Inizializza il database.
     * Crea la tabella se non esiste e cancella tutte le righe se esiste.
     */
    public void init(){
        try {
            stm = conn.createStatement();
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS dialoghi (id INT NOT NULL PRIMARY KEY, text VARCHAR)");
            stm.close();
            
            stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM dialoghi");
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    /**
     * Imposta il separatore dei dialoghi.
     * Un dialogo troppo lungo potrebbe non poter essere visualizzato tutto insieme in un riquadro.
     * Per cui, il separatore permette di identificare dove il dialogo può essere "spezzato" in più frasi,
     * in modo che possano essere visualizzate una dopo l'altra.
     * 
     * @param separator Il separatore dei dialoghi.
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * Restituisce la lista contenente le frasi del dialogo con id <tt>dialogueId</tt>.
     * Ciascun elemento della lista è una porzione di dialogo. La separazione tra una porzione e l'altra
     * è ottenuta inserendo i dialoghi e utilizzando un separatore.
     * 
     * @param dialogueId L'id del dialogo.
     * @return La lista contenente il dialogo.
     */
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

    /**
     * Aggiunge un dialogo con un id.
     * 
     * @param id L'id del dialogo.
     * @param dialogue Il dialogo.
     */
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
}
