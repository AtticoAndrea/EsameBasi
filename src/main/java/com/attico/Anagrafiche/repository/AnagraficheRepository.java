package com.attico.Anagrafiche.repository;

import com.attico.Anagrafiche.model.Anagrafica;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnagraficheRepository extends MongoRepository<Anagrafica, String> {

}
