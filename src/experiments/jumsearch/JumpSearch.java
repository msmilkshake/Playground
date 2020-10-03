package experiments.jumsearch;

public class JumpSearch {
    static int count = 0;
    static int count2 = 0;
    
    public static void main(String[] args) {
        int[] arr = {-103, -98, -65, -52, -40, -37, -24, -15, -8, -1, 3, 5, 8, 14, 22, 35, 37, 41, 42, 53, 57, 60};
        System.out.println(jumpSearch(arr, 59));
    }
    
    public static int jumpSearch(int[] arr, int elm) {
        return jumpSearch(arr, elm, 0, arr.length);
    }
    
    public static int jumpSearch(int[] arr, int elm, int start, int end) {
        System.out.println("Call: " + ++count);
        if (end - start == 0) {
            return -1;
        }
        if (arr[start] == elm) {
            return start;
        }
        int block = (int) Math.sqrt(end - start);
        int i = start + block;
        for (; i < arr.length; i += block) {
            System.out.println("Iteration: " + ++count2);
            if (arr[i] > elm) {
                break;
            }
            if (arr[i] == elm) {
                return i;
            }
        }
        if (block == 1) {
            block = 0;
        }
        return jumpSearch(arr, elm, i - block, i);
    }
}
