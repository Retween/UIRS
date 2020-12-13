import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;

public class test {
    public static void main(String[] args)throws Exception {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))) {
            coordinats coordinats = new coordinats(384399000, 0.0549, 5.1, 0, 0, 0);
            for (double i = 0; i <= 2 * Math.PI; i += 2 * Math.PI / 100) {
                writer.write(String.valueOf(coordinats.coordinates(i)[0]) + " " + String.valueOf(coordinats.coordinates(i)[1]) + " " + String.valueOf(coordinats.coordinates(i)[2]) + "\n");
            }
        }
    }
}
