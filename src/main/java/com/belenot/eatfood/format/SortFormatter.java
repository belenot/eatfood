package com.belenot.eatfood.format;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.format.Formatter;

public class SortFormatter implements Formatter<Sort> {

    @Override
    public String print(Sort sort, Locale locale) {
        String message = sort.get().map(s->s.isAscending()?s.getProperty():"!"+s.getProperty()).reduce("", (ac, s)->ac+","+s);
        message = message.length() > 1 ? message.substring(0, message.length()-1) : message;
        return message;
    }

    @Override
    public Sort parse(String message, Locale locale) throws ParseException {
        return Sort.by(Arrays.stream(message.split(",")).map(prop->prop.charAt(0)!='!'?Order.asc(prop):Order.desc(prop.substring(1))).collect(Collectors.toList()));
    }

    
    
}