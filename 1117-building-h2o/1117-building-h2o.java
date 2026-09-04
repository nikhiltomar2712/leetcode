import java.util.concurrent.Semaphore;

class H2O {
    private Semaphore hydrogenSem = new Semaphore(2);
    private Semaphore oxygenSem = new Semaphore(0);
    private int hydrogenCount = 0;

    public H2O() {
        // Initialize any necessary variables here
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSem.acquire(); // Wait for an available hydrogen slot (max 2)
        releaseHydrogen.run(); // Output "H"
        
        synchronized (this) {
            hydrogenCount++;
            if (hydrogenCount == 2) {
                // Two hydrogens ready, release oxygen
                oxygenSem.release();
            }
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSem.acquire(); // Wait until two hydrogens have arrived
        releaseOxygen.run(); // Output "O"
        
        synchronized (this) {
            hydrogenCount = 0; // Reset for next molecule
            hydrogenSem.release(2); // Allow two new hydrogens for next molecule
        }
    }
}