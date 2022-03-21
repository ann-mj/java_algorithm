package samsung.q14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int ret;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N+1][2];
        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            board[i][0] = Integer.parseInt(st.nextToken()); // 상담 걸리는 일수
            board[i][1] = Integer.parseInt(st.nextToken()); // 비용
        }

        int ret = getSum(1);

        System.out.println(ret);
    }

    // i 를 포함해서 배열의 끝(N) 까지 계산해서 최대 값을 구하는 함수
    private static int getSum(int start) {
        if(start > N) return 0;
        int sum = 0;
        int candi1 = 0 , candi2 = 0;

        if(board[start][0] + start <= N + 1) {
            int nextIndex = start + board[start][0];
            candi1 = board[start][1] + getSum(nextIndex);   // 자기 자신 포함
        }
        candi2 = getSum(start + 1);               // 자기 자신 제외 다음것 부터
        sum += Math.max(candi1, candi2);
        return sum;
    }
}
