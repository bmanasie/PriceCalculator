package com.redbubble;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class JSONReaderTest{

    static JSONArray arrayItems;

    @BeforeClass
    public static void setUp()  {
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
        arrayItems = new JSONArray(items);
    }

    @Test
    public void testJSONArraySize() {
        ProductFileReader reader = new JSONReaderProduct();
        Assert.assertEquals(3,reader.readFile("src/main/resources/test.json").length());

    }


    @Test
    public void testFileNotPresent() {
        ProductFileReader reader = new JSONReaderProduct();
        Assert.assertNull(reader.readFile("src/main/resources/demo.json"));

    }
}