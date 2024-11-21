package project.miniproject3.model.FileHandling;

import java.io.*;

/**
 * Implementation of the ISerializableFileHandler interface for handling serialization and deserialization of objects.
 * Provides methods for serializing objects to a file and deserializing objects from a file.
 */
public class SerializableFileHandler implements ISerializableFileHandler {

    /**
     * Serializes an object and writes it to a file.
     *
     * @param fileName the name of the file where the object will be serialized to
     * @param element the object to serialize
     */
    @Override
    public void serialize(String fileName, Object element) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(element);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes an object from a file.
     *
     * @param fileName the name of the file from which to deserialize the object
     * @return the deserialized object, or null if an error occurs during deserialization
     * @throws Exception if the file does not exist
     */
    @Override
    public Object deserialize(String fileName) throws Exception {
        File file = new File(fileName);

        if (!file.exists()) {
            throw new FileNotFoundException("El archivo " + fileName + " no existe.");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Object) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
