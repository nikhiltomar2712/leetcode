import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    
    private Semaphore numberSem = new Semaphore(1);   // starts with 1 permit
    private Semaphore fizzSem   = new Semaphore(0);
    private Semaphore buzzSem   = new Semaphore(0);
    private Semaphore fizzbuzzSem = new Semaphore(0);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {               // only pure "fizz"
                fizzSem.acquire();
                printFizz.run();
                numberSem.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {               // only pure "buzz"
                buzzSem.acquire();
                printBuzz.run();
                numberSem.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            fizzbuzzSem.acquire();
            printFizzBuzz.run();
            numberSem.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            numberSem.acquire();
            
            if (i % 15 == 0) {
                fizzbuzzSem.release();
            } else if (i % 3 == 0) {
                fizzSem.release();
            } else if (i % 5 == 0) {
                buzzSem.release();
            } else {
                printNumber.accept(i);
                numberSem.release();
            }
        }
    }
}