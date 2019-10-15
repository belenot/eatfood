package com.belenot.eatfood.service.support;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.belenot.eatfood.domain.Client;

public class PortionFilter {
    private Interval<LocalDate> date;
    private Interval<BigDecimal> gram;
    private Client client;

    public Interval<LocalDate> getDate() {
        return date;
    }

    public void setDate(Interval<LocalDate> date) {
        this.date = date;
    }

    public Interval<BigDecimal> getGram() {
        return gram;
    }

    public void setGram(Interval<BigDecimal> gram) {
        this.gram = gram;
    }

    public boolean isDatePresented() {
        return date != null && (isDateStartPresented() || isDateEndPresented());
    }

    public boolean isGramPresented() {
        return gram != null && (isGramStartPresented() || isGramEndPresented());
    }

    public boolean isDateStartPresented() {
        return date != null && date.getStart() != null;
    }

    public boolean isDateEndPresented() {
        return date != null && date.getEnd() != null;
    }

    public boolean isGramStartPresented() {
        return gram != null && gram.getStart() != null;
    }

    public boolean isGramEndPresented() {
        return gram != null && gram.getEnd() != null;
    }

    public boolean isAllPresented() {
        return isDateStartPresented() && isDateEndPresented() && isGramStartPresented() && isGramEndPresented();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}