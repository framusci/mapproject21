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
package engine;

/**
 *
 * @author franc
 */
public class SkyrimGame extends GameController {
    //Class per le costanti?

    //Cardinal directions
    public static final int NORTH = 0;
    public static final int WEST = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 3;

    //Dialogues
    public static final int MERCHANT_FIRST = 0;
    public static final int MERCHANT_NO_MONEY = 1;
    public static final int STRANGER = 2;
    public static final int KID_FIRST = 3;
    public static final int KID_LOSE = 4;
    public static final int KID_WIN = 5;
    public static final int JARL_START = 6;
    public static final int JARL_END = 7;
    
    //Items
    public static final String GOLDEN_RING = "Anello d'oro";
    public static final String GOLDEN_COIN = "Moneta d'oro";
    public static final String TALOS_AMULET = "Amuleto di Talos";
    public static final String SCROLL = "Pergamena";

    //Events
    public static final int MINIGAME = 0;

    public static final String KID_INTERACTION = "43";
    public static final String GUARD_INTERACTION = "11";
    public static final String MERCHANT_INTERACTION = "32";

    //Rifare, non astratto (forse)
    public static final String WIN = MinigameJabberServer.WIN_PHRASE;
    public static final String LOSE = MinigameJabberServer.LOSE_PHRASE;

    private MinigameJabberClient playerClient;
    private MinigameJabberServer server;

    //Rooms
    private Room jarl;
    private Room casa_centro;
    private Room casa_entrata;
    private Room casa_ingresso;
    private Room casa_porta;
    private Room casa_reliquia;
    private Room casa_stanza;
    private Room cerchio1;
    private Room cerchio2;
    private Room cerchio3;
    private Room fabbro;
    private Room jarl_bimbo;
    private Room jarl_centro;
    private Room jarl_entrata;
    private Room mercante;
    private Room mercante_entrata;
    private Room strada_1;
    private Room tempio_altari;
    private Room tempio_centro;
    private Room tempio_entrata;
    private Room tempio_scale;
    private Room tempio_uscita;
    
    public SkyrimGame(){
        //Istanziazione
        playerClient = new MinigameJabberClient();
        server = new MinigameJabberServer();
        super.setDialogueDatabase("jdbc:h2:./db/store", "sa", "");
        super.setDialogueSeparator("§");
    }

    public SkyrimGame(String playerName) {
        //Istanziazione
        this();
        super.setPlayerName(playerName);

        //Creazione mappa
        casa_centro.setAdjacentRoom(NORTH, "casa_centro_n.png");
        casa_centro.setAdjacentRoom(WEST, "casa_centro_w.png");
        casa_centro.setAdjacentRoom(SOUTH, "casa_centro_s.png");
        casa_centro.setAdjacentRoom(EAST, "casa_centro_e.png");

        casa_entrata.setAdjacentRoom(NORTH, "casa_entrata_n.png");
        casa_entrata.setAdjacentRoom(WEST, "casa_entrata_w.png");
        casa_entrata.setAdjacentRoom(SOUTH, "casa_entrata_s.png");
        casa_entrata.setAdjacentRoom(EAST, "casa_entrata_e.png");

        casa_ingresso.setAdjacentRoom(NORTH, "casa_ingresso_n.png");
        casa_ingresso.setAdjacentRoom(WEST, "casa_ingresso_w.png");
        casa_ingresso.setAdjacentRoom(SOUTH, "casa_ingresso_s.png");
        casa_ingresso.setAdjacentRoom(EAST, "casa_ingresso_e.png");

        casa_porta.setAdjacentRoom(NORTH, "casa_porta_n.png");
        casa_porta.setAdjacentRoom(WEST, "casa_porta_w.png");
        casa_porta.setAdjacentRoom(SOUTH, "casa_porta_s.png");
        casa_porta.setAdjacentRoom(EAST, "casa_porta_e.png");

        casa_reliquia.setAdjacentRoom(NORTH, "casa_reliquia_n.png");
        casa_reliquia.setAdjacentRoom(WEST, "casa_reliquia_w.png");
        casa_reliquia.setAdjacentRoom(SOUTH, "casa_reliqua_s.png");
        casa_reliquia.setAdjacentRoom(EAST, "casa_reliquia_e.png");

        casa_stanza.setAdjacentRoom(NORTH, "casa_stanza_n.png");
        casa_stanza.setAdjacentRoom(WEST, "casa_stanza_w.png");
        casa_stanza.setAdjacentRoom(SOUTH, "casa_stanza_s.png");
        casa_stanza.setAdjacentRoom(EAST, "casa_stanza_e.png");

        cerchio1.setAdjacentRoom(NORTH, "cerchio1_n.png");
        cerchio1.setAdjacentRoom(WEST, "cerchio1_w.png");
        cerchio1.setAdjacentRoom(SOUTH, "cerchio1_s.png");
        cerchio1.setAdjacentRoom(EAST, "cerchio1_e.png");

        cerchio2.setAdjacentRoom(NORTH, "cerchio2_n.png");
        cerchio2.setAdjacentRoom(WEST, "cerchio2_w.png");
        cerchio2.setAdjacentRoom(SOUTH, "cerchio2_s.png");
        cerchio2.setAdjacentRoom(EAST, "cerchio2_e.png");

        cerchio3.setAdjacentRoom(NORTH, "cerchio3_n.png");
        cerchio3.setAdjacentRoom(WEST, "cerchio3_w.png");
        cerchio3.setAdjacentRoom(SOUTH, "cerchio3_s.png");
        cerchio3.setAdjacentRoom(EAST, "cerchio3_e.png");

        fabbro.setAdjacentRoom(NORTH, "fabbro_n.png");
        fabbro.setAdjacentRoom(WEST, "fabbro_w.png");
        fabbro.setAdjacentRoom(SOUTH, "fabbro_s.png");
        fabbro.setAdjacentRoom(EAST, "fabbro_e.png");

        jarl.setAdjacentRoom(NORTH, "jarl_n.png");
        jarl.setAdjacentRoom(WEST, "jarl_w.png");
        jarl.setAdjacentRoom(SOUTH, "jarl_s.png");
        jarl.setAdjacentRoom(EAST, "jarl_e.png");

        jarl_bimbo.setAdjacentRoom(NORTH, "jarl_bimbo_n.png");
        jarl_bimbo.setAdjacentRoom(WEST, "jarl_bimbo_w.png");
        jarl_bimbo.setAdjacentRoom(SOUTH, "jarl_bimbo_s.png");
        jarl_bimbo.setAdjacentRoom(EAST, "jarl_bimbo_e.png");

        jarl_centro.setAdjacentRoom(NORTH, "jarl_centro_n.png");
        jarl_centro.setAdjacentRoom(WEST, "jarl_centro_w.png");
        jarl_centro.setAdjacentRoom(SOUTH, "jarl_centro_s.png");
        jarl_centro.setAdjacentRoom(EAST, "jarl_centro_e.png");

        jarl_entrata.setAdjacentRoom(NORTH, "jarl_entrata_n.png");
        jarl_entrata.setAdjacentRoom(WEST, "jarl_entrata_w.png");
        jarl_entrata.setAdjacentRoom(SOUTH, "jarl_entrata_s.png");
        jarl_entrata.setAdjacentRoom(EAST, "jarl_entrata_e.png");

        mercante.setAdjacentRoom(NORTH, "mercante_n.png");
        mercante.setAdjacentRoom(WEST, "mercante_w.png");
        mercante.setAdjacentRoom(SOUTH, "mercante_s.png");
        mercante.setAdjacentRoom(EAST, "mercante_e.png");

        mercante_entrata.setAdjacentRoom(NORTH, "mercante_entrata_n.png");
        mercante_entrata.setAdjacentRoom(WEST, "mercante_entrata_w.png");
        mercante_entrata.setAdjacentRoom(SOUTH, "mercante_entrata_s.png");
        mercante_entrata.setAdjacentRoom(EAST, "mercante_entrata_e.png");

        strada_1.setAdjacentRoom(NORTH, "strada_1_n.png");
        strada_1.setAdjacentRoom(WEST, "strada_1_w.png");
        strada_1.setAdjacentRoom(SOUTH, "strada_1_s.png");
        strada_1.setAdjacentRoom(EAST, "strada_1_e.png");

        tempio_altari.setAdjacentRoom(NORTH, "tempio_altari_n.png");
        tempio_altari.setAdjacentRoom(WEST, "tempio_altari_w.png");
        tempio_altari.setAdjacentRoom(SOUTH, "tempio_altari_s.png");
        tempio_altari.setAdjacentRoom(EAST, "tempio_altari_e.png");

        tempio_centro.setAdjacentRoom(NORTH, "tempio_centro_n.png");
        tempio_centro.setAdjacentRoom(WEST, "tempio_centro_w.png");
        tempio_centro.setAdjacentRoom(SOUTH, "tempio_centro_s.png");
        tempio_centro.setAdjacentRoom(EAST, "tempio_centro_e.png");

        tempio_entrata.setAdjacentRoom(NORTH, "tempio_entrata_n.png");
        tempio_entrata.setAdjacentRoom(WEST, "tempio_entrata_w.png");
        tempio_entrata.setAdjacentRoom(SOUTH, "tempio_entrata_s.png");
        tempio_entrata.setAdjacentRoom(EAST, "tempio_entrata_e.png");

        tempio_scale.setAdjacentRoom(NORTH, "tempio_scale_n.png");
        tempio_scale.setAdjacentRoom(WEST, "tempio_scale_w.png");
        tempio_scale.setAdjacentRoom(SOUTH, "tempio_scale_s.png");
        tempio_scale.setAdjacentRoom(EAST, "tempio_scale_e.png");

        tempio_uscita.setAdjacentRoom(NORTH, "tempio_uscita_n.png");
        tempio_uscita.setAdjacentRoom(WEST, "tempio_uscita_w.png");
        tempio_uscita.setAdjacentRoom(SOUTH, "tempio_uscita_s.png");
        tempio_uscita.setAdjacentRoom(EAST, "tempio_uscita_e.png");

        //Inizializzazione eventi
        super.addEvent(MINIGAME);

        //Dialogues
        super.addDialogue(MERCHANT_FIRST, "Edda", "Buondì! Non perderti l'offerta di oggi: un amuleto di Talos a solo una moneta d'oro!");
        super.addDialogue(MERCHANT_NO_MONEY, "Edda", "Devi scusarmi, non ti posso far credito: torna quando sarai un po' più... ricco!");
        super.addDialogue(STRANGER, "???", "Ehi, cosa guardi?!");
        super.addDialogue(KID_FIRST, "Bimba", "Ciao!" + super.getPlayerName() + "! " + super.getPlayerName() + "! Ti sfido!§Io ti dico un numero, che può iniziare anche con lo zero, di quattro cifre tutte diverse e tu lo dovrai indovinare.§Tu mi dirai il numero e io ti dirò: quante cifre sono giuste e nella posizione giusta e quante cifre sono giuste ma nella posizione sbagliata.§Scommetto che non riesci a vincere il premio!");
        super.addDialogue(KID_LOSE, "Bimba", LOSE);
        super.addDialogue(KID_WIN, "Bimba", WIN);
        super.addDialogue(JARL_START, "Jarl", super.getPlayerName() + "...§Uno spettro si aggira per Riften... un ladro.§Riften è dimora dei culti di ben nove dei. Purtroppo, qualcuno ha rubato una delle nove reliquie: quella di Talos.§Giuntami voce delle tue mirabolanti capacità investigative, ho deciso di chiamarti.§" + super.getPlayerName() + ", sei la nostra ultima speranza!");
        super.addDialogue(JARL_END, "Jarl", super.getPlayerName() + "!§Che gioia vederti! Sei stato eccellente! I miei complimenti.§Come ricompensa, ti nomino capo della squadra investigativa di Riften!§Arrivederci!");
        
        super.addItemDescription(GOLDEN_RING, "Un bell'anello d'oro. Chissà cosa potrei ricavarne...");
        super.addItemDescription(GOLDEN_COIN, "Il risparmio è il primo guadagno. Ma forse non è questo il caso.");
        super.addItemDescription(TALOS_AMULET, "L'amuleto dell'egemone famiglia Talosik, usato tra di loro come lasciapassare.");
        super.addItemDescription(SCROLL, "\"Vai nel tempio e ruba la reliquia di Talos. Firmato: sig...\" Dannazione, il foglio è sgualcito e non si riesce a leggere.");
    }

    public void startMiniGame() {
        server.start();
        playerClient.run();
    }

    public void guessGame(String s) {
        playerClient.attempt(s);
    }

    public String getGameResult() {
        return playerClient.getResult();
    }

    private void createRoomObject(Room... room) {
        for (Room r : room) {
            r = new Room();
        }
    }
}
