package BOJ.GRAPH.Q2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int ret;
    static int N;
    static int M;
    static int[][] arr;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N : y
        M = Integer.parseInt(st.nextToken()); // M : x

        arr = new int[N + 1][M + 1];

        for (int y = 1; y <= N; y++) {
            String str = br.readLine();
            for (int x = 1; x <= M; x++) {
                arr[y][x] = Integer.parseInt(String.valueOf(str.charAt(x-1)));
            }
        }
        solve();
        System.out.println(ret);
    }

    private static void solve() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N + 1][M + 1];
        visit[1][1] = true;
        queue.add(1);
        queue.add(1);
        queue.add(1); // distance , 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

        while (!queue.isEmpty()) {
            int curY = queue.poll();
            int curX = queue.poll();
            int dist = queue.poll();

            if(curX == M && curY == N) {
                ret = dist;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];
                if(ny < 1 || ny > N || nx < 1 || nx > M) continue;
                if(visit[ny][nx]) continue;
                if(arr[ny][nx] == 0) continue;
                queue.add(ny);
                queue.add(nx);
                queue.add(dist + 1);
                visit[ny][nx] = true;
            }
        }
    }
}
