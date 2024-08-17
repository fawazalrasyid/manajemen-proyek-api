package com.fawazalrasyid.api.repository;

import com.fawazalrasyid.api.model.Lokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {
}
