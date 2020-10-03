package helpppl.hs;

import java.util.Scanner;

public class StringRepeat {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        while (true) {
            char[] input = scn.nextLine().toCharArray();
            int count = 1;
            char check = '\0';
            for (int i = 0; i < input.length; ++i) {
                if (check == '\0') {
                    check = input[i];
                } else if (input[i] == check) {
                    ++count;
                } else {
                    System.out.print("" + check + (count > 1 ? count : ""));
                    check = input[i];
                    count = 1;
                }
            }
            System.out.println("" + check + (count > 1 ? count : ""));
        }
    }
}
