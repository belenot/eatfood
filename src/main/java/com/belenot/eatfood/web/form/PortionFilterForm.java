package com.belenot.eatfood.web.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.belenot.eatfood.service.support.Interval;
import com.belenot.eatfood.service.support.PortionFilter;

import org.springframework.format.annotation.DateTimeFormat;

public class PortionFilterForm {
    private BigDecimal gteGram;
    private BigDecimal lteGram;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate afterDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beforeDate;

    public BigDecimal getGteGram() {
        return gteGram;
    }

    public void setGteGram(BigDecimal gteGram) {
        this.gteGram = gteGram;
    }

    public BigDecimal getLteGram() {
        return lteGram;
    }

    public void setLteGram(BigDecimal lteGram) {
        this.lteGram = lteGram;
    }

    public LocalDate getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(LocalDate afterDate) {
        this.afterDate = afterDate;
    }

    public LocalDate getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(LocalDate beforeDate) {
        this.beforeDate = beforeDate;
    }

    public PortionFilter createFilter() {
        PortionFilter filter = new PortionFilter();
        filter.setDate(new Interval<>(afterDate, beforeDate));
        filter.setGram(new Interval<>(gteGram, lteGram));
        return filter;
    }

    
}