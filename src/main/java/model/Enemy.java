package model;

public class Enemy {
    private int health;
    private boolean run;
    private Thread t;
    
    public Enemy(){
        health = 100;
        run = false;
        
        t = new Thread() {
            @Override
            public void run() {
                while(run){
                    try {
                        changeHealth(1);
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        System.err.println("Enemy thread: " + ex);
                    }
                }
            }
        };
    }
    
    public synchronized void changeHealth(int x) {
        if(health + x >= 100){
            health = 100;
        } else if (health + x <= 0){
            health = 0;
            run = false;
        } else {
            health = health + x;
        }
    }
    
    public int getHealth(){
        return health;
    }
    
    public void start(){
        run = true;
        t.start();
    }
}
