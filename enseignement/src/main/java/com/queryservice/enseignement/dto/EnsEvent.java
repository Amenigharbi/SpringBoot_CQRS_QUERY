package com.queryservice.enseignement.dto;

import com.queryservice.enseignement.entity.Enseignant;
import com.queryservice.enseignement.entity.Enseignant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnsEvent {

    private String eventType;
    private Enseignant ens ;

}
