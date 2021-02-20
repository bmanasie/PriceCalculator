package com.redbubble;

import com.redbubble.entity.Product;
import org.json.JSONArray;

import java.util.List;
import java.util.Map;

public interface FileProcessor {
    Map<String, List<Product>> populateBasePrices(JSONArray arrayPrices);

}
