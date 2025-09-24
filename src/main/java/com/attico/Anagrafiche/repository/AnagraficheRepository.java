package com.attico.Anagrafiche.repository;

import com.attico.Anagrafiche.model.Anagrafica;
import org.springframework.data.mongodb.repository.MongoRepository;
/*
interfaccia che diventa una repositoty Spring Data MongoDB

Anagrafica -> classe modello (@Document) che rappresenta i documenti della collezione MongoDB
String -> tipo dell'id del documento

in questo modo eredita tutti i metodi CRUD da MongoRepository
 */
public interface AnagraficheRepository extends MongoRepository<Anagrafica, String> {

}
