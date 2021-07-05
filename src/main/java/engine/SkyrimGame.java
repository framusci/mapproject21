package engine;

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
    public static final int GUARD = 2;
    public static final int KID_FIRST = 3;
    public static final int KID_LOSE = 4;
    public static final int KID_WIN = 5;
    public static final int JARL_START = 6;
    public static final int JARL_END = 7;
    public static final int SMITH_NO_OBJECT = 8;
    public static final int TALOSK = 9;

    //Items
    public static final String GOLDEN_RING = "Anello d'oro";
    public static final String GOLDEN_COIN = "Moneta d'oro";
    public static final String TALOS_AMULET = "Amuleto di Talos";
    public static final String TALOS_RELIC = "Reliquia di Talos";

    //Observations
    public static final int SCROLL = 0;
    public static final int BOOK = 1;

    //Events
    public static final int NUMBER_GAME = 0;
    public static final int MONEY = 1;
    public static final int SPOKE_WITH_TALOSK = 2;

    public static final String KID_INTERACTION = "190";
    public static final String GUARD_INTERACTION = "23";
    public static final String MERCHANT_INTERACTION = "141";
    public static final String TEMPLE_INTERACTION = "172";
    public static final String RELIC_INTERACTION = "53";
    public static final String BLACKSMITH_INTERACTION = "101";
    public static final String JARL_INTERACTION = "00";

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

    public SkyrimGame() {
        playerClient = new MinigameJabberClient();
        server = new MinigameJabberServer();
        super.setDialogueDatabase("jdbc:h2:./db/store", "sa", "");
        super.setDialogueSeparator("§");
        
        jarl = new Room(0);
        casa_centro = new Room(1);
        casa_entrata = new Room(2);
        casa_ingresso = new Room(3);
        casa_porta = new Room(4);
        casa_reliquia = new Room(5);
        casa_stanza = new Room(6);
        cerchio1 = new Room(7);
        cerchio2 = new Room(8);
        cerchio3 = new Room(9);
        fabbro = new Room(10);
        jarl_bimbo = new Room(11);
        jarl_centro = new Room(12);
        jarl_entrata = new Room(13);
        mercante = new Room(14);
        mercante_entrata = new Room(15);
        strada_1 = new Room(16);
        tempio_altari = new Room(17);
        tempio_centro = new Room(18);
        tempio_entrata = new Room(19);
        tempio_scale = new Room(20);
        tempio_uscita = new Room(21);

        //Creazione mappa
        casa_centro.setAdjacentRoom(NORTH, "casa_centro_n.png", casa_porta);
        casa_centro.setAdjacentRoom(WEST, "casa_centro_w.png", casa_ingresso);
        casa_centro.setAdjacentRoom(SOUTH, "casa_centro_s.png");
        casa_centro.setAdjacentRoom(EAST, "casa_centro_e.png");

        casa_entrata.setAdjacentRoom(NORTH, "casa_entrata_n.png");
        casa_entrata.setAdjacentRoom(WEST, "casa_entrata_w.png", cerchio2);
        casa_entrata.setAdjacentRoom(SOUTH, "casa_entrata_s.png");
        casa_entrata.setAdjacentRoom(EAST, "casa_entrata_e.png", casa_ingresso);

        casa_ingresso.setAdjacentRoom(NORTH, "casa_ingresso_n.png");
        casa_ingresso.setAdjacentRoom(WEST, "casa_ingresso_w.png", casa_entrata);
        casa_ingresso.setAdjacentRoom(SOUTH, "casa_ingresso_s.png");
        casa_ingresso.setAdjacentRoom(EAST, "casa_ingresso_e.png", casa_centro);

        casa_porta.setAdjacentRoom(NORTH, "casa_porta_n.png");
        casa_porta.setAdjacentRoom(WEST, "casa_porta_w.png");
        casa_porta.setAdjacentRoom(SOUTH, "casa_porta_s.png", casa_centro);
        casa_porta.setAdjacentRoom(EAST, "casa_porta_e.png", casa_stanza);

        casa_reliquia.setAdjacentRoom(NORTH, "casa_reliquia_n.png");
        casa_reliquia.setAdjacentRoom(WEST, "casa_reliquia_w.png", casa_stanza);
        casa_reliquia.setAdjacentRoom(SOUTH, "casa_reliquia_s.png");
        casa_reliquia.setAdjacentRoom(EAST, "casa_reliquia_e.png");

        casa_stanza.setAdjacentRoom(NORTH, "casa_stanza_n.png");
        casa_stanza.setAdjacentRoom(WEST, "casa_stanza_w.png", casa_porta);
        casa_stanza.setAdjacentRoom(SOUTH, "casa_stanza_s.png");
        casa_stanza.setAdjacentRoom(EAST, "casa_stanza_e.png", casa_reliquia);

        cerchio1.setAdjacentRoom(NORTH, "cerchio1_n.png", mercante_entrata);
        cerchio1.setAdjacentRoom(WEST, "cerchio1_w.png", fabbro);
        cerchio1.setAdjacentRoom(SOUTH, "cerchio1_s.png", cerchio2);
        cerchio1.setAdjacentRoom(EAST, "cerchio1_e.png");

        cerchio2.setAdjacentRoom(NORTH, "cerchio2_n.png", cerchio1);
        cerchio2.setAdjacentRoom(WEST, "cerchio2_w.png");
        cerchio2.setAdjacentRoom(SOUTH, "cerchio2_s.png", cerchio3);
        cerchio2.setAdjacentRoom(EAST, "cerchio2_e.png", casa_entrata);

        cerchio3.setAdjacentRoom(NORTH, "cerchio3_n.png");
        cerchio3.setAdjacentRoom(WEST, "cerchio3_w.png", fabbro);
        cerchio3.setAdjacentRoom(SOUTH, "cerchio3_s.png", tempio_scale);
        cerchio3.setAdjacentRoom(EAST, "cerchio3_e.png", cerchio2);

        fabbro.setAdjacentRoom(NORTH, "fabbro_n.png", cerchio1);
        fabbro.setAdjacentRoom(WEST, "fabbro_w.png");
        fabbro.setAdjacentRoom(SOUTH, "fabbro_s.png", cerchio3);
        fabbro.setAdjacentRoom(EAST, "fabbro_e.png");

        jarl.setAdjacentRoom(NORTH, "jarl_n.png");
        jarl.setAdjacentRoom(WEST, "jarl_w.png");
        jarl.setAdjacentRoom(SOUTH, "jarl_s.png", jarl_centro);
        jarl.setAdjacentRoom(EAST, "jarl_e.png");

        jarl_bimbo.setAdjacentRoom(NORTH, "jarl_bimbo_n.png", jarl_centro);
        jarl_bimbo.setAdjacentRoom(WEST, "jarl_bimbo_w.png");
        jarl_bimbo.setAdjacentRoom(SOUTH, "jarl_bimbo_s.png", jarl_entrata);
        jarl_bimbo.setAdjacentRoom(EAST, "jarl_bimbo_e.png");

        jarl_centro.setAdjacentRoom(NORTH, "jarl_centro_n.png", jarl);
        jarl_centro.setAdjacentRoom(WEST, "jarl_centro_w.png");
        jarl_centro.setAdjacentRoom(SOUTH, "jarl_centro_s.png", jarl_bimbo);
        jarl_centro.setAdjacentRoom(EAST, "jarl_centro_e.png");

        jarl_entrata.setAdjacentRoom(NORTH, "jarl_entrata_n.png", jarl_bimbo);
        jarl_entrata.setAdjacentRoom(WEST, "jarl_entrata_w.png");
        jarl_entrata.setAdjacentRoom(SOUTH, "jarl_entrata_s.png", strada_1);
        jarl_entrata.setAdjacentRoom(EAST, "jarl_entrata_e.png");

        mercante.setAdjacentRoom(NORTH, "mercante_n.png");
        mercante.setAdjacentRoom(WEST, "mercante_w.png");
        mercante.setAdjacentRoom(SOUTH, "mercante_s.png");
        mercante.setAdjacentRoom(EAST, "mercante_e.png", mercante_entrata);

        mercante_entrata.setAdjacentRoom(NORTH, "mercante_entrata_n.png", strada_1);
        mercante_entrata.setAdjacentRoom(WEST, "mercante_entrata_w.png", mercante);
        mercante_entrata.setAdjacentRoom(SOUTH, "mercante_entrata_s.png", cerchio1);
        mercante_entrata.setAdjacentRoom(EAST, "mercante_entrata_e.png", mercante);

        strada_1.setAdjacentRoom(NORTH, "strada_1_n.png", jarl_entrata);
        strada_1.setAdjacentRoom(WEST, "strada_1_w.png");
        strada_1.setAdjacentRoom(SOUTH, "strada_1_s.png", mercante_entrata);
        strada_1.setAdjacentRoom(EAST, "strada_1_e.png");

        tempio_altari.setAdjacentRoom(NORTH, "tempio_altari_n.png", tempio_centro);
        tempio_altari.setAdjacentRoom(WEST, "tempio_altari_w.png");
        tempio_altari.setAdjacentRoom(SOUTH, "tempio_altari_s.png");
        tempio_altari.setAdjacentRoom(EAST, "tempio_altari_e.png");

        tempio_centro.setAdjacentRoom(NORTH, "tempio_centro_n.png", tempio_uscita);
        tempio_centro.setAdjacentRoom(WEST, "tempio_centro_w.png");
        tempio_centro.setAdjacentRoom(SOUTH, "tempio_centro_s.png", tempio_altari);
        tempio_centro.setAdjacentRoom(EAST, "tempio_centro_e.png");

        tempio_entrata.setAdjacentRoom(NORTH, "tempio_entrata_n.png", tempio_scale);
        tempio_entrata.setAdjacentRoom(WEST, "tempio_entrata_w.png");
        tempio_entrata.setAdjacentRoom(SOUTH, "tempio_entrata_s.png", tempio_uscita);
        tempio_entrata.setAdjacentRoom(EAST, "tempio_entrata_e.png");

        tempio_scale.setAdjacentRoom(NORTH, "tempio_scale_n.png", cerchio3);
        tempio_scale.setAdjacentRoom(WEST, "tempio_scale_w.png");
        tempio_scale.setAdjacentRoom(SOUTH, "tempio_scale_s.png", tempio_entrata);
        tempio_scale.setAdjacentRoom(EAST, "tempio_scale_e.png");

        tempio_uscita.setAdjacentRoom(NORTH, "tempio_uscita_n.png", tempio_entrata);
        tempio_uscita.setAdjacentRoom(WEST, "tempio_uscita_w.png");
        tempio_uscita.setAdjacentRoom(SOUTH, "tempio_uscita_s.png", tempio_centro);
        tempio_uscita.setAdjacentRoom(EAST, "tempio_uscita_e.png");

        super.setStartingRoom(jarl);

        //Inizializzazione eventi
        super.addEvent(NUMBER_GAME);
    }

    public SkyrimGame(String playerName) {
        //Istanziazione
        this();
        super.setPlayerName(playerName);

        //Dialogues
        super.addDialogue(MERCHANT_FIRST, "Buondì! Non perderti l'offerta di oggi: un amuleto di Talos a solo una moneta d'oro!");
        super.addDialogue(MERCHANT_NO_MONEY, "Devi scusarmi, non ti posso far credito: torna quando sarai un po' più... ricco!");
        super.addDialogue(GUARD, "Non puoi entrare senza un riconoscimento.");
        super.addDialogue(KID_FIRST, super.getPlayerName() + "! " + super.getPlayerName() + "! Ti sfido!§Io penso a un numero, che può iniziare anche con lo zero, di quattro cifre tutte diverse e tu lo dovrai indovinare.§Tu mi dirai il numero e io ti dirò: quante cifre sono giuste e nella posizione giusta e quante cifre sono giuste ma nella posizione sbagliata.§Scommetto che non riesci a vincere il premio!");
        super.addDialogue(KID_LOSE, LOSE);
        super.addDialogue(KID_WIN, WIN);
        super.addDialogue(JARL_START, super.getPlayerName() + "...§Uno spettro si aggira per Riften... un ladro.§Riften è dimora dei culti di ben nove dei. Purtroppo, qualcuno ha rubato una delle nove reliquie: quella di Talos.§Giuntami voce delle tue grandi capacità investigative, ho deciso di chiamarti.§" + super.getPlayerName() + ", sei la nostra ultima speranza!§Ti consiglio di iniziare a cercare dal tempio dalla parte opposta della città.");
        super.addDialogue(JARL_END, super.getPlayerName() + "!§Che gioia vederti! Sei stato eccellente! I miei complimenti.§Come ricompensa, ti nomino capo della squadra investigativa di Riften!§Arrivederci!");
        super.addDialogue(SMITH_NO_OBJECT, "Spade, martelli, fusione di oggetti... trovi tutto qui.");
        super.addDialogue(TALOSK, "La famiglia Talos si è presa quanto gli appartiene di diritto, in quanto discendenti di Talos.§Non ci fermerai!");

        super.addItemDescription(GOLDEN_RING, "Un bell'anello d'oro. Chissà cosa potrei ricavarne...");
        super.addItemDescription(GOLDEN_COIN, "Il risparmio è il primo guadagno. Ma forse non è questo il caso.");
        super.addItemDescription(TALOS_AMULET, "L'amuleto dell'egemone famiglia Talos, usato tra di loro come lasciapassare.");

        super.addObservation(SCROLL, "\"Ruba la reliquia di Talos e portala da me. Firmato: sig. Tal...\" Accidenti, non si riesce a leggere.");
        super.addObservation(BOOK, "\"The C programming language\". Un libro pericolosissimo.");       
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
}
