class SharedCounter {
    private int count = 0;

    public void increment() {
        count++; 
    }

    public int getCount() {
        return count;
    }
}

class CounterThread extends Thread {
    private final SharedCounter counter;

    public CounterThread(SharedCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment(); 
        }
    }
}

public class RaceConditionDebug {
    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter();
        Thread t1 = new CounterThread(counter);
        Thread t2 = new CounterThread(counter);
        Thread t3 = new CounterThread(counter);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + counter.getCount());
    }
}
