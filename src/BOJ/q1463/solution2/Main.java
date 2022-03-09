package BOJ.q1463.solution2;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int ret;
    static int[] dp;

    public static void solve() {
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
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

