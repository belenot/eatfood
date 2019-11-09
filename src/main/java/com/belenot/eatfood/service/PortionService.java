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

    public Portion updatePortion(Long id, Portion portion) {
        Portion old = portionRepository.findById(id).get();
        old.setDate(portion.getDate());
        old.setFood(portion.getFood());
        old.setGram(portion.getGram());
        portion = portionRepository.save(portion);
        return portion;
    }

    public void deletePortion(Long id) {
        portionRepository.deleteById(id);
    }

    public List<Portion> getAllPortions() {
        return portionRepository.findAll();
    }

    public Portion getById(Long id)  {
        return portionRepository.findById(id).get();
    }
}