import java.util.concurrent.*;
import java.util.*;

public class PubSub {
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Subscriber>> subscribersMap = new ConcurrentHashMap<>();
    private final ConcurrentLinkedQueue<Publisher> publishersQueue = new ConcurrentLinkedQueue<>();

    // Add a subscriber to a topic
    public void addSubscriber(String topic, Subscriber subscriber) {
        subscribersMap.computeIfAbsent(topic, k -> new CopyOnWriteArrayList<>()).add(subscriber);
    }

    // Remove a subscriber from a topic
    public void removeSubscriber(String topic, Subscriber subscriber) {
        List<Subscriber> subscribers = subscribersMap.get(topic);
        if (subscribers != null) {
            subscribers.remove(subscriber);
        }
    }

    // Add a publisher
    public void addPublisher(Publisher publisher) {
        publishersQueue.add(publisher);
        new Thread(publisher).start(); // Start the publisher in a new thread
    }

    // Remove a publisher
    public void removePublisher(Publisher publisher) {
        publishersQueue.remove(publisher);
        publisher.stopPublishing();
    }

    // Publish a message to a topic
    public void publish(String topic, String message) {
        List<Subscriber> subscribers = subscribersMap.get(topic);
        if (subscribers != null) {
            for (Subscriber subscriber : subscribers) {
                subscriber.queue.offer(message);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        PubSub pubsub = new PubSub();

        // Create some subscribers
        Subscriber subscriber1 = new Subscriber("Subscriber1");
        Subscriber subscriber2 = new Subscriber("Subscriber2");

        // Start subscriber threads
        subscriber1.start();
        subscriber2.start();

        // Add subscribers to a topic
        pubsub.addSubscriber("topic1", subscriber1);
        pubsub.addSubscriber("topic1", subscriber2);

        // Create and add publishers
        Publisher publisher1 = new Publisher(pubsub, "topic1", "Publisher1");
        Publisher publisher2 = new Publisher(pubsub, "topic1", "Publisher2");
        pubsub.addPublisher(publisher1);
        pubsub.addPublisher(publisher2);

        // Simulate some time for publishing
        Thread.sleep(5000);

        // Remove a publisher
        pubsub.removePublisher(publisher1);

        // Simulate some more time
        Thread.sleep(5000);

        // Gracefully shutdown the subscribers
        pubsub.publish("topic1", "END");
        subscriber1.join();
        subscriber2.join();


    }
}
