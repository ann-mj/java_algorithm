package interview.chapter4.q4_1;

import java.util.Scanner;

public class Main {
    static long N;

    public static void main(String[] args) {
        input();
        System.out.println(parity(N));
    }

    /**
     * 무식하게 푸는 방법
     *
     * @param 64비트 숫자
     * @return 1 이 홀수개이면 1 , 짝수개이면 0
     */
//    private static short parity(long x) {
//        short result = 0;
//        while (x != 0) {
//            result ^= (x & 1);
//            x >>>= 1;
//        }
//        return result;
//    }

    /**
     * 좀 더 나은 방법
     *
     * @param x : 64비트 숫자
     * @return
     */
//    private static short parity(long x) {
//        short result = 0;
//        while (x != 0) {
//            result ^= 1;
//            x &= (x - 1); // x의 하위 비트를 지운다.
//        }
//        return result;
//    }

    /*
    11010111 의 패리티는
    1101 과 0111을 XOR 연산결과인 1010의 패리티와 같다.
    1010의 패리티는 10과 10을 XOR 연산한 결과와 같다.
    최정 결과는 0 과 0 을 XOR 연산한 패리티인 0이 된다.
    따라서, 11010111 의 패리티는 0이다.
     */
    private static short parity(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return (short) (x & 0x1);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextLong();
    }
}
