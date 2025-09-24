package com.attico.Anagrafiche.controller;

import com.attico.Anagrafiche.repository.AnagraficheRepository;
import com.attico.Anagrafiche.model.Anagrafica;
import com.attico.Anagrafiche.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    @Autowired //inietta automaticamente dipendenza in classe gestita dal container spring
    AnagraficheRepository repo;

    @Autowired //vedi su
    SearchRepository searchRepo;

    @ApiIgnore //questo metodo non viene mostrato nella doc swagger
    @RequestMapping(value="/") //quando qualcuno accede a "/" nel browser viene reindirizzato a swagger
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");

    }

    @GetMapping("/anagrafiche")
    public List<Anagrafica> getAllAnagrafiche(){
        return repo.findAll();
    }

    @PostMapping("/anagrafiche")
    public String addAnagrafica(@RequestBody Anagrafica anagrafica){ //Request Body prende Json dal client e converte in anagrafica
        if(repo.existsById(anagrafica.getNumTessera())){
            return "Esiste già un'anagrafica con id " + anagrafica.getNumTessera();
        } else {
            repo.save(anagrafica);
            return "Anagrafica con id " + anagrafica.getNumTessera() + " aggiunta con successo";
        }
    }

    @GetMapping("/anagrafiche/{text}")
    public List<Anagrafica> search(@PathVariable String text){ // PathVariable prende variabile dall'url
        return searchRepo.findByText(text);
    }

    @DeleteMapping("/anagrafiche/{id}")
    public String deleteAnagrafica(@PathVariable String id){ //vedi su
        if(repo.existsById(id)){
            repo.deleteById(id);
            return "Anagrafica con id " + id + " eliminata";
        } else {
            return "Nessuna anagrafica con id " + id + " trovata";
        }
    }

    @PutMapping("/anagrafiche/{id}")
    public String updateAnagrafica(@PathVariable String id, @RequestBody Anagrafica updatedData ){ //vedi su
        if(repo.existsById(id)){
            Anagrafica existing = repo.findById(id).get();

            existing.setNome(updatedData.getNome());
            existing.setCognome(updatedData.getCognome());
            existing.setEmail(updatedData.getEmail());
            existing.setTelefono(updatedData.getTelefono());

            repo.save(existing);
            return "Anagrafica con id " + id + " modificata";
        } else {
            return "Nessuna anagrafica con id " + id + " trovata";
        }
    }

}
