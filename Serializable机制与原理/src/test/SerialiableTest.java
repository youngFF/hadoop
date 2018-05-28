package test;

import model.Child;
import model.Parent;

import java.io.*;

public class SerialiableTest {


    public static void main(String[] args) throws IOException {


        Parent  parent = new Child() ;

        File file = new File("/home/hyena/object");

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(parent);



    }


}
