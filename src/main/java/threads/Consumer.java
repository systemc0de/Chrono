package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable{
    BlockingQueue<String>blockingQueue = new LinkedBlockingQueue<>();
    private AtomicInteger count = new AtomicInteger(0);
    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
            try {
                while(!Thread.currentThread().isInterrupted()){
                    String result  = blockingQueue.take();
                    count.incrementAndGet();
                    System.out.println(getCount() + " :: " + result);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
        }
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }
}
