package com.redbubble;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Cart implements CartInterface {





    public long calculateTotalPrice(JSONArray cartValues) {
        long totalSum = 0;
        for (int i = 0; i < cartValues.length(); i++) {
            totalSum += getItemPrice(cartValues.getJSONObject(i));
        }
        return totalSum;
    }


    private long getItemPrice( JSONObject item) {
        String productType = item.getString(FieldNames.PRODUCT.name());
        int quantity = item.getInt(FieldNames.QUANTITY.name());
        float artistMarkup = item.getInt(FieldNames.ARTIST.name())/100;
        JSONObject options = item.getJSONObject(FieldNames.OPTIONS.name());
        //Get the list of the products under the given product type
        List<Product> products = basePrices.get(productType);
        JSONArray names = options.names();
        for (Product p : products) {
            if (isProductPresent(p,names,item)) {
                return Math.round(p.getBasePrice() + p.getBasePrice() * artistMarkup) * quantity;
            }
        }
        return 0;
    }


    public boolean isProductPresent(Product p,JSONArray names, JSONObject item){
        for (Object name : names) {

            if (p.getOptions().containsKey(name) &&

                    !p.getOptions().get(name).contains(item.getJSONObject("options").get((String) name))) {
                return false;
            }
        }
        return true;
    }



}
