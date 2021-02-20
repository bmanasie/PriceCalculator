package com.redbubble;

import org.json.JSONArray;

import java.io.BufferedReader;

/**
 *
 */
public class JSONReaderProduct implements ProductFileReader {

    @Override
    public JSONArray readFile(String fileName) {

        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonData.append(line).append("\n");
            }
            return new JSONArray(jsonData.toString());
        } catch (Exception e) {
            return null;
        }
    }
}
