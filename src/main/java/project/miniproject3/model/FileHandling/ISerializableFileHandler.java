package project.miniproject3.model.FileHandling;

public interface ISerializableFileHandler {
    void serialize(String fileName, Object element);
    Object deserialize(String fileName);
}
