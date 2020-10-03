package experiments.binarysearch;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 7, 11, 13, 21, 26, 33, 40, 57};
        System.out.println(binSearch(arr, 3));
        System.out.println(binSearch(arr, 21));
        System.out.println(binSearch(arr, 57));
        System.out.println(binSearch(arr, -13));
        System.out.println(binSearch(arr, 20));
        System.out.println(binSearch(arr, 58));
    }
    
    public static int binSearch(int[] arr, int x) {
        return binSearch(arr, x, 0, arr.length - 1);
    }
    
    public static int binSearch(int[] arr, int x, int l, int r) {
        if (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) {
                return mid;
            }
            if (arr[mid] > x) {
                return binSearch(arr, x, l, mid - 1);
            } else {
                return binSearch(arr, x, mid + 1, r);
            }
        }
        return -1;
    }
}
