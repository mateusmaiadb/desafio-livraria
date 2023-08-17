package com.db.apilivraria.repositories;

import com.db.apilivraria.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByCpf(String cpf);
}