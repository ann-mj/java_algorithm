package BOJ.q1254.solution2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (s.length() == 0 || isPD(s)) {
            System.out.println(s.length());
            return;
        }

        for (int i = 1; i < s.length(); i++) {
            StringBuilder target = new StringBuilder(s).append(new StringBuilder(s.substring(0, i)).reverse());
            if (isPD(target.toString())) {
                System.out.println(target.length());
                return;
            }
        }
    }

    private static boolean isPD(String input) {
        for (int i = 0; i < (input.length() - 1); i++) {
            if (input.charAt(i) == input.charAt(input.length() - 1 - i)) continue;
            return false;
        }
        return true;
    }
}
