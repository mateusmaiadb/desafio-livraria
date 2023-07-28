package com.db.apilivraria.repositories;

import com.db.apilivraria.models.LocatarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocatarioRepository extends JpaRepository<LocatarioModel, Long> {
}
