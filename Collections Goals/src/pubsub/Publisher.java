package pubsub;

public class Publisher implements Runnable{
    private final PubSub pubsub;
    private final String topic;
    private final String name;
    private volatile boolean running = true;

    public Publisher(PubSub pubsub, String topic, String name) {
        this.pubsub = pubsub;
        this.topic = topic;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            int messageCount = 0;
            while (running) {
                String message = name + " - Message " + messageCount++;
                pubsub.publish(topic, message);
                Thread.sleep(1000); // Simulate time between messages
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    public void stopPublishing() {
        running = false;
    }
}