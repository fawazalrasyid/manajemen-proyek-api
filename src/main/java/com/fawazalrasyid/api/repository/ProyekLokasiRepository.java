package com.fawazalrasyid.api.repository;

import com.fawazalrasyid.api.model.Proyek;
import com.fawazalrasyid.api.model.ProyekLokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProyekLokasiRepository extends JpaRepository<ProyekLokasi, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ProyekLokasi pl WHERE pl.proyek = :proyek")
    void deleteByProyek(Proyek proyek);
}
