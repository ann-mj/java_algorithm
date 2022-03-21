package samsung.q14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int maxSum = 0;
    static int sum = 0;
    static int[][][] types = {
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},           // ----
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            {{0, 0}, {1, 0}, {1, 1}, {0, 1}},           // ㅁ
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},           // --=
            {{0, 0}, {1, 0}, {2, 0}, {2, -1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
            {{0, 0}, {0, -1}, {1, -1}, {2, -1}},
            {{0, 0}, {1, 0}, {1, -1}, {1, -2}},
            {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}},       // -=_
            {{0, 0}, {1, 0}, {1, -1}, {0, 1}},
            {{0, 0}, {1, 0}, {1, -1}, {2, -1}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},       // ㅗ
            {{0, 0}, {1, 0}, {2, 0}, {1, -1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, -1}, {1, 1}}
    };
    static int N, M;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                for(int i=0; i<19; i++) {
                    // 사각형이 범위 안에 들어와있다면
                    if(isIn(y,x,types[i])) {
                        // 사각형의 합을 구함
                        int sum = getSum(y, x, types[i]);
                        maxSum = Math.max(sum, maxSum);
                    }
                }
            }
        }
        System.out.println(maxSum);
    }

    private static int getSum(int y, int x, int[][] type) {
        int res = 0;
        for(int i=0; i<4; i++) {
            int ny = y + type[i][0];
            int nx = x + type[i][1];
            res += board[ny][nx];
        }
        return res;
    }

    private static boolean isIn(int y, int x, int[][] type) {
        for(int i=0; i<4; i++) {
            int ny = y + type[i][0];
            int nx = x + type[i][1];
            if(ny < 0 || ny >= N || nx < 0 || nx >= M) return false;
        }
        return true;
    }

}
