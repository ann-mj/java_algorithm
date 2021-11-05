package q1012;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N; // 열
    static int M; // 행
    static int T;
    static int K;
    static int[][] a;
    static boolean[][] visit;
    static int[][] dir = {{0,1}, {0,-1} , {1,0} , {-1,0}};
    static ArrayList<Integer> group;
    static int gcnt;
    static void input() {
        T = scan.nextInt();

        for(int i=0; i<T; i++) {
            N = scan.nextInt();
            M = scan.nextInt();
            K = scan.nextInt();
            a = new int[M][N];
            visit = new boolean[M][N];
            group = new ArrayList<>();
            for(int j=0; j<K; j++) {
                int y = scan.nextInt();
                int x = scan.nextInt();
                a[x][y] = 1; // 배추 있는 곳 표시
            }

            pro();
            sb.append(group.size()).append('\n');
        }
    }

    static void pro() {
        // N , M 에 대해서 배추가 있으면 탐색할 가치가 있음.
            for(int i=0; i<M; i++) {
                for(int j=0; j<N; j++) {
                if(!visit[i][j] && a[i][j] == 1) {
                    gcnt = 0;
                    dfs(i,j);
                    group.add(gcnt);
                }
            }
        }

    }

    static void dfs(int x, int y) {
        gcnt++;
        visit[x][y] = true;
        
        // 상하좌우 살피면서 탐색 시작
        for(int i =0; i<4; i++) {
            int nx = x + dir[i][0], ny = y + dir[i][1];
            if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if(visit[nx][ny]) continue;
            if(a[nx][ny] == 0) continue;
            dfs(nx,ny);
        }
    }

    public static void main(String[] args) {
        input();
        System.out.println(sb.toString());
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

        String readLine() {
            String st = "";
            try {
                st = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st;
        }
    }
}
