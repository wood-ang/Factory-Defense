package com.wood.FactoryDefense.kotlin.Curve;

import java.util.HashMap;
import java.util.Map;

public class Curve {
     Double  xA, xB, yA, yB,k = 0.0,x = 1.0,y = 1.0;

    int piece;

    Map<Integer, Coordinate> lib = new HashMap<>();

    public Curve(int piece , Double x, Double y, Double xA, Double yA, Double xB, Double yB) {
        this.piece = piece;
        this.x = x;
        this.y = y;
        if (0<=xA || xA <= x) {
            this.xA = xA;
        }else{
            this.xA = x;
        }
        this.yA = yA;
        if (0<=xB || xB <= x) {
            this.xB = x;
        }else{
            this.xB = x;
        }
        this.yB = yB;
        start();
    }
     public Curve(int piece , Double xA, Double yA, Double xB, Double yB){
        this.piece = piece;
        this.xA = xA;
        this.yA = yA;
        this.xB = xB;
        this.yB = yB;
        start();
    }

    Double fx11(){
        return k * xA;
    }
    Double fx12(){
        return k * (xB - xA) + xA;
    }
    Double fx13(){
        return k * (x - xB) + xB;
    }

    Double fx21 (){
        return k * (fx12() - fx11()) + fx11();
    }
    Double fx22(){
        return k * (fx13() - fx12()) + fx12();
    }

    Double fx3(){
        return k * (fx22() - fx21()) + fx21();
    }


    Double fy11(){
        return k * yA;
    }
    Double fy12(){
        return k * (yB - yA) + yA;
    }
    Double fy13(){
        return k * (y - yB) + yB;
    }

    Double fy21 (){
        return k * (fy12() - fy11()) + fy11();
    }
    Double fy22(){
        return k * (fy13() - fy12()) + fy12();
    }

    Double fy3(){
        return k * (fy22() - fy21()) + fy21();
    }

    public void start(){
        k = -(1.0/piece);
        for (int i = 0; i < piece; i++){
            k += 1.0 /piece;
            lib.put(i,new  Coordinate(fx3(),fy3()));
        }
        lib.put(piece,new Coordinate(x,y));
    }

    public void print(){
        for (int i = 0; i <= piece; i++){
            if (1 <= x || x <= 1) {
                System.out.println(i+"\t"+lib.get(i).toPercentString());
            }else {
                System.out.println(i+"\t"+lib.get(i).toString());
            }
        }
    }

    public Coordinate getY(double x) {
        try {
            for (int i = 0; i < piece; i++){
                Coordinate current = lib.get(i);
                Coordinate next = lib.get(i + 1);

                // 检查目标x是否在当前点和下一点之间
                if (current.x <= x && x <= next.x) {
                    // 线性插值计算y值
                    double t = (x - current.x) / (next.x - current.x);
                    return new Coordinate(x,current.y + t * (next.y - current.y)) ;
                }
            }
            return new Coordinate(114514.114514,114514.114514);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    void repeat(int times, char word){
        for (int i = 0; i < times; i++){
            System.out.print(word);
        }
    }

}
