package game;

/**
 * Gestisce la battaglia col nemico finale.
 * Implementa Runnable per essere aperto a future estensioni.
 * @author franc
 */
class Enemy implements Runnable {
    
    private static final int MAX_HEALTH = 100;
    private static final int MIN_HEALTH = 0;
    
    /*
    "volatile" server per evitare problemi di 
    sincronizzazione tra thread. In particolare,
    evita che i thread leggano il valore nella cache
    anziché il valore aggiornato.
    */
    private volatile int health;
    private boolean run;

    /**
     * Costruisce il nemico con 100 punti vita.
     */
    public Enemy() {
        health = MAX_HEALTH;
        run = false;
    }

    /**
     * Esegue il thread che aumenta la vita del nemico.
     */
    @Override
    public void run() {
        run = true;

        while (run) {
            changeHealth(1);

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.err.println("Enemy thread: " + ex);
            }
        }
    }

    /**
     * Modifica la vita del nemico.
     * La funzione è synchronized, per evitare che alcune modifiche
     * fatte in contemporanea vadano perse.
     * 
     * @param x I punti vita da aggiungere (o togliere, se <tt>x</tt> è negativa) al nemico.
     */
    public synchronized void changeHealth(int x) {
        if (health + x >= MAX_HEALTH) {
            health = MAX_HEALTH;
        } else if (health + x <= MIN_HEALTH) {
            run = false;
            health = MIN_HEALTH;
        } else {
            health = health + x;
        }
    }

    /**
     * Restituisce i punti vita del nemico.
     * 
     * @return I punti vita del nemico.
     */
    public int getHealth() {
        return health;
    }
}
