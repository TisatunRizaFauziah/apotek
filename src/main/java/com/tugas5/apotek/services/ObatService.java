package com.tugas5.apotek.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas5.apotek.models.Obat;
import com.tugas5.apotek.repositories.ObatRepository;



@Service
public class ObatService {

    @Autowired
    private ObatRepository obatRepository;

    public void save(Obat obat) {
        obatRepository.save(obat);
    }

    public void deleteById(Integer id) {
        obatRepository.deleteById(id);
    }

    public List<Obat> getAllObat() {
        return obatRepository.findAll();
    }
     public Obat findById(Integer id) {
        return obatRepository.findById(id).orElse(null);
    }
}
