package com.db.apilivraria.repositories;

import com.db.apilivraria.models.AluguelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<AluguelModel, Long> {
}
