package project.miniproject3.model.FileHandling;

/**
 * Interface for handling plain text file operations.
 * Defines methods for reading from and writing to text files.
 */
public interface IPlainTextFileHandler {

    /**
     * Writes content to a specified plain text file.
     *
     * @param fileName the name of the file where content will be written
     * @param content the content to be written to the file
     */
    void writeToFile(String fileName, String content);

    /**
     * Reads content from a specified plain text file.
     *
     * @param fileName the name of the file from which content will be read
     * @return an array of strings containing the lines of content from the file
     */
    String[] readFromFile(String fileName);
}
