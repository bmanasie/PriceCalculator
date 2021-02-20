package com.redbubble;

import org.json.JSONArray;

public interface ProductFileReader {
    JSONArray readFile(String fileName);
}
