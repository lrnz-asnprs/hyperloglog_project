import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class HashQualityEvaluation {

    public static int[] generateInput(int n) {
        long seed = 42;
        int[] arr = new Random(seed).ints(n, 1, n + 1).toArray();
        return arr;
    }

    static int[] generateInput2(int n) {
        int seed = 42;
        Set<Integer> elements = new HashSet<>();
        while (elements.size() != n) {
            int randInt = ThreadLocalRandom.current().nextInt(1, seed);
            elements.add(randInt);
        }
        return elements.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {

        int[] arr = new Random().ints(1000000, 1, 1000000).map(i -> HyperLogLog.h(i)).toArray();

        HashMap<Integer, Integer> p_distribution = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int key = HyperLogLog.p(arr[i]); // get p-value
            if (p_distribution.containsKey(key)) {
                int count = p_distribution.get(key);
                count++;
                p_distribution.put(key, count);
            } else {
                p_distribution.put(key, 1);
            }
        }

        /**
         * Print out for testing
         */
        for (int key : p_distribution.keySet()) {
            System.out.println("P-value: " + key + " with distribution: " + p_distribution.get(key));
        }

        /**
         * Write results to file
         */

        String eol = System.getProperty("line.separator");

        try (Writer writer = new FileWriter("hash_quality_results.csv")) {
            writer.append("p-value,frequency").append(eol);
            for (Map.Entry<Integer, Integer> entry : p_distribution.entrySet()) {
                writer.append(Integer.toString(entry.getKey())).append(',').append(Integer.toString(entry.getValue()))
                        .append(eol);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
