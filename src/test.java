import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class test {

  public static void main(String[] args) throws Exception {
    coordinates coordinates = new coordinates(1000000, 0.016, 0.00005, 2, 2, 0);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))) {
      for (double i = 0; i <= 2 * Math.PI; i += 2 * Math.PI / 100) {
        double[] x = coordinates.coordinates(i);
        writer.write(x[0] + " " + x[1] + " " + x[2] + "\n");

        coordinates.orbitElemCalc(x);
      }
    }

    HashMap<String, Double> orbitElem = coordinates
        .orbitElemCalc(coordinates.coordinates(1.5 * Math.PI));
    for (Map.Entry<String, Double> entry : orbitElem.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }
}
