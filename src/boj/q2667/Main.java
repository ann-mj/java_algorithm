package boj.q2667;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    
    static int N;  // 지도의 크기
    static String[] a; // 그래프 가지고 있을 배열
    static boolean[][] visit;
    static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; // 4개 방향을 표시하기 위한 배열
    static ArrayList<Integer> group; // 단지 갯수를 저장하고 있는 배열
    static int group_cnt = 0;

    static void dfs(int x, int y) {
        group_cnt++;
        visit[x][y] = true;

        for(int i=0; i<4; i++) {
            int nx = x + dir[i][0], ny = y + dir[i][1];
            // 유효한지 아닌지 체크
            if(nx >= N || ny >= N || nx < 0 || ny < 0) continue;
            if(visit[nx][ny]) continue;
            if(a[nx].charAt(ny)== '0') continue;
            dfs(nx,ny);
        }
    }

    static void pro() {
        group = new ArrayList<>();
        // 아직 방문하지 않은 집 방문
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(a[i].charAt(j) == '0' || visit[i][j]) continue;
                group_cnt = 0;
                dfs(i,j);
                // 탐색을 하고 나서
                group.add(group_cnt);
            }
        }

        Collections.sort(group);
        sb.append(group.size()).append('\n');
        for(int ans : group) sb.append(ans).append('\n');
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = scan.nextInt();
        a = new String[N];
        visit = new boolean[N][N];
        for(int i=0; i<N; i++) {
            a[i] = scan.nextLine();
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(s));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        Double nextDouble() {
            return Double.parseDouble(next());
        }

        Long nextLong() {
            return Long.parseLong(next());
        }

        String nextLine() {
            String str = "";

            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
