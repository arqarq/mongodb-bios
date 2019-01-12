package com.sda.bios;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.function.Consumer;

public class FilterExample {
    public static void main(String[] args) {
        try (MongoClient client = Utils.connect()) {
            MongoDatabase db = client.getDatabase("bios");
            MongoCollection<Document> bios = db.getCollection("bios");
            bios.find().forEach((Consumer<Document>) System.out::println);
            System.out.println("-----");
            bios.find().forEach((Consumer<Document>) FilterExample::printBio);
            System.out.println("-----");
            bios.find(Filters.eq("title", "Rear Admiral"))
                    .forEach((Consumer<Document>) FilterExample::printBio);
            System.out.println("-----");
            bios.find(Filters.eq("name.first", "Kristen"))
                    .forEach((Consumer<Document>) FilterExample::printBio);
            System.out.println("-----");
            bios.find(Filters.and(
                    Filters.eq("name.first", "John"),
                    Filters.eq("name.last", "Backus")))
                    .forEach((Consumer<Document>) FilterExample::printBio);
            System.out.println("-----");
            bios.find(Filters.all("contribs", "Fortran"))
                    .forEach((Consumer<Document>) FilterExample::printBio);
            System.out.println("-----");
            bios.find().projection(Projections.include("name"))
                    .forEach((Consumer<Document>) System.out::println);
        }
    }

    public static void printBio(Document doc) {
        Document name = doc.get("name", Document.class);
        System.out.println(name.getString("first") + " "
                + name.getString("last"));
    }
}
