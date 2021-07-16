package game;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Gestisce il server del minigioco.
 */
class MinigameJabberServer extends Thread {

    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private String strGuess;
    private ServerSocket s;
    public static final String WIN_PHRASE = "Hai vinto!";
    public static final String LOSE_PHRASE = "Hai perso!";
    private int attempts;
    private static final int MAX_ATTEMPTS = 20;
    private boolean win;
    private String strToGuess;
    private String result;

    private int i, j;
    
    private int equalPosChars;
    private int diffPosChars;
    private List<Character> numbers;
    
    /**
     * Costruisce un server vuoto.
     */
    public MinigameJabberServer(){
        strToGuess = "";
        numbers = new ArrayList();
    }

    /**
     * Esegue il gioco tramite un thread.
     */
    @Override
    public void run() {
        numbers.addAll(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

        try {
            s = new ServerSocket(6666);
            System.out.println("Started: " + s);

            socket = s.accept();

            System.out.println("Connessione accettata: " + socket);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); // Flush automatico con PrintWriter:

            while (!win) {
                //Semplificare
                Collections.shuffle(numbers);
                strToGuess = "";

                numbers.forEach(c -> {
                    strToGuess = strToGuess + String.valueOf(c);
                });

                strToGuess = strToGuess.subSequence(0, 4).toString();

                win = false;
                attempts = 0;

                while (win == false && attempts < MAX_ATTEMPTS) {
                    strGuess = in.readLine();

                    if (strGuess.equals(strToGuess)) {
                        win = true;
                    } else {
                        equalPosChars = 0;
                        diffPosChars = 0;

                        for (i = 0; i < strToGuess.length(); i++) {
                            for (j = 0; j < strToGuess.length(); j++) {

                                if (strGuess.charAt(i) == strToGuess.charAt(j)) {
                                    if (i == j) {
                                        equalPosChars++;
                                    } else {
                                        diffPosChars++;
                                    }
                                }

                            }
                        }
                    }

                    attempts++;

                    if (win == true) {
                        result = "Hai vinto!";
                        win = false;
                    } else {
                        if (attempts < MAX_ATTEMPTS) {
                            result = equalPosChars + " numeri corretti in posizione corretta. " + diffPosChars + " numeri corretti in posizione sbagliata.";
                        } else if (attempts == MAX_ATTEMPTS) {
                            result = "Hai perso!";
                        }
                    }

                    out.println(result);
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                s.close();
                System.out.println("(Server) Closing server socket...");
                socket.close();
                System.out.println("(Server) Closing client socket...");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    }
}
