package com.example.demo.discount;

public class DiscountResult {
    private final double initialSum;
    private final double discount;
    private final double deliveryCost;
    private final double totalAmount;

    public DiscountResult(double initialSum, double discount, double deliveryCost, double totalAmount) {
        this.initialSum = initialSum;
        this.discount = discount;
        this.deliveryCost = deliveryCost;
        this.totalAmount = totalAmount;
    }

    // Геттеры для получения значений полей

    public double getInitialSum() {
        return initialSum;
    }

    public double getDiscount() {
        return discount;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
