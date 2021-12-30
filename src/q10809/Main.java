package q10809;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String s;
    static String alpha = "abcdefghijklmnopqrstuvwxyz";
    static int[] visit = new int[26];

    public static void main(String[] args) {
        input();
        process();
    }

    public static void process() {
        char[] chars = s.toCharArray();

        Arrays.fill(visit, -1);
        for (int i = 0; i < chars.length; i++) {
            int index = alpha.indexOf(chars[i]);
            if(visit[index] >= 0) continue;
            visit[index] = i;
        }

        for (int val : visit) {
            System.out.print(val + " ");
        }
    }

    public static void input() {
        Scanner scanner = new Scanner(System.in);
        s = scanner.next();
    }
}
