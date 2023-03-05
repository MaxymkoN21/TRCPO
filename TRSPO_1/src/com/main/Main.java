package com.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner choose = new Scanner(System.in);
        System.out.println("Choose what you want : 1)Random or 2)File Input   ?");
        int integer=choose.nextInt();
        int[] arr = new int[10000] ;
  if(integer==1) {
        // Generating a random array of size 100000
        arr = new int[100000];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }
        System.out.println("Unsorted random array is creating: " );
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
        long startTime = System.currentTimeMillis();
        // Sorting the array using selection sort
        selectionSort(arr);
        System.out.println("Sorting array: " );
        // Saving the sorted array to a file
        String outputFileName = "output.txt";
        saveArrayToFile(outputFileName, arr);
        System.out.println("Saved sorted array to file :" + outputFileName);
// Code to be timed goes her
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " milliseconds");

    }

    public static void selectionSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i+1; j < len; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int k = arr[i];
            arr[i] = arr[min];
            arr[min] = k;
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
}

