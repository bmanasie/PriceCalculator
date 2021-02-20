package com.redbubble;

import com.redbubble.entity.Product;
import com.redbubble.enums.FieldNames;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class CartPriceCalculator implements PriceCalculator {

    private final Map<String, List<Product>> basePrices;

    CartPriceCalculator(Map<String, List<Product>> basePrices) {

        this.basePrices = basePrices;
    }

    @Override
    public long calculateTotalPrice(JSONArray cartItems) {
        long cartPrice = 0;
        for (int i = 0; i < cartItems.length(); i++) {
            cartPrice += getProductPrice(cartItems.getJSONObject(i));
        }
        return cartPrice;
    }

    private long getProductPrice(JSONObject item) {
        JSONObject options = item.getJSONObject(FieldNames.OPTIONS.toString());
        String productType = item.getString(FieldNames.PRODUCT.toString());
        List<Product> products = basePrices.get(productType);
        for (Product product : products) {
            if (options.length() == 0 || isProductPresentInOptionList(product, item))
                return calculatePrice(product, item);
        }
        return 0;
    }

    private long calculatePrice(Product product, JSONObject item) {
        int quantity = item.getInt(FieldNames.QUANTITY.toString());
        float artistMarkup = (float) item.getInt(FieldNames.ARTIST.toString()) / 100;
        return (long) Math.round(product.getBasePrice() + product.getBasePrice() * artistMarkup) * quantity;
    }

    private boolean isProductPresentInOptionList(Product product, JSONObject item) {
        JSONObject options = item.getJSONObject(FieldNames.OPTIONS.toString());
        Map<String, List<Object>> mapOfOptions = product.getOptions();
        JSONArray optionKeys = options.names();
        for (Object keyString : optionKeys) {
            Object optionInCart = options.get(keyString.toString());
            List<Object> optionsInBasePriceList = mapOfOptions.get(keyString.toString());
            if (optionsInBasePriceList != null && !optionsInBasePriceList.contains(optionInCart)) return false;
        }
        return true;
    }
}
