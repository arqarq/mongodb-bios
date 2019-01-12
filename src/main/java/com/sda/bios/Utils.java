package com.sda.bios;

import com.mongodb.MongoClient;

public class Utils {

    public static MongoClient connect() {
        return new MongoClient("localhost", 27017);
    }
}
