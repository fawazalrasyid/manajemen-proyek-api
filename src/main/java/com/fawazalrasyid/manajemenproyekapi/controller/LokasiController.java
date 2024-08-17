package com.fawazalrasyid.manajemenproyekapi.controller;

import com.fawazalrasyid.manajemenproyekapi.model.Lokasi;
import com.fawazalrasyid.manajemenproyekapi.service.LokasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LokasiController {

    @Autowired
    private LokasiService lokasiService;

    @PostMapping("/lokasi")
    public ResponseEntity<Map<String, Object>> createLokasi(@RequestBody Lokasi lokasi) {
        Lokasi savedLokasi = lokasiService.saveLokasi(lokasi);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Lokasi berhasil ditambahkan");
        response.put("data", savedLokasi);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/lokasi")
    public ResponseEntity<Map<String, Object>> getAllLokasi() {
        List<Lokasi> lokasiList = lokasiService.getAllLokasi();
        Map<String, Object> response = new LinkedHashMap<>();
        if (lokasiList.isEmpty()) {
            response.put("message", "Data lokasi kosong");
            response.put("data", lokasiList);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("message", "Data lokasi ditemukan");
        response.put("data", lokasiList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/lokasi/{id}")
    public ResponseEntity<Map<String, Object>> getLokasiById(@PathVariable Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Lokasi lokasi = lokasiService.getLokasiById(id);
            response.put("message", "Lokasi ditemukan");
            response.put("data", lokasi);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            response.put("message", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }

    @PutMapping("/lokasi/{id}")
    public ResponseEntity<Map<String, Object>> updateLokasi(@PathVariable Long id, @RequestBody Lokasi lokasi) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Lokasi updatedLokasi = lokasiService.updateLokasi(id, lokasi);
            response.put("message", "Lokasi ditemukan");
            response.put("data", updatedLokasi);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            response.put("message", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }

    @DeleteMapping("/lokasi/{id}")
    public ResponseEntity<Map<String, String>> deleteLokasi(@PathVariable Long id) {
        Map<String, String> response = new LinkedHashMap<>();
        try {
            lokasiService.deleteLokasi(id);
            response.put("message", "Lokasi berhasil dihapus");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            response.put("message", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }
}
