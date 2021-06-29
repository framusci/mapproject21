/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class JabberClient {

    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;
    private static InetAddress addr;

    public void attempt(String s) {
        out.println(s);
    }
    
    public void run(){
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
        } catch (Exception ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
}
