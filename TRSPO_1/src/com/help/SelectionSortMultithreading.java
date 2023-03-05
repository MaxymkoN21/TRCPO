package com.help;

import java.util.Arrays;

public class SelectionSortMultithreading {

    private static void selectionSort(int[] arr, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            int minIndex = i;
            for (int j = i + 1; j < endIndex; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    private static void merge(int[] arr, int startIndex, int middleIndex, int endIndex) {
        int[] temp = new int[endIndex - startIndex];
        int i = startIndex;
        int j = middleIndex;
        int k = 0;

        while (i < middleIndex && j < endIndex) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i < middleIndex) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        while (j < endIndex) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (i = 0; i < k; i++) {
            arr[startIndex + i] = temp[i];
        }
    }

    private static void parallelSort(int[] arr, int numThreads) {
        int chunkSize = arr.length / numThreads;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int startIndex = i * chunkSize;
            final int endIndex = (i == numThreads - 1) ? arr.length : (i + 1) * chunkSize;
            threads[i] = new Thread(() -> {
                selectionSort(arr, startIndex, endIndex);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i < numThreads; i *= 2) {
            for (int j = 0; j < arr.length; j += 2 * i) {
                int startIndex = j;
                int middleIndex = j + i;
                int endIndex = Math.min(j + 2 * i, arr.length);
                merge(arr, startIndex, middleIndex, endIndex);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 3, 7, 1, 8, 2, 6, 5, 4, 0};
        parallelSort(arr, 4);
        System.out.println(Arrays.toString(arr));
    }

}

