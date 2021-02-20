package com.redbubble;

import com.redbubble.entity.Product;
import org.json.JSONArray;
import org.junit.*;

import java.util.*;

public class CartPriceCalculatorTest {
    static Map<String, List<Product>> mapProducts;
    static JSONArray arrayCartItems ;
    static JSONArray arrayDiffCartItems ;


    @BeforeClass
    public static void setUp()  {
        mapProducts = new HashMap<>();
        Map<String, List<Object>> mapOptions;
        List<Product> listProductOptions = new ArrayList<>();
        Product product;
        String[] arraySize = {"medium", "small", "xs"};
        String[] arrayColors = {"black", "white"};
        int[] basePrices = {3800, 3600, 3500};
        for (int i = 0; i < 3; i++) {
            product = new Product();
            mapOptions = new HashMap<>();
            mapOptions.put("size", Arrays.asList(arraySize[i]));
            mapOptions.put("colour", Arrays.asList(arrayColors));
            product.setBasePrice(basePrices[i]);
            product.setOptions(mapOptions);
            listProductOptions.add(product);

        }
        mapProducts.put("hoodie", listProductOptions);
        listProductOptions = new ArrayList<>();
        product = new Product();
        mapOptions = new HashMap<>();
        product.setBasePrice(1200);
        product.setOptions(mapOptions);
        listProductOptions.add(product);
        mapProducts.put("sticker", listProductOptions);

        String items = "[{ \"product-type\": \"hoodie\","+
                "\"options\": { "+
            "\"size\": \"small\","+
                   " \"colour\": \"white\","+
                   " \"print-location\": \"front\" },"+
       " \"artist-markup\": 20,"+
              "  \"quantity\": 1 }," +

        "{ \"product-type\": \"hoodie\","+
                "\"options\": { "+
                "\"size\": \"medium\","+
                " \"colour\": \"white\","+
                " \"print-location\": \"front\" },"+
                " \"artist-markup\": 30,"+
                "  \"quantity\": 2 }";
        arrayCartItems = new JSONArray(items+"]");

        String sb = items + ",{ \"product-type\": \"sticker\"," +
                "\"options\": { " +
                " }," +
                " \"artist-markup\": 20," +
                "  \"quantity\": 2 }]";
        arrayDiffCartItems  = new JSONArray(sb);


    }

    @Test
    public void testSumForOneTypeOfProduct() {

    PriceCalculator priceCalculator = new CartPriceCalculator(mapProducts);
    long cartPrice = priceCalculator.calculateTotalPrice(arrayCartItems);
        Assert.assertEquals(14200,cartPrice);
    }
    @Test
    public void testSumForDiffProducts() {

        PriceCalculator priceCalculator = new CartPriceCalculator(mapProducts);
        long cartPrice = priceCalculator.calculateTotalPrice(arrayDiffCartItems);
        Assert.assertEquals(17080,cartPrice);
    }
    @Test
    public void testSumForNoProducts() {

        PriceCalculator priceCalculator = new CartPriceCalculator(mapProducts);
        long cartPrice = priceCalculator.calculateTotalPrice(new JSONArray("[]"));
        Assert.assertEquals(0,cartPrice);
    }
}