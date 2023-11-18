package com.example.demo;

import com.example.demo.discount.DiscountCalculator;
import com.example.demo.discount.DiscountResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class MainController {

    @Autowired
    private DiscountCalculator discountCalculator;

    private int[] generatedArray; // Переменная для хранения массива

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/addition")
    public String additionPage() {
        return "addition";
    }

    @GetMapping("/add")
    public String addNumbers(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model) {
        int sum = num1 + num2;
        model.addAttribute("result", sum);
        return "addition";
    }

    @GetMapping("/arrays")
    public String arraysPage() {
        return "arrays";
    }

    @GetMapping("/generateArray")
    public String generateArray(@RequestParam(name = "arrayLength", required = false) Integer arrayLength, Model model) {
        if (arrayLength == null || arrayLength <= 0) {
            model.addAttribute("result", "Ошибка: Укажите корректную длину массива.");
        } else {
            generatedArray = ArraysService.arr(arrayLength);
            model.addAttribute("result", "Сгенерированный массив: " + Arrays.toString(generatedArray));
        }
        return "arrays";
    }

    @GetMapping("/generateRandomArray")
    public String generateRandomArray(Model model) {
        generatedArray = ArraysService.arrRandom();
        model.addAttribute("result", "Сгенерированный случайный массив: " + Arrays.toString(generatedArray));
        return "arrays";
    }

    @GetMapping("/sortArray")
    public String sortArray(Model model) {
        if (generatedArray == null || generatedArray.length == 0) {
            model.addAttribute("result", "Ошибка: Массив не сгенерирован. Пожалуйста, сначала сгенерируйте массив.");
        } else {
            int[] sortedArray = ArraysService.sortArray(generatedArray);
            model.addAttribute("result", "Отсортированный массив: " + Arrays.toString(sortedArray));
        }
        return "arrays";
    }

    @GetMapping("/reversSortedArray")
    public String reversSortedArray(Model model) {
        if (generatedArray == null || generatedArray.length == 0) {
            model.addAttribute("result", "Ошибка: Массив не сгенерирован. Пожалуйста, сначала сгенерируйте массив.");
        } else {
            int[] sortedArray = ArraysService.reversSortedArray(generatedArray);
            model.addAttribute("result", "Отсортированный массив: " + Arrays.toString(sortedArray));
        }
        return "arrays";
    }

    @GetMapping("/minAndMaxArr")
    public String minAndMaxArr(Model model) {
        if (generatedArray == null || generatedArray.length == 0) {
            model.addAttribute("result", "Ошибка: Массив не сгенерирован. Пожалуйста, сначала сгенерируйте массив.");
        } else {
            String sortedArray = ArraysService.minAndMaxArr(generatedArray);
            model.addAttribute("result", sortedArray);
        }
        return "arrays";
    }


    // Просчет стоимости

    @GetMapping("/calculate")
    public String calculateDiscount(@RequestParam double sum, Model model) {
        DiscountResult discountResult = discountCalculator.calculateDiscount(sum);
        model.addAttribute("result", discountResult);
        return "discount_calculator";
    }

    @GetMapping("/discount_calculator")
    public String discountCalculatorPage() {
        return "discount_calculator";
    }

    @GetMapping("/sent-email")
    public String sentEmail() {
        return "sent-email";
    }
}

