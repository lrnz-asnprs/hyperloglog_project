import java.io.IOException;

public class EstimationError {

    
    
    
    public static void main(String[] args) throws NumberFormatException, IOException {

        int[] input = HashQualityEvaluation.generateInputUniqueRandom(10000, 99);

        HyperLogLog loglog = new HyperLogLog(1024);

        loglog.setRegistersInput(input);

        System.out.println(loglog.estimate());

    }

}
