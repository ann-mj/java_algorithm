package interview.chapter4.q4_1;

import java.util.Scanner;

/**
 * 정수에서 1로 세팅된 비트의 개수를 찾는 프로그램
 */
public class Main {
    static int N;
    public static void main(String[] args) {
        input();
        System.out.println(countBits(N));
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
    }

    public static short countBits(int x) {
        short numBits = 0;
        while (x != 0) {
            numBits += (x & 1);
            x >>>= 1;
        }
        return numBits;
    }
}
