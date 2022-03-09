package SWEA.SWEA_2105.solution1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T;
    static int N;
    static int[][] arr;
    static List<Integer> picked; // 뽑은 숫자, 중복되면 안됨.
    static int ret = -1;
    static int[] dy = {1, 1, -1, -1};
    static int[] dx = {-1, 1, 1, -1};  // 좌하, 우하, 우상, 좌상 순서

    static void dfs(int sy, int sx, int y, int x, int dir, int count) {
        // 탈출 조건
        // 좌상 까지 다 탐색한 이후에는 좌하를 다시 탐색 할 가치가 없다.
        // 위->아래 , 좌->우 순서대로 탐색하기 때문에
        if(dir > 3) return;

        int ny = y + dy[dir];
        int nx = x + dx[dir];

        // 다음 탐색해야 될 가게가 시작점과 같다면 사각형 완성
        if (sy == ny && sx == nx) {
            ret = Math.max(ret, count);
            return;
        }

        if(ny < 0 || ny >= N || nx < 0 || nx >= N) return;
        if(picked.contains(arr[ny][nx])) return; // 이미 뽑은 숫자는 뽑으면 안됨.

        picked.add(arr[ny][nx]);
        dfs(sy, sx, ny, nx, dir, count + 1); // 진행 방향 그대로
        dfs(sy, sx, ny, nx, dir + 1, count + 1); // 다음 방향 (90도 시계반대방향)

        // 탐색 다 끝났으면, picked 에서 제거해줘야 함
        picked.remove((Integer) arr[ny][nx]);
    }

    static void solve() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                picked.clear();
                picked.add(arr[y][x]);
                dfs(y, x, y, x, 0, 1);
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            picked = new ArrayList<>();
            ret = -1;
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    arr[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            solve();
            sb.append("#" + t + " " + ret + "\n");
        }
        System.out.println(sb);

        // 출력 방식
        /* System.out.println(String.format("#%d %d", T, MaxSize)); */
    }
}
