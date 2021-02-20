package com.redbubble;

import com.redbubble.entity.Product;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class BasePriceFileProcessorTest {
    static JSONArray arrayBasePrices ;
    static Map<String, List<Product>> mapProducts;

    @BeforeClass
    public static void setUp() {
        mapProducts = new HashMap<>();
        Map<String, List<Object>> mapOptions;
        List<Product> listProductOptions = new ArrayList<>();
        Product product;
        String[][] arraySize = {{"large","xl"},{"medium", "small"}};
        String[] arrayColors = {"black", "white"};
        int[] basePrices = {4000, 3800};
        for (int i = 0; i < 2; i++) {
            product = new Product();
            mapOptions = new HashMap<>();
            mapOptions.put("size", Arrays.asList(arraySize[i]));
            mapOptions.put("colour", Arrays.asList(arrayColors[i]));
            product.setBasePrice(basePrices[i]);
            product.setOptions(mapOptions);
            listProductOptions.add(product);

        }
        mapProducts.put("hoodie", listProductOptions);

        listProductOptions = new ArrayList<>();
        product = new Product();
        mapOptions = new HashMap<>();
        product.setBasePrice(1220);
        product.setOptions(mapOptions);
        listProductOptions.add(product);
        mapProducts.put("leggings", listProductOptions);


        String items =   "[{ \"product-type\": \"hoodie\","+
                "\"options\": { "+
                "\"size\": [\"large\",\"xl\"],"+
                " \"colour\": [\"black\"]},"+
                " \"base-price\": 4000},"+
                "{ \"product-type\": \"hoodie\","+
                "\"options\": { "+
                "\"size\": [\"medium\",\"small\"],"+
                " \"colour\": [\"white\"]},"+
                " \"base-price\": 3800},"+
                "{ \"product-type\": \"leggings\"," +
                "\"options\": { " +
                " }," +
                " \"base-price\": 1220 }]";
        arrayBasePrices = new JSONArray(items);


    }

    @Test
    public void testCompareHashKeys() {
        FileProcessor basePrices = new BasePriceFileProcessor();
        Map<String, List<Product>> mapPrices = basePrices.populateBasePrices(arrayBasePrices);
        assertEquals(mapProducts.keySet(), mapPrices.keySet());

    }

    @Test
    public void testCompareBasePrices() {
        FileProcessor basePrices = new BasePriceFileProcessor();
        Map<String, List<Product>> mapPrices = basePrices.populateBasePrices(arrayBasePrices);
        Assert.assertEquals(mapProducts.get("leggings").get(0).getBasePrice(),
                mapPrices.get("leggings").get(0).getBasePrice());

    }
    @Test
    public void testCompareOptionsSize() {
        FileProcessor basePrices = new BasePriceFileProcessor();
        Map<String, List<Product>> mapPrices = basePrices.populateBasePrices(arrayBasePrices);
        Assert.assertEquals(0,mapPrices.get("leggings").get(0).getOptions().size() );

    }
    @Test
    public void testCompareOptions() {
        FileProcessor basePrices = new BasePriceFileProcessor();
        Map<String, List<Product>> mapPrices = basePrices.populateBasePrices(arrayBasePrices);
        Assert.assertEquals(mapProducts.get("hoodie").get(0).getOptions(),mapPrices.get("hoodie").get(0).getOptions());

    }
}