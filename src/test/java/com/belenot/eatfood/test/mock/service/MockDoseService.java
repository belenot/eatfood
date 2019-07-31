package com.belenot.eatfood.test.mock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.DoseService;

//Works for single client
public class MockDoseService extends DoseService {
    private Map<Dose, Food> doses = new HashMap<>();
    private int id = 1;

    @Override
    public void addDose(Food food, Dose dose) {
	dose.setId(id++);
	doses.put(dose, food);
    }

    @Override
    public Dose getDoseById(int id) {
	return doses.keySet().stream().filter( d -> d.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Dose> getDoseByFood(Food food) {
	List<Dose> result = new ArrayList<>();
	for (Dose dose : doses.keySet()) {
	    if (doses.get(dose).getId() == food.getId()) {
		result.add(dose);
	    }
	}
	return result;
    }

    //Attention! Only single client - result whole collection!
    @Override
    public List<Dose> getDoseByClient(Client client) {
	List<Dose> result = new ArrayList<>(doses.keySet());
	return result;
    }

    //Only for field GRAM
    @Override
    public void updateDose(Dose dose) {
	Dose updatedDose = doses.keySet().stream().filter( d -> d.getId() == dose.getId()).findFirst().orElse(null);
	updatedDose.setGram(dose.getGram());
    }

    @Override
    public void deleteDose(Dose dose) {
	doses.remove(dose);
    }
}
