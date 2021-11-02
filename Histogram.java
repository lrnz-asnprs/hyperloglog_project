package hyper;
import java.util.HashMap;
import java.io.File ;
import java.io.FileNotFoundException ;
import java.io.PrintWriter ;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Arrays;
class Histogram {

  private double[] results;
  private final double binWidth;
  private final double min;
  private final double max;
  private final int bins;
  private HashMap<Integer,Integer> histo = new HashMap<>();


  public Histogram(double[] res, int bins){
    this.results = res;
    Arrays.sort(results);
    this.min = this.results[0]; System.out.println("min: "+min);
    this.max = this.results[this.results.length-1]; System.out.println("max: "+max);
    this.bins = bins;
    this.binWidth = (max-min)/bins;System.out.println("binWidt: "+binWidth);

    for (double r : results){
      
      for (int i = 0; i < bins; i++){
        double lower = min+i*binWidth;
        double upper = ((i+1) == bins) ? max+1 : min+(i+1)*binWidth;
        if (r >= lower && r < upper) {
          if (histo.containsKey(i)) {
            int count = histo.get(i);
            histo.put(i,(count+1));
          }
          else {
            histo.put(i,1);
          }
        }
      }
    }
  }

  public void toCSV(String filename){
    String output = "";

    Set<Integer> keys = new HashSet<>(histo.keySet());

    for(Integer key : keys){
      output = output + String.format("%d , %d\n",(int)(min+key*binWidth), histo.get(key));
    }
    File f = new File(filename);
    try (PrintWriter pw = new PrintWriter(f);){
      for (Integer key : keys){
       pw.println(String.format("%d , %d",(int)(min+key*binWidth), histo.get(key)));
       System.out.println(String.format("%d , %d",(int)(min+key*binWidth), histo.get(key)));
      }
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }


  }







