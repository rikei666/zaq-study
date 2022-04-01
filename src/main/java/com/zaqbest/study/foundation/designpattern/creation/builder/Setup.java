package com.zaqbest.study.foundation.designpattern.creation.builder;

public class Setup {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.setBuilder(new F1Builder());
        worker.constructNewCar();
        Car car = worker.getCar();
        System.out.println(car);
    }
}
