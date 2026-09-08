import java.util.concurrent.Semaphore;

class DiningPhilosophers {
    // Semaphore to limit only 4 philosophers to pick up forks at a time
    // This prevents deadlock
    private Semaphore limit = new Semaphore(4);
    
    // One semaphore per fork (5 forks)
    private Semaphore[] forks = new Semaphore[5];
    
    public DiningPhilosophers() {
        // Initialize all forks as available
        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
    }
    
    // Call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        
        // Acquire permission to eat (limit to 4 philosophers)
        limit.acquire();
        
        // Philosopher's fork indices (circular arrangement)
        int leftFork = philosopher;
        int rightFork = (philosopher + 1) % 5;
        
        // To prevent deadlock, make all philosophers pick up forks in same order
        // Here we pick the smaller index first
        if (philosopher == 4) {
            // Last philosopher picks right fork first (to break symmetry)
            forks[rightFork].acquire();
            forks[leftFork].acquire();
        } else {
            forks[leftFork].acquire();
            forks[rightFork].acquire();
        }
        
        // Pick up forks (execute the provided actions)
        pickLeftFork.run();
        pickRightFork.run();
        
        // Eat
        eat.run();
        
        // Put down forks
        putLeftFork.run();
        putRightFork.run();
        
        // Release forks
        forks[leftFork].release();
        forks[rightFork].release();
        
        // Release limit permit
        limit.release();
    }
}