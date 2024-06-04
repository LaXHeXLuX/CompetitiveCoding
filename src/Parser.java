import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static String[] parseStrings(String filename) throws IOException {
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
    public static String[][] parseManyStrings(String filename) throws IOException {
        return parseManyStrings(filename, " ");
    }
    public static String[][] parseManyStrings(String filename, String splitter) throws IOException {
        String[] rows = parseStrings(filename);
        String[][] stringRows = new String[rows.length][];

        for (int i = 0; i < stringRows.length; i++) {
            stringRows[i] = rows[i].split(splitter);
        }

        return stringRows;
    }
    public static int[] parseInts(String filename) throws IOException {
        String[] rows = parseStrings(filename);
        int[] ints = new int[rows.length];

        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(rows[i]);
        }

        return ints;
    }

    public static int[][] parseManyInts(String filename) throws IOException {
        return parseManyInts(filename, " ");
    }
    public static int[][] parseManyInts(String filename, String splitter) throws IOException {
        String[][] stringRows = parseManyStrings(filename, splitter);
        int[][] intRows = new int[stringRows.length][];

        for (int i = 0; i < intRows.length; i++) {
            intRows[i] = Converter.arrStringToArrInt(stringRows[i]);
        }

        return intRows;
    }
}
