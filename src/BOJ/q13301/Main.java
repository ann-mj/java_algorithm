package BOJ.q13301;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        long[][] dp = new long[N + 1][2];

        dp[1][0] = 1;
        dp[1][1] = 1;
        if(N == 1){
            System.out.println((dp[N][0] + dp[N][1]) * 2);
            return;
        }
        dp[2][0] = 1;
        dp[2][1] = 2;
        if(N == 2) {
            System.out.println((dp[N][0] + dp[N][1]) * 2);
            return;
        }
        for (int i = 3; i <= N; i++) {
            dp[i][0] = dp[i-1][1] + dp[i-2][0];
            dp[i][1] = dp[i-1][0] + dp[i-2][1];
        }

        System.out.println((dp[N][0] + dp[N][1]) * 2);
    }
}
