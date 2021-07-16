package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Gestisce il client che si connette al server del minigioco.
 */
class MinigameJabberClient {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private InetAddress addr;
    
    /**
     * Effettua un tentativo del minigioco.
     * 
     * @param s Il numero inserito per effettuare il tentativo.
     */
    public void attempt(String s) {
        out.println(s);
    }

    /**
     * Si connette al server del minigioco.
     */
    public void connect(){
        try {
            addr = InetAddress.getByName("localhost");
            socket = new Socket(addr, 6666);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); // Flush automatico con PrintWriter:
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
    
    /**
     * Restituisce il risultato del tentativo.
     * 
     * @return L'esito.
     */
    public String getResult() {
        try {
            return in.readLine();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
}
