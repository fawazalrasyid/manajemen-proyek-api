package com.fawazalrasyid.api.service;

import com.fawazalrasyid.api.model.Lokasi;
import com.fawazalrasyid.api.repository.LokasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class LokasiService {

    @Autowired
    private LokasiRepository lokasiRepository;

    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    public Lokasi getLokasiById(Long id) {
        return lokasiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokasi tidak ditemukan"));
    }

    public Lokasi saveLokasi(Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    public Lokasi updateLokasi(Long id, Lokasi lokasi) {
        Lokasi existingLokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokasi tidak ditemukan"));
        existingLokasi.setNamaLokasi(lokasi.getNamaLokasi());
        existingLokasi.setNegara(lokasi.getNegara());
        existingLokasi.setProvinsi(lokasi.getProvinsi());
        existingLokasi.setKota(lokasi.getKota());
        return lokasiRepository.save(existingLokasi);
    }

    public void deleteLokasi(Long id) {
        Lokasi existingLokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokasi tidak ditemukan"));
        lokasiRepository.delete(existingLokasi);
    }
}