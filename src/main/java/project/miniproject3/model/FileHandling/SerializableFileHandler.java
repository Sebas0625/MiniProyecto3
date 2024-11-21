package project.miniproject3.model.FileHandling;

import java.io.*;

public class SerializableFileHandler implements ISerializableFileHandler {
    @Override
    public void serialize(String fileName, Object element) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(element);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(String fileName) throws Exception{
        File file = new File(fileName);

        if (!file.exists()) {
            throw new FileNotFoundException("El archivo " + fileName + " no existe.");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            return (Object) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
