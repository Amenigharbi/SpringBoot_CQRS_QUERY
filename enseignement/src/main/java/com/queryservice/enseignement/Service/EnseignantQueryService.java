package com.queryservice.enseignement.Service;


import com.queryservice.enseignement.dto.EnsEvent;
import com.queryservice.enseignement.entity.Enseignant;
import com.queryservice.enseignement.repository.EnseignantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantQueryService {
    @Autowired
    private EnseignantRepo repo;

    public List<Enseignant> getEnseignants()
    {
        return repo.findAll();
    }


    public Enseignant getEns(@RequestParam Long id)
    {
        Optional<Enseignant> ens=repo.findById(id);
        if(ens.isPresent())
            return ens.get();
        else
            return null;
    }

    @KafkaListener(topics="enseignant-event-topic",groupId = "enseignant-event-group")
    public void processEnseignEvent(EnsEvent e)
    {
        Enseignant enseign=e.getEns();
        if(e.getEventType().equals("createEnseignant")){
            repo.save(enseign);
        }
        if(e.getEventType().equals("update enseignant")){
            Enseignant existingEns=repo.findById(enseign.getId()).get();
            existingEns.setFirstname(enseign.getFirstname());
            existingEns.setLastname(enseign.getLastname());
            existingEns.setAge(enseign.getAge());
            repo.save(existingEns);
        }
        else if (e.getEventType().startsWith("deleteEnseignant:")) {
            // Extraire l'ID de l'enseignant supprimé depuis le message
            String[] parts = e.getEventType().split(":");
            if (parts.length == 2) {
                Long deletedId = Long.parseLong(parts[1]);
                repo.deleteById(deletedId);

                // Ajouter un message indicatif
                System.out.println("Enseignant supprimé avec l'ID : " + deletedId);
            } else {
                System.out.println("Format de message de suppression invalide : " + e.getEventType());
            }
        }

    }

}
