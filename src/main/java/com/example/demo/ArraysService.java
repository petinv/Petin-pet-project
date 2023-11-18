package com.example.demo;
import java.util.Random;

public class ArraysService {

    static int[] arr;
    static int[] sortedArray;

    static int[] arr(int length) { // создание массива
        arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random();
        }
        return arr;
    }

    static int[] arrRandom() { // создание массива
        arr = new int[random()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random();
        }
        return arr;
    }

    static int random() { // рандом
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    static int[] sortArray(int[] arr) { // сортировка по возрастанию
        sortedArray = new int[arr.length]; // Создаем новый массив той же длины
        // Копируем каждый элемент из исходного массива в новый массив
        for (int i = 0; i < arr.length; i++) {
            sortedArray[i] = arr[i];
        }
        java.util.Arrays.sort(sortedArray); // Сортируем новый массив
        return sortedArray; // Возвращаем отсортированный массив
    }

    static int [] reversSortedArray(int[] arr) {
        sortedArray = sortArray(arr);
        int length = sortedArray.length;
        for (int i = 0; i < length / 2; i++) {
            int temp = sortedArray[i];
            sortedArray[i] = sortedArray[length - 1 - i];
            sortedArray[length - 1 - i] = temp;
        }
        return sortedArray;
    }

    static String minAndMaxArr (int [] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min){
                min = arr[i];
            }
            if (arr[i] > max)
                max = arr[i];
        }

        return "\nМинимальное: " + min + "\nМаксимальное: " + max + "\n";
    }

}
