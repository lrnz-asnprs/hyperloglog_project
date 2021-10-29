import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        
        Estimate estimation = new Estimate(10000, 1024, 100);

        estimation.generateResults();

        double[] results = estimation.getResults();

        System.out.println(Arrays.toString(results));

    }
}
