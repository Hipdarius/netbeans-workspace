package u1_mocktestjune;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CryptoBox {

    private int[][] C = {
        {-3, 5, 6},
        {-1, 2, 2},
        {1, -1, -1}
    };

    private int[][] invC = {
        {0, 1, 2},
        {-1, 3, 0},
        {1, -2, 1}
    };

    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int m = B.length;
        int p = B[0].length;
        int[][] result = new int[n][p];

        // Calculate the matrix product A * B and store it in result.
        return result;
    }

    public String encodeMessage(String textToEncode) {
        // Encode the text with matrix C and return numbers separated by semicolons.
        return "";
    }

    public String decodeMessage(String encodedMessage) {
        // Decode the semicolon-separated message with matrix invC.
        return "";
    }

    public String loadFromFile(String fileName) throws FileNotFoundException, IOException {
        // Read and return the first line of the selected text file.
        return "";
    }

    public void saveToFile(String fileName, String line) throws IOException {
        // Write the given line to the selected text file.
    }
}
