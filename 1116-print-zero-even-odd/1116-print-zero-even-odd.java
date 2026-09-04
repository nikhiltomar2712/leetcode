import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    private Semaphore zeroSem = new Semaphore(1);
    private Semaphore evenSem = new Semaphore(0);
    private Semaphore oddSem = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroSem.acquire();        // Wait for permission to print zero
            printNumber.accept(0);    // Print 0
            // After printing 0, release the appropriate next thread
            if (i % 2 == 1) {
                oddSem.release();     // Next number is odd
            } else {
                evenSem.release();    // Next number is even
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSem.acquire();        // Wait for even turn
            printNumber.accept(i);    // Print even number
            zeroSem.release();        // Allow zero to print next
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire();         // Wait for odd turn
            printNumber.accept(i);    // Print odd number
            zeroSem.release();        // Allow zero to print next
        }
    }
}