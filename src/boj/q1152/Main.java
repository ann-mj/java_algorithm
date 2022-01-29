package boj.q1152;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();
    static String a;

    public static void main(String[] args) {
        input();
        pro();
    }

    private static void pro() {
        if (a.length() == 1 && a.charAt(0) == ' ') {
            System.out.println(0);
            return;
        }

        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (i == 0 && a.charAt(0) == ' ') continue; // 첫번째 공백
            if (i == a.length() - 1 && a.charAt(i) == ' ') continue; // 맨 마지막 줄
            if (a.charAt(i) == ' ') count++;
        }

        System.out.println(count + 1);
    }

    private static void input() {
        a = scan.nextLine();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
