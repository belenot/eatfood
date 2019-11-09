package com.belenot.eatfood.service;

import java.util.List;

import com.belenot.eatfood.domain.Portion;
import com.belenot.eatfood.repository.PortionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortionService {
    @Autowired
    private PortionRepository portionRepository;

    public Portion createPortion(Portion portion) {
        portion = portionRepository.save(portion);
        return portion;
    }

    public Portion updatePortion(Portion portion) {
        portion = portionRepository.save(portion);
        return portion;
    }

    public void deletePortion(Portion portion) {
        portionRepository.delete(portion);
    }

    public List<Portion> getAllPortions() {
        return portionRepository.findAll();
    }
}