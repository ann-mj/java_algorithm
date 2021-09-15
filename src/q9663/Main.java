package q9663;

import java.io.*;
import java.util.*;
/*문제
N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.

N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. (1 ≤ N < 15)

출력
첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.*/
public class Main {
    static StringBuilder sb = new StringBuilder(); // 출력용

    static int N;
    static int ans; // 정답
    static int[] col;
    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        col = new int[N+1];
    }

    static void rec_func(int k) {
        if(k == N + 1) {
            ans++; // 퀸의 갯수가 N개 일때 -> 다 공격가능하지 않도록 다 놓았다는 것을 의미한다.
        } else {
            //     col
            // row * * * *
            //     * * * *
            //     * * * *
            //     * * * *
            for(int c=1; c<N+1; c++) {
                col[k] = c;
                if(attackable(k, col[k])) {
                    continue;
                }
                rec_func(k+1);
            }
        }
    }

    private static boolean attackable(int k, int c) {
        // 이전의 퀸 들과 현재 퀸이 공격가능한지를 체크하는 함수.

        // 좌표 : k, c
        // 같은 열에는 절대 있을 수 없는 구조
        for(int i=1; i<k; i++) {
            // 첫번째 퀸부터 k-1 번째 퀸까지 현재 k 번째 퀸과 공격가능한지를 체크한다.

            // k 가 row , col[k] 가 col
            if(c == col[i]) {
                // 현재 행이 같은 행에 있으면 공격 가능
                return true;
            }
            // 우상향 대각선 상에 있으면 공격 가능
            if(k+c == (i) + col[i]) {
                return true;
            }

            // 우하향 대각선 상에 있으면 공격 가능
            if(k-c == (i) - col[i]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        input();
        rec_func(1);
        System.out.println(ans);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        Double nextDouble() {
            return Double.parseDouble(next());
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
