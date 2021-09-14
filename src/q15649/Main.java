package q15649;

import java.io.*;
import java.util.*;

/*
*
* 문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.
* */
public class Main {
    static StringBuilder sb = new StringBuilder(); // 출력용

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[N + 1]; // 어떤 숫자가 선택되었었는지를 확인하는 배열
        ans = new int[M+1];
    }

    // k 는 수열의 크기, k가 1 부터 M 까지 될때까지 반복수행
    public static void rec_func(int k) {
        if(k == M+1) {
            // sb 에 쓰기
            for(int j=1; j<M+1; j++) {
                sb.append(ans[j]);
                sb.append(" ");
            }
            sb.append("\n");
        } else {
            for(int i=1; i<N+1; i++) {
                if(selected[i] == 1) {
                    continue; // for 문 다음번 i 로 진행
                }
                selected[i] = 1; // 숫자 i (1~8) 을 선택
                ans[k] = i; // 출력해야할 수열 ( 나중에 출력하기 편하도록 )
                // 특정 조건을 만족하는지 체크하고, 다음 k + 1 번째 부터 M 까지 체크
                rec_func(k+1);
                selected[i] = 0; // 다시 selected 된 거를 초기화 (선택 안된 것으로 처리함)
                ans[k] = 0; // 다시 초기화
            }
        }
    }

    public static void main(String[] args) {
        input();
        rec_func(1);
        System.out.println(sb.toString()); // 출력
    }

    static int N, M;
    static int[] selected, ans; // 수가 선택된건지 아닌지 체크

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
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
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
