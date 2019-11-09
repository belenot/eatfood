package com.belenot.eatfood.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Portion;
import com.belenot.eatfood.service.FoodService;
import com.belenot.eatfood.service.PortionService;
import com.belenot.eatfood.web.form.AddPortionForm;
import com.belenot.eatfood.web.form.UpdatePortionForm;
import com.belenot.eatfood.web.model.PortionModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portion")
public class PortionController {
    @Autowired
    private PortionService portionService;
    @Autowired
    private FoodService foodService;

    @PostMapping("/add")
    public PortionModel addPortion(@RequestBody AddPortionForm form) {
        Portion portion = new Portion();
        Food food = foodService.getById(form.getFoodId());
        if (food == null) {
            throw new IllegalArgumentException("Should specify food");
        }
        portion.setFood(food);
        portion.setGram(form.getGram());
        portion.setDate(form.getDate());
        portion = portionService.createPortion(portion);
        return new PortionModel(portion);
    }

    @PostMapping("/{id}/update")
    public PortionModel updatePortion(@PathVariable("id") Long id, @RequestBody UpdatePortionForm form) {
        Portion updatedPortion = new Portion();
        Food food = foodService.getById(form.getFoodId());
        updatedPortion.setDate(form.getDate());
        updatedPortion.setGram(form.getGram());
        updatedPortion.setFood(food);
        updatedPortion = portionService.updatePortion(id, updatedPortion);
        return new PortionModel(updatedPortion);
    }

    @PostMapping("/{id}/delete")
    public boolean deletePortion(@PathVariable("id")  Long id) {
        portionService.deletePortion(id);
        return true;
    }

    @GetMapping
    public List<PortionModel> getAllPortions() {
        return portionService.getAllPortions().stream().map(PortionModel::new).collect(Collectors.toList());
    }
    
}