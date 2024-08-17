package com.fawazalrasyid.manajemenproyekapi.service;

import com.fawazalrasyid.manajemenproyekapi.model.Lokasi;
import com.fawazalrasyid.manajemenproyekapi.model.Proyek;
import com.fawazalrasyid.manajemenproyekapi.model.ProyekLokasi;
import com.fawazalrasyid.manajemenproyekapi.repository.LokasiRepository;
import com.fawazalrasyid.manajemenproyekapi.repository.ProyekLokasiRepository;
import com.fawazalrasyid.manajemenproyekapi.repository.ProyekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ProyekService {

    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    @Autowired
    private ProyekLokasiRepository proyekLokasiRepository;

    public List<ProyekLokasi> getAllProyek() {
        return proyekLokasiRepository.findAll();
    }

    public Proyek getProyekById(Long id) {
        return proyekRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyek tidak ditemukan"));
    }

    public ProyekLokasi getProyekLokasiByProyekId(Long proyekId) {
        return proyekLokasiRepository.findByProyekId(proyekId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Relasi Proyek dan Lokasi tidak ditemukan"));
    }


    public Proyek saveProyek(Proyek proyek, Long lokasiId) {
        Lokasi lokasi = lokasiRepository.findById(lokasiId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokasi tidak ditemukan"));

        Proyek savedProyek = proyekRepository.save(proyek);

        ProyekLokasi proyekLokasi = new ProyekLokasi();
        proyekLokasi.setProyek(savedProyek);
        proyekLokasi.setLokasi(lokasi);
        proyekLokasiRepository.save(proyekLokasi);

        return savedProyek;
    }

    public Proyek updateProyek(Long id, Proyek proyek, Long lokasiId) {
        Proyek existingProyek = proyekRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyek tidak ditemukan"));

        existingProyek.setNamaProyek(proyek.getNamaProyek());
        existingProyek.setClient(proyek.getClient());
        existingProyek.setTglMulai(proyek.getTglMulai());
        existingProyek.setTglSelesai(proyek.getTglSelesai());
        existingProyek.setPimpinanProyek(proyek.getPimpinanProyek());
        existingProyek.setKeterangan(proyek.getKeterangan());

        if (lokasiId != null) {
            Lokasi lokasi = lokasiRepository.findById(lokasiId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokasi tidak ditemukan"));

            proyekLokasiRepository.deleteByProyek(existingProyek);
            ProyekLokasi proyekLokasi = new ProyekLokasi();
            proyekLokasi.setProyek(existingProyek);
            proyekLokasi.setLokasi(lokasi);
            proyekLokasiRepository.save(proyekLokasi);
        }

        return proyekRepository.save(existingProyek);
    }

    public void deleteProyek(Long id) {
        Proyek existingProyek = proyekRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyek tidak ditemukan"));

        proyekLokasiRepository.deleteByProyek(existingProyek);
        proyekRepository.delete(existingProyek);
    }
}
