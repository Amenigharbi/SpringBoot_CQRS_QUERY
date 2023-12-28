package com.queryservice.enseignement.repository;

import com.queryservice.enseignement.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepo extends JpaRepository<Enseignant,Long> {

}
