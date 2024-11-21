package project.miniproject3.model.FileHandling;

import java.io.*;

/**
 * Implementation of the IPlainTextFileHandler interface for handling plain text files.
 * Provides methods for reading from and writing to text files.
 */
public class PlainTextFileHandler implements IPlainTextFileHandler {

    /**
     * Writes the specified content to a file.
     *
     * @param fileName the name of the file where the content will be written
     * @param content the content to write to the file
     */
    @Override
    public void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads content from a file and returns it as an array of strings.
     * Each line in the file is treated as a separate string in the array.
     *
     * @param fileName the name of the file from which to read
     * @return an array of strings containing the lines of the file
     */
    @Override
    public String[] readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.trim()).append(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().split(",");
    }
}