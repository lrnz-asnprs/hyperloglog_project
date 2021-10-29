import java.io.IOException;

public class Estimate {

    private int n;
    private int m;
    private double[] results;
    private int runs;
    private HyperLogLog estimator;

    public Estimate(int n, int m, int runs) {
        this.n = n;
        this.m = m;
        this.runs = runs;
        results = new double[runs];
        estimator = new HyperLogLog(m);
    }

    public void generateResults() throws NumberFormatException, IOException {

        for (int i=0; i < runs; i++) { 
            int[] input = HashQualityEvaluation.generateInputUniqueRandom(n, i); //generate random input of size n and i simply be seed value
            estimator.setRegistersInput(input);
            results[i] = estimator.estimate();
        }
    }

    public int getN() {
        return this.n;
    }
    public int getM() {
        return this.m;
    }
    public int getRuns() {
        return this.runs;
    }
    public double[] getResults() {
        return this.results;
    }
    


}
    
    // public static void main(String[] args) throws NumberFormatException, IOException {

    //     int[] input = HashQualityEvaluation.generateInputUniqueRandom(10000, 42);

    //     HyperLogLog loglog = new HyperLogLog(1024);

    //     loglog.setRegistersInput(input);

    //     System.out.println(loglog.estimate());

    // }

