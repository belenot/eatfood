package com.belenot.eatfood.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Portion;
import com.belenot.eatfood.service.PortionService;
import com.belenot.eatfood.user.ClientDetails;
import com.belenot.eatfood.web.form.CreatePortionForm;
import com.belenot.eatfood.web.form.UpdatePortionForm;
import com.belenot.eatfood.web.model.PortionModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portion")
public class PortionController {
    @Autowired
    private PortionService portionService;

    @PostMapping("/create")
    public PortionModel createPortion(@RequestBody CreatePortionForm form) {
        Portion portion = form.createDomain();
        portion = portionService.createPortion(portion, form.getFoodId());
        return PortionModel.of(portion);
    }

    @PostMapping("/{portionId}/delete")
    public boolean deletePortion(@PathVariable("portionId") Long portionId) {
        Portion portion = portionService.byId(portionId);
        portionService.deletePortion(portion);
        return true;
    }

    @PostMapping("/{portionId}/update")
    public PortionModel updatePortion(@PathVariable("portionId") Long portionId, @RequestBody UpdatePortionForm form) {
        Portion portion = portionService.byId(portionId);
        portion = form.updateDomain(portion);
        portion = portionService.updatePortion(portion, form.getClientId());
        return PortionModel.of(portion);
    }

    @GetMapping("/get/food/{foodId}")
    public List<PortionModel> getPortionsByFood(@PathVariable("foodId") Long foodId) {
        return portionService.byFoodId(foodId).stream().map(PortionModel::of).collect(Collectors.toList());
    }

    @GetMapping("/get")
    public List<PortionModel> getPortions() {
        Client client = ((ClientDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClient();
        return portionService.byClient(client).stream().map(PortionModel::of).collect(Collectors.toList());
    }
}