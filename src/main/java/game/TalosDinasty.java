package game;

import model.GameController;

public class TalosDinasty extends GameController {
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
    public static final int TALOS = 9;
    public static final int PURCHASE = 10;
    public static final int SMITH_OBJECT = 11;

    //Items
    public static final String GOLDEN_RING = "Anello d'oro";
    public static final String GOLDEN_COIN = "Moneta d'oro";
    public static final String TALOS_AMULET = "Amuleto di Talos";
    public static final String TALOS_RELIC = "Reliquia di Talos";

    //Observations
    public static final int SCROLL = 0;
    public static final int BOOK = 1;

    //Events
    public static final String KID_EVENT = "tempio_entrata_n.png";
    public static final String GUARD_EVENT = "casa_entrata_e.png";
    public static final String MERCHANT_EVENT = "mercante_w.png";
    public static final String TEMPLE_EVENT = "tempio_altari_s.png";
    public static final String RELIC_EVENT = "casa_reliquia_e.png";
    public static final String BLACKSMITH_EVENT = "fabbro_w.png";
    public static final String JARL_EVENT = "jarl_n.png";
    public static final String ENEMY_EVENT = "casa_porta_n.png";

    public static final String WIN = MinigameJabberServer.WIN_PHRASE;
    public static final String LOSE = MinigameJabberServer.LOSE_PHRASE;

    private MinigameJabberClient playerClient;
    private MinigameJabberServer server;
    private Enemy enemy;

    public TalosDinasty() {
        playerClient = new MinigameJabberClient();
        server = new MinigameJabberServer();
        enemy = new Enemy();
        super.setDialogueDatabase("jdbc:h2:./resources/db/store", "sa", "");
        super.setDialogueSeparator("§");

        //Rooms
        super.addFirstRoom("jarl_n.png", "jarl_e.png", "jarl_s.png", "jarl_w.png");
        super.addRoom("jarl_centro_n.png", "jarl_centro_e.png", "jarl_centro_s.png", "jarl_centro_w.png");
        super.addRoom("jarl_bimbo_n.png", "jarl_bimbo_e.png", "jarl_bimbo_s.png", "jarl_bimbo_w.png");
        super.addRoom("jarl_entrata_n.png", "jarl_entrata_s.png");
        super.addRoom("strada_1_n.png", "strada_1_s.png");
        super.addRoom("mercante_entrata_n.png", "mercante_entrata_s.png", "mercante_entrata_w.png");
        super.addRoom("mercante_w.png", "mercante_e.png");
        super.addRoom("cerchio1_n.png", "cerchio1_s.png", "cerchio1_w.png");
        super.addRoom("cerchio2_s.png", "cerchio2_w.png", "cerchio2_n.png", "cerchio2_e.png");
        super.addRoom("cerchio3_n.png", "cerchio3_e.png", "cerchio3_s.png", "cerchio3_w.png");
        super.addRoom("fabbro_s.png", "fabbro_w.png", "fabbro_n.png");
        super.addRoom("tempio_scale_s.png", "tempio_scale_n.png");
        super.addRoom("tempio_entrata_s.png", "tempio_entrata_n.png");
        super.addRoom("tempio_uscita_n.png", "tempio_uscita_e.png", "tempio_uscita_s.png", "tempio_uscita_w.png");
        super.addRoom("tempio_centro_n.png", "tempio_centro_s.png");
        super.addRoom("tempio_altari_n.png", "tempio_altari_e.png", "tempio_altari_s.png", "tempio_altari_w.png");
        super.addRoom("casa_entrata_e.png", "casa_entrata_w.png");
        super.addRoom("casa_ingresso_e.png", "casa_ingresso_w.png");
        super.addRoom("casa_centro_n.png", "casa_centro_e.png", "casa_centro_s.png", "casa_centro_w.png");
        super.addRoom("casa_porta_n.png", "casa_porta_e.png", "casa_porta_s.png", "casa_porta_w.png");
        super.addRoom("casa_reliquia_e.png", "casa_reliquia_w.png");

        //Links between rooms
        super.addEdge("jarl_s.png", "jarl_centro_s.png");
        super.addEdge("jarl_centro_n.png", "jarl_n.png");
        super.addEdge("jarl_centro_s.png", "jarl_bimbo_s.png");
        super.addEdge("jarl_bimbo_s.png", "jarl_entrata_s.png");
        super.addEdge("jarl_entrata_s.png", "strada_1_s.png");
        super.addEdge("strada_1_s.png", "mercante_entrata_w.png");
        super.addEdge("jarl_bimbo_n.png", "jarl_centro_n.png");
        super.addEdge("jarl_entrata_n.png", "jarl_bimbo_n.png");
        super.addEdge("strada_1_n.png", "jarl_entrata_n.png");
        super.addEdge("mercante_entrata_n.png", "strada_1_n.png");
        super.addEdge("mercante_entrata_w.png", "mercante_w.png");
        super.addEdge("mercante_e.png", "mercante_entrata_n.png");
        super.addEdge("mercante_entrata_s.png", "cerchio1_s.png");
        super.addEdge("cerchio1_s.png", "cerchio2_s.png");
        super.addEdge("cerchio2_n.png", "cerchio1_n.png");
        super.addEdge("cerchio1_n.png", "mercante_entrata_n.png");
        super.addEdge("cerchio2_s.png", "cerchio3_s.png");
        super.addEdge("cerchio2_n.png", "cerchio1_n.png");
        super.addEdge("cerchio3_w.png", "fabbro_w.png");
        super.addEdge("fabbro_s.png", "cerchio3_s.png");
        super.addEdge("fabbro_n.png", "cerchio1_n.png");
        super.addEdge("cerchio1_w.png", "fabbro_w.png");
        super.addEdge("cerchio3_s.png", "tempio_scale_s.png");
        super.addEdge("cerchio3_e.png", "cerchio2_n.png");
        super.addEdge("tempio_scale_n.png", "cerchio3_n.png");
        super.addEdge("tempio_scale_s.png", "tempio_entrata_s.png");
        super.addEdge("tempio_entrata_n.png", "tempio_scale_n.png");
        super.addEdge("tempio_entrata_s.png", "tempio_uscita_s.png");
        super.addEdge("tempio_uscita_n.png", "tempio_entrata_n.png");
        super.addEdge("tempio_uscita_s.png", "tempio_centro_s.png");
        super.addEdge("tempio_centro_s.png", "tempio_altari_s.png");
        super.addEdge("tempio_altari_n.png", "tempio_centro_n.png");
        super.addEdge("tempio_centro_n.png", "tempio_uscita_n.png");
        super.addEdge("cerchio2_e.png", "casa_entrata_e.png");
        super.addEdge("casa_entrata_e.png", "casa_ingresso_e.png");
        super.addEdge("casa_ingresso_e.png", "casa_centro_e.png");
        super.addEdge("casa_centro_n.png", "casa_porta_n.png");
        super.addEdge("casa_porta_e.png", "casa_reliquia_e.png");
        super.addEdge("casa_reliquia_w.png", "casa_porta_w.png");
        super.addEdge("casa_entrata_w.png", "cerchio2_w.png");
        super.addEdge("casa_porta_s.png", "casa_centro_s.png");
        super.addEdge("casa_centro_w.png", "casa_ingresso_w.png");
        super.addEdge("casa_ingresso_w.png", "casa_entrata_w.png");
    }

    public TalosDinasty(String playerName) {
        this();
        super.setPlayerName(playerName);
        super.initDialogues();

        //Dialogues
        super.addDialogue(MERCHANT_FIRST, "Buondì! Non perderti l'offerta di oggi: un amuleto di Talos a solo una moneta d'oro!");
        super.addDialogue(MERCHANT_NO_MONEY, "Devi scusarmi, non ti posso far credito: torna quando sarai un po' più... ricco!");
        super.addDialogue(GUARD, "Non puoi entrare senza un riconoscimento.");
        super.addDialogue(KID_FIRST, getPlayerName() + "! " + getPlayerName() + "! Ti sfido!§Io penso a un numero, che può iniziare anche con lo zero, di quattro cifre tutte diverse e tu lo dovrai indovinare.§Tu mi dirai il numero e io ti dirò: quante cifre sono giuste e nella posizione giusta e quante cifre sono giuste ma nella posizione sbagliata.§Scommetto che non riesci a vincere il premio!");
        super.addDialogue(KID_LOSE, LOSE);
        super.addDialogue(KID_WIN, WIN + "§Spero che a papà non piacesse questo gioiello...");
        super.addDialogue(JARL_START, getPlayerName() + "...§Uno spettro si aggira per Riften... un ladro.§Riften è dimora dei culti di ben nove dei. Purtroppo, qualcuno ha rubato una delle nove reliquie: quella di Talos.§Giuntami voce delle tue grandi capacità investigative, ho deciso di chiamarti.§" + getPlayerName() + ", sei la nostra ultima speranza!§Ti consiglio di iniziare a cercare dal tempio dalla parte opposta della città.");
        super.addDialogue(JARL_END, getPlayerName() + "!§Che gioia vederti! Sei stato eccellente! I miei complimenti.§Come ricompensa, ti nomino capo della squadra investigativa di Riften!§Arrivederci!");
        super.addDialogue(SMITH_NO_OBJECT, "Spade, martelli, fusione di oggetti... trovi tutto qui.");
        super.addDialogue(TALOS, "La famiglia Talos si è ripresa quanto gli appartiene di diritto, in quanto discendenti di Talos.§Non ci fermerai!");
        super.addDialogue(PURCHASE, "Ottimi gusti.§Grazie per l'acquisto!");
        super.addDialogue(SMITH_OBJECT, "Un anello d'oro...§Ne posso ricavare due monete.§Una a te... e una a me!");

        super.addItemDescription(GOLDEN_RING, "Un bell'anello d'oro. Chissà cosa potrei ricavarne...");
        super.addItemDescription(GOLDEN_COIN, "\"Il risparmio è il primo guadagno\". Ma forse non è questo il caso.");
        super.addItemDescription(TALOS_AMULET, "L'amuleto dell'egemone famiglia Talos, usato tra di loro come lasciapassare.");
        super.addItemDescription(TALOS_RELIC, "Finalmente! Devo avvisare immediatamente lo Jarl.");

        super.addObservation(SCROLL, "\"Ruba la reliquia di Talos e portala da me. Firmato: sig. Tal...\" Accidenti, non si riesce a leggere.");
        super.addObservation(BOOK, "\"The C Programming Language\". Un libro pericolosissimo.");
    }

    public void startMiniGame() {
        server.start();
        playerClient.connect();
    }

    public void guessGame(String s) {
        playerClient.attempt(s);
    }

    public String getGameResult() {
        return playerClient.getResult();
    }
    
    public void startBattle(){
        enemy.start();
    }
    
    public void hitEnemy(){
        enemy.changeHealth(-12);
    }
    
    public int getEnemyHealth(){
        return enemy.getHealth();
    }
}