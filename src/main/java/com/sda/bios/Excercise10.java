package com.sda.bios;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.function.Consumer;

public class Excercise10 {
    public static void main(String[] args) {
        try (MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");

            MongoCollection<Document> bios = db.getCollection("bios");
            bios.find(Filters.elemMatch("awards",
                    Filters.and(
                            Filters.eq("award", "Turing Award"),
                            Filters.lt("year", 2000))))
                    .forEach((Consumer<Document>) FilterExample::printBio);
        }
    }
}
