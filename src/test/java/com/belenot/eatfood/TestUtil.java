package com.belenot.eatfood;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.ClientData;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Nutrients;

public class TestUtil {


    public static String[] anyStrings = { "sonic", "very", "derby", "malina", "troll", "fix", "misc", "agent", "drum",
					  "fear", "leat", "loong", "fresc", "grel", "lq", "sand", "water", "fire", "earth", "wind",
					  "stone", "winter", "summer"}; 

    public static Client copyClient(Client client) {
	Client copy = new Client();
	copy.setId(-1);
	copy.setLogin(client.getLogin());
	copy.setPassword(client.getPassword());
	copy.setData(copyClientData(client.getData()));
        copy.setFoods(new ArrayList<>());
	Collections.copy(client.getFoods(), copy.getFoods());
	return copy;
    }

    public static ClientData copyClientData(ClientData clientData) {
	ClientData copy = new ClientData();
	copy.setName(clientData.getName());
	copy.setSurname(clientData.getSurname());
	copy.setEmail(clientData.getEmail());
	return copy;
    }

    public static Food copyFood(Food food) {
	Food copy = new Food().setName(food.getName()).setNutrients(copyNutrients(food.getNutrients()));
	return copy;
    }

    public static Nutrients copyNutrients(Nutrients nutrients) {
	Nutrients copy = new Nutrients(nutrients.getCalories().doubleValue(),
				       nutrients.getProtein().doubleValue(),
				       nutrients.getCarbohydrate().doubleValue(),
				       nutrients.getFat().doubleValue());
	return copy;
    }

    public static Client randomClient() {
        Client client = new Client()
	    .setLogin(anyString()).setPassword(anyString())
	    .setData(new ClientData(anyString(), anyString(), anyString()));
	return client;
    }

    public static Food randomFood() {
	Food food = new Food().setName(anyString())
	    .setNutrients(new Nutrients(Math.random() * 1000, Math.random() * 1000, Math.random() * 1000, Math.random() * 1000));
	return food;
    }

    public static Dose randomDose() {
	Dose dose = new Dose().setGram(new BigDecimal(Math.random() * 1000).setScale(2, RoundingMode.CEILING)).setDate(new Date());
	return dose;
    }

    public static String anyString() {
	int index = (int)Math.ceil(Math.random() * (anyStrings.length - 1));
	return anyStrings[index];
    }
	
}
