package BOJ.q1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * import java.io.BufferedReader;
 * import java.io.InputStreamReader;
 * import java.util.StringTokenizer;
 *
 * public class BufferedReaderTest {
 *     // BufferedReader는 Exception이 처리를 따로 해줘야 하기 때문에 throws를 해주거나
 *     // try ~ catch로 예외처리를 해줘야합니다.
 *     public static void main(String[] args) throws Exception {
 *         // BufferedReader 객체 생성
 *         // new InputStreamReader 및 System.in
 *         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 *
 *         // StringTokenizer 객체 선언
 *         StringTokenizer st = null;
 *
 *         // String Line이므로 Integer.parseInt를 이용하여 형변환해야함
 *         int n = Integer.parseInt(br.readLine());
 *
 *         // "1 3 5 7" 식으로 공란 포함 String Line일시 StringTokenizer 이용
 *         int[] arrays = new int[n + 1];
 *         st = new StringTokenizer(br.readLine());
 *         for (int i = 1; i <= n; i++) {
 *             // 배열에다 토큰을 하나씩 불러서 입력해줌
 *             arrays[i] = Integer.parseInt(st.nextToken());
 *         }
 *     }
 * }
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][3];
        int[][] dp = new int[N+1][3];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][0] = arr[1][0];
        dp[1][1] = arr[1][1];
        dp[1][2] = arr[1][2];

        for (int i = 2; i <= N; i++) {
            dp[i][0] = arr[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = arr[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = arr[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
    }
}
