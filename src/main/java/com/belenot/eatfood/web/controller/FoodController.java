package com.belenot.eatfood.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.FoodService;
import com.belenot.eatfood.user.ClientDetails;
import com.belenot.eatfood.web.form.CreateFoodForm;
import com.belenot.eatfood.web.form.UpdateFoodForm;
import com.belenot.eatfood.web.model.FoodModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired 
    private FoodService foodService;

    @PostMapping("/create")
    public FoodModel createFood(@RequestBody CreateFoodForm form) {
        Food food = form.createDomain();
        Client client = ((ClientDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClient();
        food = foodService.createFood(client, food);
        return FoodModel.of(food);
    }

    @PostMapping("/{id}/delete")
    public boolean deleteFood(@PathVariable("id") Long foodId) {
        Food food = foodService.byId(foodId);
        foodService.deleteFood(food);
        return true;
    }

    @PostMapping("/{id}/update")
    public FoodModel updateFood(@PathVariable("id") Long foodId, @RequestBody UpdateFoodForm form) {
        Food food = foodService.byId(foodId);
        food = form.updateDomain(food);
        food = foodService.updateFood(food);
        return FoodModel.of(food);
    }

    @GetMapping("/get")
    public List<FoodModel> getFoods() {
        Client client = ((ClientDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClient();
        return foodService.byClient(client).stream().map(f->FoodModel.of(f)).collect(Collectors.toList());
    }

}