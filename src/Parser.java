import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Parser provides functionality for counting the number of characters of the English
 * alphabet (not case-sensitive) in a given file and writing this data in a format "Key=value\n" to a new file
 *
 * @author Anton Shadrin
 * @version 1.0
 */
public class Parser {

    private final String pathFrom, pathTo;

    /**
     * The constructor allows you to specify where to read data from and where to write it
     *
     * @param pathFrom - file to open
     * @param pathTo   - file to write
     */
    public Parser(String pathFrom, String pathTo) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }

    /**
     * Private method for reading data from a file
     *
     * @return String with data read
     * @throws IOException if a wrong path given
     */
    private String readData() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.pathFrom))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString();
        }
    }

    /**
     * Private method for writing data into a file
     *
     * @throws IOException if a wrong path given (contains special characters)
     */
    private void writeData(Map<Character, Integer> data) throws IOException {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(this.pathTo))) {
            for (Map.Entry<Character, Integer> pair : data.entrySet()) {
                wr.write(pair.toString() + '\n');
            }
        }
    }

    /**
     * A public API method for counting the number of characters in a file.
     * Uses the following functions under the hood:
     *
     * @throws IOException from
     *                     Parser.readData
     *                     Parser.writeData
     * @see Parser#readData
     * @see Parser#writeData
     */
    public void count() throws IOException {
        String data;
        try {
            data = readData();
        } catch (IOException e) {
            throw new IOException("Invalid path to reading file\n");
        }

        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                continue;
            }
            counter.put(data.charAt(i), counter.getOrDefault(data.charAt(i), 0) + 1);
        }

        try {
            writeData(counter);
        } catch (IOException e) {
            throw new IOException("Invalid path to writing file\n");
        }
    }


}
