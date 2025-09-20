package com.attico.Anagrafiche.repository;

import com.attico.Anagrafiche.model.Anagrafica;

import java.util.List;

public interface SearchRepository {

    List<Anagrafica> findByText(String text);
}
