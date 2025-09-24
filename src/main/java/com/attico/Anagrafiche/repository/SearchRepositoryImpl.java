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

/*
identifica la classe come un bean, cioè un oggetto che deve essere gestito dal container di spring
in questo modo è compito del container configurare, instanziare e passare ad altre classi
 */
@Component
public class SearchRepositoryImpl implements SearchRepository {

    @Autowired //inietta automaticamente dipendenza in classe gestita dal container spring
    MongoClient client; //client MongoDB per connettersi al DB

    @Autowired //vedi su
    MongoConverter converter; //serve per convertire file BSON in oggetti java e viceversa

    @Override
    public List<Anagrafica> findByText(String text) {

        final List<Anagrafica> anagrafiche = new ArrayList<>();

        //pipeline di ricerca sui campi nome e cognome usando l'operatore search di MOngo DB Atlas Search
        MongoDatabase database = client.getDatabase("attico"); //si connette al database
        MongoCollection<Document> collection = database.getCollection("Anagrafiche"); //ottiene la collezione
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                new Document("query", text)
                .append("path", Arrays.asList("nome", "cognome"))))));

        result.forEach(doc -> anagrafiche.add(converter.read(Anagrafica.class, doc)));


        return anagrafiche;
    }
}
