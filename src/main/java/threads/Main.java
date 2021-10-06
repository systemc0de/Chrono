package threads;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String>blockingQueue = new LinkedBlockingQueue<>();
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.execute(producer);
        executorService.execute(consumer);
        executorService.execute(consumer);
        executorService.shutdown();
    }
}
