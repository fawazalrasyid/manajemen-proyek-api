package com.fawazalrasyid.api.controller;

import com.fawazalrasyid.api.model.Proyek;
import com.fawazalrasyid.api.service.ProyekService;
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
public class ProyekController {

    @Autowired
    private ProyekService proyekService;

    @PostMapping("/proyek")
    public ResponseEntity<Map<String, Object>> createProyek(@RequestBody Proyek proyek, @RequestParam Long lokasiId) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Proyek savedProyek = proyekService.saveProyek(proyek, lokasiId);
            response.put("message", "Proyek berhasil ditambahkan");
            response.put("data", savedProyek);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            response.put("message", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }

    @GetMapping("/proyek")
    public ResponseEntity<Map<String, Object>> getAllProyek() {
        List<Proyek> proyekList = proyekService.getAllProyek();
        Map<String, Object> response = new LinkedHashMap<>();
        if (proyekList.isEmpty()) {
            response.put("message", "Data proyek kosong");
            response.put("data", proyekList);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("message", "Data proyek ditemukan");
        response.put("data", proyekList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/proyek/{id}")
    public ResponseEntity<Map<String, Object>> getProyekById(@PathVariable Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Proyek proyek = proyekService.getProyekById(id);
            response.put("message", "Proyek ditemukan");
            response.put("data", proyek);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            response.put("message", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }

    @PutMapping("/proyek/{id}")
    public ResponseEntity<Map<String, Object>> updateProyek(@PathVariable Long id, @RequestBody Proyek proyek) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Proyek updatedProyek = proyekService.updateProyek(id, proyek);
            response.put("message", "Proyek berhasil diperbarui");
            response.put("data", updatedProyek);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            response.put("message", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }

    @DeleteMapping("/proyek/{id}")
    public ResponseEntity<Map<String, String>> deleteProyek(@PathVariable Long id) {
        Map<String, String> response = new LinkedHashMap<>();
        try {
            proyekService.deleteProyek(id);
            response.put("message", "Proyek berhasil dihapus");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            response.put("message", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }
}
