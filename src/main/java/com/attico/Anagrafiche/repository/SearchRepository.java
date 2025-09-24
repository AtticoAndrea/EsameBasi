package com.attico.Anagrafiche.repository;

import com.attico.Anagrafiche.model.Anagrafica;

import java.util.List;
/*
interfaccia per creare un metodo di ricerca su campi personalizzati e non solo per id
 */
public interface SearchRepository {

    List<Anagrafica> findByText(String text);
}
