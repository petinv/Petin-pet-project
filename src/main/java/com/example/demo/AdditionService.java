package com.example.demo;

import org.springframework.stereotype.Service;


@Service
public class AdditionService {
    public int add(int num1, int num2) {
        return num1 + num2;
    }
}
