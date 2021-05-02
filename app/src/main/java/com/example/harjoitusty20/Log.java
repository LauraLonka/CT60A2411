/* Log class: */
/* Class for creating Log-objects that hold weekly emissions calculation results and weight entries */

package com.example.harjoitusty20;

import java.text.DecimalFormat;

public class Log {

    private int weight;
    private double dairyEmissions;
    private double meatEmissions;
    private double plantEmissions;
    private double restaurantEmissions;
    private double totalEmissions;
    private int weekNumber;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public Log() {
        this.weight = 0;
        this.dairyEmissions = 0;
        this.meatEmissions = 0;
        this.plantEmissions = 0;
        this.restaurantEmissions = 0;
        this.totalEmissions = 0;
        this.weekNumber = 0;
    }

    public Log(int weight, double dairy_em, double meat_em, double plant_em, double restaurant_em, double total_em, int week_nmbr) {
        this.weight = weight;
        this.dairyEmissions = dairy_em;
        this.meatEmissions = meat_em;
        this.plantEmissions = plant_em;
        this.restaurantEmissions = restaurant_em;
        this.totalEmissions = total_em;
        this.weekNumber = week_nmbr;
    }

    public String weekNumberToString() {
        return ("Week " + this.weekNumber);
    }

    @Override
    public String toString() {

        return (" Dairy emissions:  " + df2.format(this.getDairyEmissions()) + " kg CO2e" +
                "\n Meat emissions:  " + df2.format(this.getMeatEmissions()) + " kg CO2e" +
                "\n Plant emissions:  " + df2.format(this.getPlantEmissions()) + " kg CO2e" +
                "\n Restaurant emissions:  " + df2.format(this.getRestaurantEmissions()) + " kg CO2e" +
                "\n Total emissions:  " + df2.format(this.getTotalEmissions()) + " kg CO2e" +
                "\n Weight:  " + this.getWeight() + " kg");
    }

    // Getters
    public int getWeight() {
        return weight;
    }

    public double getDairyEmissions() {
        return dairyEmissions;
    }

    public double getMeatEmissions() {
        return meatEmissions;
    }

    public double getPlantEmissions() {
        return plantEmissions;
    }

    public double getRestaurantEmissions() {
        return restaurantEmissions;
    }

    public double getTotalEmissions() {
        return totalEmissions;
    }

    public double getWeekNumber() {
        return weekNumber;
    }

    // Setters
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDairyEmissions(double dairyEmissions) {
        this.dairyEmissions = dairyEmissions;
    }

    public void setMeatEmissions(double meatEmissions) {
        this.meatEmissions = meatEmissions;
    }

    public void setPlantEmissions(double plantEmissions) {
        this.plantEmissions = plantEmissions;
    }

    public void setRestaurantEmissions(double restaurantEmissions) { this.restaurantEmissions = restaurantEmissions; }

    public void setTotalEmissions(double totalEmissions) {
        this.totalEmissions = totalEmissions;
    }
}
