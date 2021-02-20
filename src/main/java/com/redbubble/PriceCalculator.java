package com.redbubble;

import org.json.JSONArray;

public interface PriceCalculator {
    long calculateTotalPrice(JSONArray cartValues);

}
