package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    String filename = "parse.txt";

    @BeforeEach
    public void createFile() throws IOException {
        File file = new File(filename);
        if (!file.createNewFile()) throw new TestAbortedException("File " + file.getName() + " already exists!");
    }
    @AfterEach
    public void deleteFile() {
        File file = new File(filename);
        if (!file.delete()) throw new TestAbortedException("File " + file.getName() + " doesn't exist!");
    }
    @Test
    void parseStrings() throws IOException {
        assertArrayEquals(new String[] {}, Parser.parseStrings(filename));

        FileWriter fw1 = new FileWriter(filename);
        fw1.write("a");
        fw1.close();
        assertArrayEquals(new String[] {"a"}, Parser.parseStrings(filename));

        FileWriter fw2 = new FileWriter(filename);
        fw2.write("\n");
        fw2.close();
        assertArrayEquals(new String[] {""}, Parser.parseStrings(filename));

        FileWriter fw3 = new FileWriter(filename);
        fw3.write("a\nb");
        fw3.close();
        assertArrayEquals(new String[] {"a", "b"}, Parser.parseStrings(filename));

        FileWriter fw4 = new FileWriter(filename);
        fw4.write("a very\ncool brown\ndog");
        fw4.close();
        assertArrayEquals(new String[] {"a very", "cool brown", "dog"}, Parser.parseStrings(filename));
    }

    @Test
    void parseManyStrings() throws IOException {
        assertArrayEquals(new String[][] {}, Parser.parseManyStrings(filename));

        FileWriter fw1 = new FileWriter(filename);
        fw1.write("1 a");
        fw1.close();
        assertArrayEquals(new String[][] {{"1", "a"}}, Parser.parseManyStrings(filename));

        FileWriter fw2 = new FileWriter(filename);
        fw2.write("12,3\n456");
        fw2.close();
        assertArrayEquals(new String[][] {{"12", "3"}, {"456"}}, Parser.parseManyStrings(filename, ","));
    }

    @Test
    void parseInts() throws IOException {
        assertArrayEquals(new int[] {}, Parser.parseInts(filename));

        FileWriter fw1 = new FileWriter(filename);
        fw1.write("1");
        fw1.close();
        assertArrayEquals(new int[] {1}, Parser.parseInts(filename));

        FileWriter fw2 = new FileWriter(filename);
        fw2.write("123\n456");
        fw2.close();
        assertArrayEquals(new int[] {123, 456}, Parser.parseInts(filename));
    }

    @Test
    void parseManyInts() throws IOException {
        assertArrayEquals(new int[][] {}, Parser.parseManyInts(filename));

        FileWriter fw1 = new FileWriter(filename);
        fw1.write("1 2");
        fw1.close();
        assertArrayEquals(new int[][] {{1, 2}}, Parser.parseManyInts(filename));

        FileWriter fw2 = new FileWriter(filename);
        fw2.write("123\n45,6");
        fw2.close();
        assertArrayEquals(new int[][] {{123}, {45, 6}}, Parser.parseManyInts(filename, ","));
    }
}