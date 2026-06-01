/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author loegu
 */
public class CryptoBox {

    private int[][] C = {{-3, 5, 6},
                         {-1, 2, 2},
                         {1, -1, -1}
                        };
    private int[][] invC = {{0, 1, 2},
                            {-1, 3, 0},
                            {1, -2, 1}
                           };

    // appends spaces to the given string to get a string length of n 
    public String padString(String string, int n) {
        int currentLength = string.length();
        int paddingLength = (n - (currentLength % n)) % n;
        StringBuilder paddedString = new StringBuilder(string);
        for (int i = 0; i < paddingLength; i++) {
            paddedString.append(" ");
        }
        return paddedString.toString();
    }
    
    public int[][] multiply(int[][] A, int[][] B) {
        // le resultat sera une matrice C m * n
        int m = A.length; // nombre de lignes
        int n = B[0].length; // nombre de colonnes
        int p = A[0].length; // nombre de lignes de B
        int[][] C = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < p; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
    
    public String encodeMessage(String textToEncode) {
        // padding the string with spaces to get the correct length
        // for insertion into M 
        while (textToEncode.length() % C.length != 0) {
            textToEncode += " ";
        }
        
        // create Matrix M and fill with ASCII codes
        int numberOfCols = textToEncode.length() / C.length;
        int[][] M = new int[C.length][numberOfCols];
        int pos = 0;
        
        for (int col = 0; col < numberOfCols; col++) {
            for (int row = 0; row < C.length; row++) {
                char character = textToEncode.charAt(pos);
                int asciiCode = (int) character;
                M[row][col] = asciiCode;
                pos++;
            }
        }
        
        // get the encoded message B = C * M
        int[][] B = multiply(C, M);
        
        // generate the encrypted message string
        String encodedMessage = "";
        for(int col = 0; col < B[0].length; col++) {
            for (int row = 0; row < B.length; row++) {
                encodedMessage += B[row][col] + ";";
            }
        }
        
        // delete last ";"
        encodedMessage = encodedMessage.substring(0, encodedMessage.length() - 1);
        
        return encodedMessage;
    }

    public String decodeMessage(String encodedMessage) {
        // put the encrypted message in an array
        String[] codes = encodedMessage.split(";");

        int numberOfCols = codes.length / invC.length;
        
        // generate matrix B
        int pos = 0;
        int[][] B = new int[invC.length][numberOfCols];

        for (int col = 0; col < numberOfCols; col++) {
            for (int row = 0; row < invC.length; row++) {
                B[row][col] = Integer.valueOf(codes[pos]);
                pos++;
            }
        }

        // get the decoded matrix M
        int[][] M = multiply(invC, B);

        // recombine the contents of M to a string
        String decodedMessage = "";

        for (int col = 0; col < M[0].length; col++) {
            for (int row = 0; row < M.length; row++) {
                decodedMessage += (char) M[row][col];
            }
        }

        return decodedMessage;
    }
}