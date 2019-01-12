package com.sda.bios;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.function.Consumer;

public class Excercise9 {
    public static void main(String[] args) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try (MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");

            MongoCollection<Document> bios = db.getCollection("bios");
            bios.find(Filters.lt("birth", dateFormat.parse("1960-01-01")))
                    .forEach((Consumer<Document>) FilterExample::printBio);
        }
    }
}
