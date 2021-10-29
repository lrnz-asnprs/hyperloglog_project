import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Write {

    public static void writeLatexTabular(Record[] records, String filename) {
        
        File f = new File("tests\\" + filename); //put it in the tests folder
        
        try (PrintWriter pw = new PrintWriter(f)) {
            pw.println("\\begin{tabular}{rrr}"+"\\");
            pw.println(" $n$ & $m$ & 1st standard dev & 2nd standard dev " + " \\\\");
            for (int i = 0; i < records.length; ++i) {
                String[] fields = new String[] { Integer.toString(records[i].getN()), String.format(" %.6f", records[i].getM()),
                        String.format(" %.6f", records[i].getSigma1()), String.format(" %.6f", records[i].getSigma2()) };
                pw.println(String.join(" & ", fields) + " \\\\ ");
            }
            pw.println("\\end{tabular}");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
