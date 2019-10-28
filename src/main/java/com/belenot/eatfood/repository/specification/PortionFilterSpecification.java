package com.belenot.eatfood.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Food_;
import com.belenot.eatfood.domain.Portion;
import com.belenot.eatfood.domain.Portion_;
import com.belenot.eatfood.service.support.PortionFilter;
import com.belenot.eatfood.service.support.PortionFilter.Builder;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.data.jpa.domain.Specification;

public class PortionFilterSpecification implements Specification<Portion> {

    private PortionFilter filter;

    public PortionFilterSpecification(PortionFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Portion> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // Join<Portion, Food> portionFood = root.join(Portion_.food);
        // Join<Food, Client> foodClient = portionFood.joi
        Predicate predicate = builder.equal(root.get(Portion_.food).get(Food_.client), filter.getClient());
        if (filter.getDate() != null) {
            if (filter.getDate().getStart() != null)  {
                predicate = builder.and(predicate, builder.greaterThan(root.get(Portion_.date), filter.getDate().getStart()));
            }
            if (filter.getDate().getEnd() != null) {
                predicate = builder.and(predicate, builder.lessThan(root.get(Portion_.date), filter.getDate().getEnd()));
            }
        }
        if (filter.getGram() != null) {
            if (filter.getGram().getStart() != null) {
                predicate = builder.and(predicate, builder.greaterThan(root.get(Portion_.gram), filter.getGram().getStart()));
            }
            if (filter.getGram().getEnd() != null) {
                predicate = builder.and(predicate, builder.lessThan(root.get(Portion_.gram), filter.getGram().getEnd()));
            }
        }
        if (filter.getFoodIdList() != null) {
            Predicate foodIdMatches = null;
            for (Long foodId : filter.getFoodIdList()) {
                if (foodIdMatches==null) {
                    foodIdMatches = builder.equal(root.get(Portion_.food), foodId);
                    continue;
                }
                foodIdMatches = builder.or(foodIdMatches, builder.equal(root.get(Portion_.food), foodId));
            }
            predicate = builder.and(predicate, foodIdMatches);
        }
        
        return predicate;
    }
    
}