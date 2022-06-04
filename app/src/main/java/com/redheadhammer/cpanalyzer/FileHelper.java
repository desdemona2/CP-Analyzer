package com.redheadhammer.cpanalyzer;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {
    public static final String FILE = "CompetitionHistory.dat";

    public static void writeData (ArrayList<String[]> itemList, Context context) {
        try {
            /* file stream and object stream will be used to write the data in file */
            FileOutputStream fileOutputStream = context.openFileOutput(FILE, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(itemList);

            objectOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String[]> readData (Context context) {
        ArrayList<String[]> itemList = null;
        try {
            FileInputStream fileInputStream = context.openFileInput(FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            itemList = (ArrayList<String[]>) objectInputStream.readObject();
        }
        catch (FileNotFoundException e) {
            itemList = new ArrayList<>();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
