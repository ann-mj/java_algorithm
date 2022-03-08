package boj.q11660.solution1;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] pairs;
    static int[] ret;
    static StringBuilder sb;
    static void solve() {
        int[][] dp = new int[N + 1][N + 1];
        // dp 만들기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i][j];
            }
        }

        sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int x1 = pairs[i][0];
            int y1 = pairs[i][1];
            int x2 = pairs[i][2];
            int y2 = pairs[i][3];
            sb.append(dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1] + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        pairs = new int[M][4];
        ret = new int[M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                pairs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
