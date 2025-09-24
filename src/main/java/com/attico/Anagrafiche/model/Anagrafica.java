package com.attico.Anagrafiche.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//ogni oggetto di Anagrafiche sarà salvato nella collection mongoDB "Anagrafiche", Anagrafiche è un modello
@Document(collection = "Anagrafiche")
public class Anagrafica {

    @Id //imposta chiave primaria del documento
    private String numTessera;

    private String nome;
    private String cognome;
    private String email;
    private String telefono;

    //costruttore vuoto è richiesto da Spring Data
    public Anagrafica() {
    }

    public String getNumTessera() {
        return numTessera;
    }

    public void setNumTessera(String numTessera) {
        this.numTessera = numTessera;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    @Override
    public String toString() {
        return "Anagrafica{" +
                "numTessera=" + numTessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono +
                '}';
    }
}
