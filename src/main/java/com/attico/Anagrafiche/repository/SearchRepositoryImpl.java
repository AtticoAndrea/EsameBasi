package com.attico.Anagrafiche.repository;

import com.attico.Anagrafiche.model.Anagrafica;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Anagrafica> findByText(String text) {

        final List<Anagrafica> anagrafiche = new ArrayList<>();

        MongoDatabase database = client.getDatabase("attico");
        MongoCollection<Document> collection = database.getCollection("Anagrafiche");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                new Document("query", text)
                .append("path", Arrays.asList("nome", "cognome"))))));

        result.forEach(doc -> anagrafiche.add(converter.read(Anagrafica.class, doc)));


        return anagrafiche;
    }
}
