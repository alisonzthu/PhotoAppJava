package com.example.macstudio.photoappjava.db;

import java.util.ArrayList;

public class DataGenerator {
    public static ArrayList<String> getDummyData() {
        final ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            data.add("string" + i);
        }
        return data;
    }
}
