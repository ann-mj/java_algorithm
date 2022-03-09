package BOJ.q1806;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static int S;
    static int[] a;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        S = scan.nextInt();
        a = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            a[i] = scan.nextInt();
        }
    }


    private static void pro() {
        int l = 1, r = 1;
        int ans = N + 1; // 가장 짧은 것의 길이 , 초기에는 배열의 크기보다 1 큰 값으로 설정. 왜 ? 배열의 크기가 가장 짧은 것일 수도 있으니까
        int sum = a[1]; // 현재까지의 sum

        // l 과 r 선택

        // l 에서 r 까지의 합 계산

        // 15 이상인지 비교

        // 15 보다 넘으면 l 감소 시킴

        // 15 보다 작으면 r 증가 시킴
        for(l = 1; l <= N; l++) {
            sum -= a[l-1];

            // l ~ r 까지의 합
            while(sum < S && r < N) {
                r++;
                sum += a[r];
            }

            if(sum >= S && r-l+1 < ans) {
                ans = r-l+1;
            }

        }

        if (ans == N + 1) {
            sb.append(0);
        } else {
            sb.append(ans);
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();
        pro();
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
            while (st == null || !st.hasMoreElements()) {
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
