package project.miniproject3.model.FileHandling;

public interface IPlainTextFileHandler {
    void writeToFile(String fileName, String content);
    String[] readFromFile(String fileName);
}
