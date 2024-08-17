package com.fawazalrasyid.manajemenproyekapi.repository;

import com.fawazalrasyid.manajemenproyekapi.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Long> {
}
