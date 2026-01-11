package com.wood.FactoryDefense;

public class Coordinate {
    public Double x;
    public Double y;

    public Coordinate(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return new String("( "+ String.format("%.4f", x) + "\t, " + String.format("%.4f", y) + " )");
    }

    public String toPercentString() {
        return new String("( "+ String.format("%.2f", x * 100) + "%\t" +  String.format("%.2f", y * 100) + "% )") ;
    }
}
