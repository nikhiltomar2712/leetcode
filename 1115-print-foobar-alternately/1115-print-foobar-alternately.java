import java.util.concurrent.Semaphore;

class FooBar {
    private int n;
    private Semaphore semFoo = new Semaphore(1);
    private Semaphore semBar = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semFoo.acquire(); // Wait for permission to print "foo"
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semBar.release(); // Allow "bar" to print
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semBar.acquire(); // Wait for permission to print "bar"
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semFoo.release(); // Allow "foo" to print next
        }
    }
}