package com.help;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class SelectionSorts {
    public static void main(String[] args) {
        // Reading the input array from a file
        String inputFilename = "input.txt";
        int[] arr = readArrayFromFile(inputFilename);
        System.out.println("Input array: " + Arrays.toString(arr));

        // Sorting the array using selection sort
        selectionSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));

        // Outputting the sorted array to the screen
        System.out.println("Sorted array:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Saving the sorted array to a file
        String outputFilename = "output.txt";
        saveArrayToFile(outputFilename, arr);
        System.out.println("Saved sorted array to file: " + outputFilename);
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static int[] readArrayFromFile(String filename) {
        int[] arr = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            String[] tokens = line.split(" ");
            arr = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return arr;
    }

    public static void saveArrayToFile(String filename, int[] arr) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < arr.length; i++) {
                writer.write(arr[i] + " ");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

