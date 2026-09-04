class Foo {
    private int flag = 0;   // 0 → first, 1 → second, 2 → third

    public Foo() {
        
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        flag = 1;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (flag < 1) {
            wait();
        }
        printSecond.run();
        flag = 2;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (flag < 2) {
            wait();
        }
        printThird.run();
    }
}