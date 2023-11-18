package com.example.demo.discount;

import org.springframework.stereotype.Component;

@Component
public class DiscountCalculator {
    public DiscountResult calculateDiscount(double sum) {
        double discount = 0;
        double deliveryCost = 0;

        if (sum < 100.0) {
            deliveryCost = 50.0;
        } else if (sum >= 100.0 && sum < 200.0) {
            discount = sum * 0.05;
            deliveryCost = 50.0;
        } else if (sum >= 200.0 && sum < 500.0) {
            discount = sum * 0.1;
            deliveryCost = 100.0;
        } else if (sum >= 500.0) {
            discount = sum * 0.2;
            deliveryCost = 0.0;
        }

        double totalAmount = sum - discount + deliveryCost;

        double discountPercentage = (discount / sum) * 100;

        return new DiscountResult(sum, discountPercentage, deliveryCost, totalAmount);
    }
}