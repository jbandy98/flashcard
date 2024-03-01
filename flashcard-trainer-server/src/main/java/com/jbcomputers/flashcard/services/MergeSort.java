package com.jbcomputers.flashcard.services;

import org.springframework.stereotype.Component;

@Component
public class MergeSort {

    void merge(int MainArray[], int left, int middle, int right)
    {
        // Find sizes of two subarrays to be merged
        int leftArraySize = middle - left + 1;
        int rightArraySize = right - middle;

        // Create temp arrays
        int LeftArray[] = new int[leftArraySize];
        int RightArray[] = new int[rightArraySize];

        // Copy data to temp arrays
        for (int i = 0; i < leftArraySize; ++i)
            LeftArray[i] = MainArray[left + i];
        for (int j = 0; j < rightArraySize; ++j)
            RightArray[j] = MainArray[middle + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int leftArrayIndex = 0, rightArrayIndex = 0;

        // Initial index of merged subarray array
        int mergedIndex = left;
        while (leftArrayIndex < leftArraySize && rightArrayIndex < rightArraySize) {
            if (LeftArray[leftArrayIndex] <= RightArray[rightArrayIndex]) {
                MainArray[mergedIndex] = LeftArray[leftArrayIndex];
                leftArrayIndex++;
            }
            else {
                MainArray[mergedIndex] = RightArray[rightArrayIndex];
                rightArrayIndex++;
            }
            mergedIndex++;
        }

        // Copy remaining elements of LeftArray[] if any
        while (leftArrayIndex < leftArraySize) {
            MainArray[mergedIndex] = LeftArray[leftArrayIndex];
            leftArrayIndex++;
            mergedIndex++;
        }

        // Copy remaining elements of RightArray[] if any
        while (rightArrayIndex < rightArraySize) {
            MainArray[mergedIndex] = RightArray[rightArrayIndex];
            rightArrayIndex++;
            mergedIndex++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int MainArray[], int leftSize, int rightSize)
    {
        if (leftSize < rightSize) {

            // Find the middle point
            int middlePoint = leftSize + (rightSize - leftSize) / 2;

            // Sort first and second halves
            sort(MainArray, leftSize, middlePoint);
            sort(MainArray, middlePoint + 1, rightSize);

            // Merge the sorted halves
            merge(MainArray, leftSize, middlePoint, rightSize);
        }
    }

    // A utility function to print array of size n
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code
    public void run()
    {
        int arr[] = { 12, 5, 13, 11, 6, 7 };

        System.out.println("Given array is");
        printArray(arr);

        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array is");
        printArray(arr);
    }
}
