import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static String[] parseStringRows(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();

        if (line == null) {
            br.close();
            return new String[0];
        }

        List<String> rows = new ArrayList<>();

        while (line != null) {
            rows.add(line);
            line = br.readLine();
        }

        br.close();
        return Converter.listToArr(rows);
    }
    public static int[] parseInts(String filename) throws IOException {
        String[] rows = parseStringRows(filename);
        int[] ints = new int[rows.length];

        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(rows[i]);
        }

        return ints;
    }

    public static int[][] parseManyInts(String filename) throws IOException {
        String[] rows = parseStringRows(filename);
        int[][] intRows = new int[rows.length][];

        for (int i = 0; i < intRows.length; i++) {
            String[] numbers = rows[i].split(" ");
            intRows[i] = Converter.arrStringToArrInt(numbers);
        }

        return intRows;
    }
}
