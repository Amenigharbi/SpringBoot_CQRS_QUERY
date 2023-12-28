package com.queryservice.enseignement.Controller;


import com.queryservice.enseignement.Service.EnseignantQueryService;
import com.queryservice.enseignement.dto.EnsEvent;
import com.queryservice.enseignement.entity.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/enseignants")
@RestController
public class EnseignantQueryController {
    @Autowired

    private EnseignantQueryService queryService;
    @GetMapping
    public List<Enseignant> getEnseign()
    {
       return queryService.getEnseignants();
    }

    @GetMapping("/get-ens")
    public Enseignant getEns(@PathVariable Long id){
        return queryService.getEns(id);
    }



}
