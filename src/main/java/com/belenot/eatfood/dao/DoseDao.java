package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;

public interface DoseDao {
    Dose newDose(Dose dose) throws Exception;
    Dose getDoseById(int id) throws Exception;
    List<Dose> getDoseByFood(Food food, int offset, int limit, boolean desc) throws Exception;
    List<Dose> getDoseByClient(Client client, int offset, int limit, boolean desc) throws Exception;
    List<Dose> getDoseByClient(Client client, int offset, int limit, boolean desc, Date dateFirst, Date dateLast) throws Exception;
    void updateDose(Dose dose) throws Exception;
    boolean deleteDose(Dose dose) throws Exception;
    Map<String, BigDecimal> totalNutrients(Client client) throws Exception;
}
