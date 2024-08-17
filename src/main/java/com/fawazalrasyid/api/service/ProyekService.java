package com.fawazalrasyid.api.service;

import com.fawazalrasyid.api.model.Lokasi;
import com.fawazalrasyid.api.model.Proyek;
import com.fawazalrasyid.api.model.ProyekLokasi;
import com.fawazalrasyid.api.repository.LokasiRepository;
import com.fawazalrasyid.api.repository.ProyekLokasiRepository;
import com.fawazalrasyid.api.repository.ProyekRepository;
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

    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    public Proyek getProyekById(Long id) {
        return proyekRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyek tidak ditemukan"));
    }

    public Proyek saveProyek(Proyek proyek, Long lokasiId) {
        Lokasi lokasi = lokasiRepository.findById(lokasiId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokasi tidak ditemukan"));

        // Simpan proyek
        Proyek savedProyek = proyekRepository.save(proyek);

        // Simpan relasi
        ProyekLokasi proyekLokasi = new ProyekLokasi();
        proyekLokasi.setProyek(savedProyek);
        proyekLokasi.setLokasi(lokasi);
        proyekLokasiRepository.save(proyekLokasi);

        return savedProyek;
    }

    public Proyek updateProyek(Long id, Proyek proyek) {
        Proyek existingProyek = proyekRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyek tidak ditemukan"));
        existingProyek.setNamaProyek(proyek.getNamaProyek());
        existingProyek.setClient(proyek.getClient());
        existingProyek.setTglMulai(proyek.getTglMulai());
        existingProyek.setTglSelesai(proyek.getTglSelesai());
        existingProyek.setPimpinanProyek(proyek.getPimpinanProyek());
        existingProyek.setKeterangan(proyek.getKeterangan());
        return proyekRepository.save(existingProyek);
    }

    public void deleteProyek(Long id) {
        Proyek existingProyek = proyekRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyek tidak ditemukan"));

        // Hapus relasi
        proyekLokasiRepository.deleteByProyek(existingProyek);

        // Hapus proyek
        proyekRepository.delete(existingProyek);
    }
}