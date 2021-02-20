package com.redbubble;

import com.redbubble.entity.Product;
import com.redbubble.enums.FieldNames;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BasePriceFileProcessor implements FileProcessor {

    HashMap<String, List<Product>> basePrices;

    @Override
    public Map<String, List<Product>> populateBasePrices(JSONArray arrayPrices) {
        basePrices = new HashMap<>();
        for (int i = 0; i < arrayPrices.length(); i++) {
            processItemPrice(arrayPrices.getJSONObject(i));
        }
        return basePrices;
    }

    private void processItemPrice(JSONObject item) {
        String productType = item.getString(FieldNames.PRODUCT.toString());
        long basePrice = item.getLong(FieldNames.BASEPRICE.toString());
        JSONObject optionsObject = item.getJSONObject(FieldNames.OPTIONS.toString());
        Product product = new Product();
        product.setBasePrice(basePrice);
        product.setOptions(getOptionsList(optionsObject));
        addToBasePriceList(productType, product);
    }

    private HashMap<String, List<Object>> getOptionsList(JSONObject options) {
        HashMap<String, List<Object>> mapOptions = new HashMap<>();
        JSONArray optionKeys = options.names();
        int numOfKeys = optionKeys != null ? optionKeys.length() : 0;
        for (int y = 0; y < numOfKeys; y++) {
            String keyName = optionKeys.getString(y);
            List<Object> keyValueList = options.getJSONArray(keyName).toList();
            mapOptions.put(keyName, keyValueList);
        }
        return mapOptions;
    }

    private void addToBasePriceList(String productType, Product product) {
        List<Product> listProducts = new ArrayList<>();
        if (basePrices.containsKey(productType))
            listProducts = basePrices.get(productType);
        listProducts.add(product);
        basePrices.put(productType, listProducts);
    }

}
