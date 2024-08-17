package com.fawazalrasyid.manajemenproyekapi.repository;

import com.fawazalrasyid.manajemenproyekapi.model.Proyek;
import com.fawazalrasyid.manajemenproyekapi.model.ProyekLokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProyekLokasiRepository extends JpaRepository<ProyekLokasi, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ProyekLokasi pl WHERE pl.proyek = :proyek")
    void deleteByProyek(Proyek proyek);

    List<ProyekLokasi> findByProyek(Proyek proyek);
}
