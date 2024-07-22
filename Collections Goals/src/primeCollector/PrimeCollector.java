package primeCollector;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.Collector;

public class PrimeCollector implements Collector<Integer, Set<Integer>, List<Integer>> {

    // Helper method to check if a number is prime
    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }

    @Override
    public Supplier<Set<Integer>> supplier() {
        return ConcurrentHashMap::newKeySet; // thread-safe set to handle parallel processing
    }

    @Override
    public BiConsumer<Set<Integer>, Integer> accumulator() {
        return (set, num) -> {
            if (isPrime(num)) {
                set.add(num);
            }
        };
    }

    @Override
    public BinaryOperator<Set<Integer>> combiner() {
        return (set1, set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<Integer>, List<Integer>> finisher() {
        return ArrayList::new;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT, Characteristics.UNORDERED));
    }


    public static void main(String[] args) {

        // Generate a large stream of integers (example with up to 1 million elements)
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1_000_000; i++) {
            numbers.add(random.nextInt(1_000_000)); // numbers between 0 and 999999
        }

        // Process the stream in parallel and collect prime numbers
        long startTime = System.currentTimeMillis();

        List<Integer> primes = numbers.parallelStream()
                .collect(new PrimeCollector());

        long endTime = System.currentTimeMillis();

        // Log execution time
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        // Log the number of unique prime numbers found
        System.out.println("Number of unique primes: " + primes.size());

        // Optional: Print the primes (limited output for demonstration)
        primes.stream().limit(10).forEach(System.out::println);
    }
}
