package experiments.threading.lotsofthreads;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("> Program started");
        long ms = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 20000; ++i) {
            int id = i;
            Thread t = new Thread(() -> {
                int v = id;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("    - Thread #" + v + " finished.");
            });
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("> Program finished in " + (System.currentTimeMillis() - ms) + "ms");
    }
}
