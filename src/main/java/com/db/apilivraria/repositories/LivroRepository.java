package com.db.apilivraria.repositories;

import com.db.apilivraria.models.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, Long> {
}
