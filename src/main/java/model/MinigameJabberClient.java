package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author franc
 */
class MinigameJabberClient {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private InetAddress addr;

    public void attempt(String s) {
        out.println(s);
    }
    
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
    
    public String getResult() {
        try {
            return in.readLine();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
}
