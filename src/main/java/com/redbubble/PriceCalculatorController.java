package com.redbubble;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements InterfaceController {
    private final FileReader fileReader;
    private final UserInterface ui;
    private Map<String, List<Product>> basePrices;
    PriceProcessor basePriceProcessor;

    public Controller(){
        fileReader = new JSONReader();
        ui = new UserInterface();
        basePrices =  new HashMap<>();
        basePriceProcessor = new BasePriceFileProcessor();
    }

    @Override
    public void startUpApplication(){

        processBasePrices();
        while (basePrices.size()==0){
            int option = ui.displayInitialOptions();
            if(option==1)
                processBasePrices();
            else return;
        }

        ui.displayOutput("Base Price file processed");

        while(true) {
            int option = ui.displayOptions();
            if (option == 1 && basePrices.size()>0)
                ui.displayOutput(getCartPrice());
            else if(option == 2 )
                processBasePrices();
            else break;
        }

    }


    private void processBasePrices(){
        String fileName = ui.getBasePriceFile();
        JSONArray jsonArray = fileReader.readFile(fileName);
        if (jsonArray == null)
            ui.displayOutput("Wrong path or the format of the file");
        else if (jsonArray.length() >0)
            basePrices = basePriceProcessor.populateBasePrices(jsonArray);

    }


    private String getCartPrice() {
        PriceCalculator cart = new CartPriceCalculator(basePrices);
        String fileName = ui.getCartFile();
        JSONArray jsonArray = fileReader.readFile(fileName);
        if (jsonArray == null)
            return "Wrong path or the format of the file";
        return String.valueOf(cart.calculateTotalPrice(jsonArray));
    }

}
