package com.belenot.eatfood.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.User;
import com.belenot.eatfood.service.FoodService;
import com.belenot.eatfood.web.form.AddFoodForm;
import com.belenot.eatfood.web.form.UpdateFoodForm;
import com.belenot.eatfood.web.model.FoodModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/add")
    public FoodModel addFood(@RequestBody AddFoodForm form, @AuthenticationPrincipal User user) {
        Food food = new Food();
        food.setName(form.getName());
        food.setNutrients(form.getNutrients());
        Food parent = foodService.getById(form.getParentId());
        food.setParent(parent);
        food.setAuthor(user);
        food = foodService.createFood(food);
        return new FoodModel(food);
    }

    @PostMapping("/{id}/update")
    public FoodModel updateFood(@PathVariable("id") Long id, @RequestBody UpdateFoodForm form) {
        Food updatedFood = new Food();
        updatedFood.setName(form.getName());
        updatedFood.setNutrients(form.getNutrients());
        Food parent = foodService.getById(form.getParentId());
        updatedFood.setParent(parent);
        updatedFood = foodService.updateFood(id, updatedFood);
        return new FoodModel(updatedFood);
    }

    @PostMapping("/{id}/delete")
    public boolean deleteFood(@PathVariable("id") Long id) {
        foodService.deleteFood(id);
        return true;
    }

    @GetMapping
    public List<FoodModel> getAllFoods() {
        return foodService.getAllFoods().stream().map(FoodModel::new).collect(Collectors.toList());
    }
}