package pubsub;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Subscriber  extends Thread{

    private final String name;
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = queue.take(); // Blocks if the queue is empty
                if (message.equals("END")) {
                    break; // Exit the loop and terminate the subscriber
                }
                System.out.println(name + " received: " + message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
}