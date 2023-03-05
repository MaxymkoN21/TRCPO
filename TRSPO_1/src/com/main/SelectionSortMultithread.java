package com.main;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class SelectionSortMultithread {
    public static void main(String[] args) {
        // Generate input array of size 100000 with random values
        int size = 100000;

        Scanner choose = new Scanner(System.in);
        System.out.println("Choose what you want : 1)Random or 2)File Input   ?");
        int integer=choose.nextInt();
        int[] arr ;
        if(integer==1) {
            // Generating a random array of size 100000
            arr = generateRandomArray(size);
            System.out.println("Unsorted random array is creating: ");
        }
        else if(integer==2) {
            // Reading the input array from a file
            String inputFileName = "input.txt";
            arr = readArrayFromFile(inputFileName);
            System.out.println("Unsorted input array is creating:");
        }
        else {
            int[] array = {124, 5, 32, 78, 43, 456, 12, 34, 67, 21};
            arr = array;
        }

        //int[] arr = generateRandomArray(size);
        //System.out.println("Input array: " + Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        // Sort the array using selection sort with multithreading

        int numThreads = 5;
        selectionSortParallel(arr, numThreads);
        System.out.println("Sorting array: " );
        String outputFileName = "output.txt";
        saveArrayToFile(outputFileName, arr);
        System.out.println("Saved sorted array to file :" + outputFileName);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " milliseconds");

    }

    public static void selectionSortParallel(int[] arr, int numThreads) {
        int length = arr.length;
        int chunkSize = length / numThreads; //sizeof thread
        Thread[] threads = new Thread[numThreads]; //thread array
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads-1) ? length : (i+1) * chunkSize;
            threads[i] = new Thread(new SelectionSort(arr, start, end));
            threads[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
        for (int i = 1; i < length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j-1]) {
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
    }

    public static void saveArrayToFile(String outputname, int[] arr) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputname));
            for (int i = 0; i < arr.length; i++) {
                writer.write(arr[i] + " ");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file" + e.getMessage());
        }
    }
    public static int[] readArrayFromFile(String inputname) {
        int[] arr =  null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputname));
            String line = reader.readLine();
            String[] tokens = line.split(" ");
            arr = new int[tokens.length];
            for(int i = 0; i< tokens.length;i++){
                arr[i] = Integer.parseInt(tokens[i]);
            }
            reader.close();
        }
        catch (IOException e){
            System.err.println("Error reading from file" + e.getMessage());
        }
        return arr;
    }
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        return arr;
    }
}

