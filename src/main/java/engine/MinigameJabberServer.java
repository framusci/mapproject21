package engine;

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
import java.util.Collections;

class MinigameJabberServer extends Thread {

    private static BufferedReader in;
    private static PrintWriter out;
    private static Socket socket;
    private static String strGuess;
    private static ServerSocket s;

    public static String WIN_PHRASE = "Hai vinto!";
    public static String LOSE_PHRASE = "Hai perso!";

    private static int attempts;
    private static final int MAX_ATTEMPTS = 20;
    private static boolean win;
    private static String strToGuess = "";
    private static String result;

    private static int i, j;
    private static int equalPosChars;
    private static int diffPosChars;
    private static List<Character> numbers = new ArrayList();

    @Override
    public void run() {
        numbers.add('0');
        numbers.add('1');
        numbers.add('2');
        numbers.add('3');
        numbers.add('4');
        numbers.add('5');
        numbers.add('6');
        numbers.add('7');
        numbers.add('8');
        numbers.add('9');

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

                numbers.forEach(c -> {
                    strToGuess = strToGuess + String.valueOf(c);
                });

                strToGuess = strToGuess.subSequence(0, 4).toString();
                //System.out.println(strToGuess);
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
                s.close();//ServerSocket
                System.out.println("(Server) Closing server socket...");
                socket.close();
                System.out.println("(Server) Closing client socker...");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    }
}
