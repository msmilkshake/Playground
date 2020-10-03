package experiments.quicksort;

import util.Timer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuickSort {
    public static void main(String[] args) {
        Timer t = new Timer();
        t.start();
        System.out.println("Reading file...");
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("box/directory.txt"))) {
            String lineRead;
            while ((lineRead = br.readLine()) != null) {
                list.add(Integer.valueOf(lineRead.split(" ")[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] arr = list.stream().mapToInt(i -> i).toArray();
        t.stop();
        System.out.println("Time taken: " + t);
        t.start();
        System.out.println("Sorting...");
        quickSort(arr);
        t.stop();
        System.out.println("Time taken: " + t);
        System.out.println();
    }
    
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    
    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int pivot = partition(arr, l, r);
            quickSort(arr, l, pivot - 1);
            quickSort(arr, pivot + 1, r);
        }
    }
    
    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int partitionI = l;
        for (int i = l; i < r; ++i) {
            if (arr[i] <= pivot) {
                swap(arr, i, partitionI++);
            }
        }
        swap(arr, partitionI, r);
        return partitionI;
    }
    
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
