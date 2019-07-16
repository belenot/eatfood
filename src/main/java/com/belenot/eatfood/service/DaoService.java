package com.belenot.eatfood.service;

import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.DoseDao;
import com.belenot.eatfood.dao.FoodDao;

public interface DaoService extends ClientDao, FoodDao, DoseDao {
    
}
