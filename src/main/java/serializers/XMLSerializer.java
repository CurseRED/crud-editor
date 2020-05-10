package main.java.serializers;

import jdk.jfr.Description;
import main.java.util.Serializer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Description("XML сериализатор")
public class XMLSerializer implements Serializer, Serializable {

    @Override
    @Description("xml")
    public void serialize(File file, List<Object> objectList) {
        XMLEncoder xmlEncoder = null;
        try{
            xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        if (xmlEncoder != null) {
            for (var o : objectList)
                xmlEncoder.writeObject(o);
            xmlEncoder.flush();
            xmlEncoder.close();
        }
    }

    @Override
    public List<Object> deserialize(File file) {
        List<Object> list = new ArrayList<>();
        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                Object o = xmlDecoder.readObject();
                list.add(o);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return list;
    }
}
