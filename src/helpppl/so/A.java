package helpppl.so;

public class A {
    
    public static void main(String[] args) {
        System.out.println(f(new int[] {2, 2, 2, 2, 2}, 0, 4, 2));
        System.out.println(f(new int[] {1, 2, 3, 3, 3}, 0, 4, 3));
        System.out.println(f(new int[] {1, 2, 3, 4, 5}, 0, 4, 5));
        System.out.println(f(new int[] {1, 2, 3, 5, 5}, 0, 5, 5));
        System.out.println(f(new int[] {1, 1, 1, 2, 2}, 1, 3, 1));
    }
    
    public static int f(int[] arr, int l, int r, int n) {
        if (l >= r) {
            return 0;
        } else if (l == r - 1) {
            if (arr[l] == n) {
                return 1;
            } else {
                return 0;
            }
        } else {
            int m = (l + r) / 2;
            return f(arr, l, m, n) + f(arr, m, r, n);
        }
    }
}