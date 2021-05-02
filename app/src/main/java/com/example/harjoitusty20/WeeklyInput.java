/* Weekly Input class: */
/* Class for the users input data of the amount of weekly consumed food and weekly weight*/

package com.example.harjoitusty20;

import java.util.ArrayList;

public class WeeklyInput {

    private String diet;
    private Boolean lowCarbonPreference;
    private int beefLevel;
    private int fishLevel;
    private int porkPoultryLevel;
    private int dairyLevel;
    private int cheeseLevel;
    private int riceLevel;
    private int eggLevel;
    private int winterSaladLevel;
    private int restaurantSpending;
    private int weight;

    public WeeklyInput() {
        this.diet = null;
        this.lowCarbonPreference = null;
        this.beefLevel = 0;
        this.fishLevel = 0;
        this.porkPoultryLevel = 0;
        this.dairyLevel = 0;
        this.cheeseLevel = 0;
        this.riceLevel = 0;
        this.eggLevel = 0;
        this.winterSaladLevel = 0;
        this.restaurantSpending = 0;
        this.weight = 0;
    }

    public WeeklyInput(String diet, Boolean lowcarb, int beef, int fish,
                       int pork, int dairy, int cheese, int rice, int egg, int salad, int restaurant, int weight) {
        this.diet = diet;
        this.lowCarbonPreference = lowcarb;
        this.beefLevel = beef;
        this.fishLevel = fish;
        this.porkPoultryLevel = pork;
        this.dairyLevel = dairy;
        this.cheeseLevel = cheese;
        this.riceLevel = rice;
        this.eggLevel = egg;
        this.winterSaladLevel = salad;
        this.restaurantSpending = restaurant;
        this.weight = weight;
    }

    public static void main(String[] args, WeeklyInput new_week) {

        // Creating an ArrayList of Object type
        ArrayList<WeeklyInput> array_list = new ArrayList<WeeklyInput>();

        // Inserting String value in arr
        array_list.add(new_week);
    }

    // Getters
    public String getDiet() {
        return diet;
    }

    public Boolean getLowCarbonPreference() {
        return lowCarbonPreference;
    }

    public int getBeefLevel() {
        return beefLevel;
    }

    public int getFishLevel() {
        return fishLevel;
    }

    public int getPorkPoultryLevel() {
        return porkPoultryLevel;
    }

    public int getDairyLevel() {
        return dairyLevel;
    }

    public int getCheeseLevel() {
        return cheeseLevel;
    }

    public int getRiceLevel() {
        return riceLevel;
    }

    public int getEggLevel() {
        return eggLevel;
    }

    public int getWinterSaladLevel() {
        return winterSaladLevel;
    }

    public int getRestaurantSpending() {
        return restaurantSpending;
    }

    public int getWeight() {
        return weight;
    }

    // Setters
    public void setDiet(String diet) {
        this.diet = diet;
    }

    public void setLowCarbonPreference(Boolean lowCarbonPreference) {
        this.lowCarbonPreference = lowCarbonPreference;
    }

    public void setBeefLevel(int beefLevel) {
        this.beefLevel = beefLevel;
    }

    public void setFishLevel(int fishLevel) {
        this.fishLevel = fishLevel;
    }

    public void setPorkPoultryLevel(int porkPoultryLevel) {
        this.porkPoultryLevel = porkPoultryLevel;
    }

    public void setDairyLevel(int dairyLevel) {
        this.dairyLevel = dairyLevel;
    }

    public void setCheeseLevel(int cheeseLevel) {
        this.cheeseLevel = cheeseLevel;
    }

    public void setRiceLevel(int riceLevel) {
        this.riceLevel = riceLevel;
    }

    public void setEggLevel(int eggLevel) {
        this.eggLevel = eggLevel;
    }

    public void setWinterSaladLevel(int winterSaladLevel) {
        this.winterSaladLevel = winterSaladLevel;
    }

    public void setRestaurantSpending(int restaurantSpending) {
        this.restaurantSpending = restaurantSpending;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
