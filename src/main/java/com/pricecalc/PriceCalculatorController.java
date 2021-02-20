package com.redbubble;

import com.redbubble.entity.Product;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceCalculatorController implements Controller {
    private final ProductFileReader productFileReader;
    private final UserInterface ui;
    private Map<String, List<Product>> basePrices;
    FileProcessor baseFileProcessor;
    private static final String ERROR_MESSAGE = "Wrong path or format of the file";

    public PriceCalculatorController() {
        productFileReader = new JSONReaderProduct();
        ui = new UserInterface();
        basePrices = new HashMap<>();
        baseFileProcessor = new BasePriceFileProcessor();
    }

    @Override
    public void startUpApplication() {
        processBasePrices();
        while (basePrices.size() == 0) {
            if (isInitialExit()) return;
        }
        ui.displaySuccessOutput("Base Price file processed");
        while (true) {
            if (isExit()) break;
        }
    }

    private boolean isInitialExit() {
        int option = ui.displayInitialOptions();
        if (option == 1)
            processBasePrices();
        else if (option == 2) return true;
        return false;
    }

    private boolean isExit() {
        int option = ui.displayOptions();
        if (option == 1 && basePrices.size() > 0)
            ui.displayOutput(getCartPrice());
        else if (option == 2)
            processBasePrices();
        else if (option == 3) return true;
        else ui.displayErrorOutput("Invalid input");
        return false;
    }


    private void processBasePrices() {
        String fileName = ui.getBasePriceFile();
        JSONArray jsonArray = productFileReader.readFile(fileName);
        if (jsonArray == null)
            ui.displayErrorOutput(ERROR_MESSAGE);
        else if (jsonArray.length() > 0)
            basePrices = baseFileProcessor.populateBasePrices(jsonArray);

    }

    private String getCartPrice() {
        PriceCalculator cart = new CartPriceCalculator(basePrices);
        String fileName = ui.getCartFile();
        JSONArray jsonArray = productFileReader.readFile(fileName);
        if (jsonArray == null)
            return ERROR_MESSAGE;
        return "Total cart value is " + cart.calculateTotalPrice(jsonArray);
    }

}
