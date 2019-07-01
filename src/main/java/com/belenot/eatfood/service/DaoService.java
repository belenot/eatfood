package com.belenot.eatfood.service;

import com.belenot.eatfood.dao.AccountDao;
import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.FoodCriteria;
import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.dao.FoodListDao;

public interface DaoService<T extends FoodCriteria<?>> extends ClientDao, FoodDao<T>, FoodListDao, AccountDao {
    
    
}
