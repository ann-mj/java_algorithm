package interview.chapter4.q4_2;

import java.util.Scanner;

/**
 * 64비트 정수가 주어졌을 때 i 번째 비트와 j 번째 비트를 스왑하는 코드를 작성하자.
 */
public class Main {
    static long N;
    static int i,j;
    public static void main(String[] args) {
        input();
        System.out.println(1L);
        System.out.println(swapBits(N, i , j));
    }

    private static long swapBits(long n, int i, int j) {
        // i번째 비트와 j번째 비트가 같은지 비교한다.
        if (((n >>> i) & 1) != ((n >>> j) & 1)) {
            // i번째 비트와 j번째 비트가 다르다면, 각 비트를 뒤집어서 스왑을 구현
            // 1L << i : 1을 i번 왼쪽으로 shift

            // 8 비트라고 가정해보자. i 는 3, j 는 5
            // long bitMask = (1L << i) | (1L << j);
            // 00001000 , 00100000
            // bitMask = 00101000 이 된다.
            // x = 1일때 x^1 = 0 , x = 0 일때 x^1 = 1 이므로, XOR 을 이용하여 비트를 뒤집을 수 있다.
            // n ^= bitmask 는 i , j 번째 비트를 뒤집은 결과와 같게 된다.
            long bitMask = (1L << i) | (1L << j);
            n ^= bitMask;
        }
        return n;
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
    }
}
