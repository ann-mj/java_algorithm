package BOJ.q2579;

import java.util.*;
import java.io.*;

public class Main {
    static int N; // 계단의 개수
    static int[] stairs;
    static int ret;

    static void solve() {
        int[][] dp = new int[N + 1][2];

        dp[1][0] = stairs[1];
        dp[1][1] = 0;

        if (N == 1) {
            ret = stairs[1];
            return;
        }

        dp[2][0] = dp[1][0] + stairs[2];
        dp[2][1] = stairs[2];

        if (N == 2) {
            ret = stairs[1] + stairs[2];
            return;
        }

        for (int i = 3; i <= N; i++) {
            dp[i][0] = stairs[i] + dp[i - 1][1];
            dp[i][1] = stairs[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
        }

        ret = Math.max(dp[N][0], dp[N][1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        stairs = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            stairs[i] = Integer.parseInt(st.nextToken());
        }

        solve();
        System.out.println(ret);
    }
}
