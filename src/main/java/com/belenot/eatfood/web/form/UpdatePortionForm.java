package com.belenot.eatfood.web.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.belenot.eatfood.domain.Portion;

public class UpdatePortionForm {
    private BigDecimal gram;
    private LocalDateTime time;
    private Long clientId;

    public Portion updateDomain(Portion portion) {
        portion.setGram(gram);
        portion.setTime(time);
        return portion;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    public Long getClientId() {
        return clientId;
    }
}