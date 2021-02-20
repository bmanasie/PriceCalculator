package com.redbubble.entity;

import java.util.List;
import java.util.Map;

public class Product {

    private Map<String, List<Object>> options;

    private long basePrice;

    public Map<String, List<Object>> getOptions() {
        return options;
    }

    public void setOptions(Map<String, List<Object>> options) {
        this.options = options;
    }

    public long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(long basePrice) {
        this.basePrice = basePrice;
    }
}
