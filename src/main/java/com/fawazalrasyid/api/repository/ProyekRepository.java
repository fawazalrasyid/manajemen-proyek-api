package com.fawazalrasyid.api.repository;

import com.fawazalrasyid.api.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Long> {
}
