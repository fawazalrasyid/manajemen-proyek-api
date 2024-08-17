package com.fawazalrasyid.manajemenproyekapi.controller;

import com.fawazalrasyid.manajemenproyekapi.model.ProyekLokasi;
import com.fawazalrasyid.manajemenproyekapi.model.Proyek;
import com.fawazalrasyid.manajemenproyekapi.service.ProyekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

            ProyekLokasi proyekLokasi = proyekService.getProyekLokasiByProyekId(savedProyek.getId());

            Map<String, Object> proyekData = new LinkedHashMap<>();
            proyekData.put("id", savedProyek.getId());
            proyekData.put("namaProyek", savedProyek.getNamaProyek());
            proyekData.put("client", savedProyek.getClient());
            proyekData.put("tglMulai", savedProyek.getTglMulai());
            proyekData.put("tglSelesai", savedProyek.getTglSelesai());
            proyekData.put("pimpinanProyek", savedProyek.getPimpinanProyek());
            proyekData.put("keterangan", savedProyek.getKeterangan());
            proyekData.put("createdAt", savedProyek.getCreatedAt());

            Map<String, Object> lokasiData = new LinkedHashMap<>();
            lokasiData.put("id", proyekLokasi.getLokasi().getId());
            lokasiData.put("namaLokasi", proyekLokasi.getLokasi().getNamaLokasi());
            lokasiData.put("negara", proyekLokasi.getLokasi().getNegara());
            lokasiData.put("provinsi", proyekLokasi.getLokasi().getProvinsi());
            lokasiData.put("kota", proyekLokasi.getLokasi().getKota());
            lokasiData.put("createdAt", proyekLokasi.getLokasi().getCreatedAt());

            proyekData.put("lokasi", lokasiData);

            response.put("message", "Proyek berhasil ditambahkan");
            response.put("data", proyekData);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            response.put("message", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }

    @GetMapping("/proyek")
    public ResponseEntity<Map<String, Object>> getAllProyek() {
        List<ProyekLokasi> proyekLokasiList = proyekService.getAllProyek();
        Map<String, Object> response = new LinkedHashMap<>();

        if (proyekLokasiList.isEmpty()) {
            response.put("message", "Data proyek kosong");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<Map<String, Object>> proyekWithLokasi = proyekLokasiList.stream().map(proyekLokasi -> {
            Map<String, Object> proyekData = new LinkedHashMap<>();
            proyekData.put("id", proyekLokasi.getProyek().getId());
            proyekData.put("namaProyek", proyekLokasi.getProyek().getNamaProyek());
            proyekData.put("client", proyekLokasi.getProyek().getClient());
            proyekData.put("tglMulai", proyekLokasi.getProyek().getTglMulai());
            proyekData.put("tglSelesai", proyekLokasi.getProyek().getTglSelesai());
            proyekData.put("pimpinanProyek", proyekLokasi.getProyek().getPimpinanProyek());
            proyekData.put("keterangan", proyekLokasi.getProyek().getKeterangan());
            proyekData.put("createdAt", proyekLokasi.getProyek().getCreatedAt());

            Map<String, Object> lokasiData = new LinkedHashMap<>();
            lokasiData.put("id", proyekLokasi.getLokasi().getId());
            lokasiData.put("namaLokasi", proyekLokasi.getLokasi().getNamaLokasi());
            lokasiData.put("negara", proyekLokasi.getLokasi().getNegara());
            lokasiData.put("provinsi", proyekLokasi.getLokasi().getProvinsi());
            lokasiData.put("kota", proyekLokasi.getLokasi().getKota());
            lokasiData.put("createdAt", proyekLokasi.getLokasi().getCreatedAt());

            proyekData.put("lokasi", lokasiData);
            return proyekData;
        }).collect(Collectors.toList());

        response.put("message", "Data proyek ditemukan");
        response.put("data", proyekWithLokasi);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/proyek/{id}")
    public ResponseEntity<Map<String, Object>> updateProyek(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Proyek proyek = new Proyek();
            proyek.setNamaProyek((String) request.get("namaProyek"));
            proyek.setClient((String) request.get("client"));
            proyek.setTglMulai(java.sql.Date.valueOf((String) request.get("tglMulai")));
            proyek.setTglSelesai(java.sql.Date.valueOf((String) request.get("tglSelesai")));
            proyek.setPimpinanProyek((String) request.get("pimpinanProyek"));
            proyek.setKeterangan((String) request.get("keterangan"));

            Long lokasiId = request.containsKey("lokasiId") ? ((Number) request.get("lokasiId")).longValue() : null;

            Proyek updatedProyek = proyekService.updateProyek(id, proyek, lokasiId);

            ProyekLokasi proyekLokasi = proyekService.getProyekLokasiByProyekId(updatedProyek.getId());

            Map<String, Object> proyekData = new LinkedHashMap<>();
            proyekData.put("id", updatedProyek.getId());
            proyekData.put("namaProyek", updatedProyek.getNamaProyek());
            proyekData.put("client", updatedProyek.getClient());
            proyekData.put("tglMulai", updatedProyek.getTglMulai());
            proyekData.put("tglSelesai", updatedProyek.getTglSelesai());
            proyekData.put("pimpinanProyek", updatedProyek.getPimpinanProyek());
            proyekData.put("keterangan", updatedProyek.getKeterangan());
            proyekData.put("createdAt", updatedProyek.getCreatedAt());

            Map<String, Object> lokasiData = new LinkedHashMap<>();
            lokasiData.put("id", proyekLokasi.getLokasi().getId());
            lokasiData.put("namaLokasi", proyekLokasi.getLokasi().getNamaLokasi());
            lokasiData.put("negara", proyekLokasi.getLokasi().getNegara());
            lokasiData.put("provinsi", proyekLokasi.getLokasi().getProvinsi());
            lokasiData.put("kota", proyekLokasi.getLokasi().getKota());
            lokasiData.put("createdAt", proyekLokasi.getLokasi().getCreatedAt());

            proyekData.put("lokasi", lokasiData);

            response.put("message", "Proyek berhasil diperbarui");
            response.put("data", proyekData);
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
