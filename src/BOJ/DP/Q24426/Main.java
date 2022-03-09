package BOJ.DP.Q24426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr;
    static int[][] dp;
    static int R, C;
    static StringBuilder sb = new StringBuilder();
    private static int sy;
    private static int sx;
    private static int r;
    private static int c;

    static void solve() {
        int result = 0;
        result = findPath(1, 1, R, C) + findPath(R, C, N, N) - arr[R][C];
        sb.append(result).append(" ");

        // 두번째 값
        result = findPath2();
        sb.append(result);
    }

    private static int findPath2() {
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (R == y && C == x) {
                    continue;
                }

                // 맨 윗 열
                if (R == 1 && x >= C && y == R) {
                    dp[y][x] = 0;
                    continue;
                }

                // 맨 왼쪽 행
                if (y >= R && C == 1 && x == C) {
                    dp[y][x] = 0;
                    continue;
                }

                // ( R,C ) 의 하단
                if (y - 1 == R && x == C) {
                    dp[y][x] = arr[y][x] + dp[y][x - 1];
                    continue;
                }

                // ( R,C ) 의 우측
                if (y == R && x - 1 == C) {
                    dp[y][x] = arr[y][x] + dp[y - 1][x];
                    continue;
                }

                dp[y][x] = arr[y][x] + Math.max(dp[y][x - 1], dp[y - 1][x]);
            }
        }
        return dp[N][N];
    }

    private static int findPath(int sy, int sx, int r, int c) {
        // dp 배열 반드시 초기화 해주어야 한다.
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], 0);
        }

        for (int y = sy; y <= r; y++) {
            for (int x = sx; x <= c; x++) {
                dp[y][x] = arr[y][x] + Math.max(dp[y - 1][x], dp[y][x - 1]);
            }
        }
        return dp[r][c];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        solve();
        System.out.println(sb);
    }
}