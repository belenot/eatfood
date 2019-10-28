package com.belenot.eatfood.service.support;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.belenot.eatfood.domain.Client;

public class PortionFilter {
    private Interval<LocalDate> date;
    private Interval<BigDecimal> gram;
    private Client client;
    private List<Long> foodIdList;

    
    public static Builder builder() { 
        PortionFilter filter = new PortionFilter();
        return filter.new Builder(); 
    }
    
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

    public List<Long> getFoodIdList() {
        return foodIdList;
    }

    public void setFoodIdList(List<Long> foodIdList) {
        this.foodIdList = foodIdList;
    }
    
    public class Builder {
        private Builder() {};
        public Builder date(LocalDate start, LocalDate end) {
            PortionFilter.this.setDate(new Interval<>(start, end));
            return this;
        }
        public Builder gram(BigDecimal start, BigDecimal end) {
            PortionFilter.this.setGram(new Interval<>(start, end));
            return this;
        }
        public Builder gram(double start, double end) {
            return gram(BigDecimal.valueOf(start), BigDecimal.valueOf(end));
        }
        public Builder client(Client client) {
            PortionFilter.this.setClient(client);
            return this;
        }
        public PortionFilter build() {
            return PortionFilter.this;
        }

    }
}