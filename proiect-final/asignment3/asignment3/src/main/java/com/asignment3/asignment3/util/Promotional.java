package com.asignment3.asignment3.util;


public class Promotional {
    private static Promotional singleton = new Promotional();

    private Promotional(){}

    public static Promotional getInstance(){
        return singleton;
    }

    public float pricePromotion(String value){
        float money=0;

        switch (value){
            case "Abc10": money=10;
                        break;
            case "Pres15": money=15;
                        break;
            case "GoldM50": money=50;
                        break;
        }

        return money;
    }





}
