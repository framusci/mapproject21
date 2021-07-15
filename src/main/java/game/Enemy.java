package game;

class Enemy implements Runnable {
    
    private volatile int health;
    private boolean run;

    public Enemy() {
        health = 100;
        run = false;
    }

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

    public synchronized void changeHealth(int x) {
        if (health + x >= 100) {
            health = 100;
        } else if (health + x <= 0) {
            run = false;
            health = 0;
        } else {
            health = health + x;
        }
    }

    public int getHealth() {
        return health;
    }
}
