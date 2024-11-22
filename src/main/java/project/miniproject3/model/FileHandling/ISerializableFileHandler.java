package project.miniproject3.model.FileHandling;

/**
 * Interface for handling object serialization and deserialization.
 * Provides methods for serializing objects to a file and deserializing them back.
 */
public interface ISerializableFileHandler {

    /**
     * Serializes an object and writes it to a specified file.
     *
     * @param fileName the name of the file to which the object will be serialized
     * @param element the object to be serialized
     */
    void serialize(String fileName, Object element);

    /**
     * Deserializes an object from a specified file.
     *
     * @param fileName the name of the file from which the object will be deserialized
     * @return the deserialized object
     * @throws Exception if the deserialization fails
     */
    Object deserialize(String fileName) throws Exception;
}
