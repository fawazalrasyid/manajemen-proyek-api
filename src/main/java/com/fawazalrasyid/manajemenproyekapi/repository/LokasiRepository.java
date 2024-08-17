package com.fawazalrasyid.manajemenproyekapi.repository;

import com.fawazalrasyid.manajemenproyekapi.model.Lokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {
}
