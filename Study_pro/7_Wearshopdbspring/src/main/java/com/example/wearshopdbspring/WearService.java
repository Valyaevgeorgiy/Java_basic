package com.example.wearshopdbspring;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class WearService {
    @Autowired
    private WearRepository wearRepository;

    public List<Wear> getAllWears() {
        return wearRepository.findAll();
    }

    public void saveWear(Wear wear) {
        wearRepository.save(wear);
    }

    public void deleteWear(long id) {
        wearRepository.deleteById(id);
    }
}