package BOJ.q1463;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int ret;
    static int[] dp;

    public static void solve() {
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= N; i++) {
            if (i % 3 == 0) {
                if (i % 2 == 0) {
                    dp[i] = Math.min(Math.min(dp[i/3]+1 , dp[i/2]+1), dp[i-1] + 1);
                } else{
                    dp[i] = Math.min(dp[i / 3] + 1, dp[i - 1] + 1);
                }
            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i / 2] + 1, dp[i - 1] + 1);
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }

        ret = dp[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[1000001];
        solve();
        System.out.println(ret);
    }
}
