package BOJ.q15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] dir = {{1,0},{0,-1},{-1,0},{0,1}}; // 방향은 4가지가 전부이고, 이를 순환하면서 좌표가 변경된다.
    static boolean[][] visit = new boolean[101][101];
    static void input() {
        N = scan.nextInt();
        for(int i=0; i<N; i++) {
            ArrayList<Integer>[] a;
            int x = scan.nextInt(), y = scan.nextInt();
            int d = scan.nextInt(), g = scan.nextInt();

            a = new ArrayList[g+1];
            for(int c=0; c<g+1; c++) a[c] = new ArrayList<>();
            // d , g 만 주어지면 시작점(x,y) 에서 끝점까지 가는 모든 방향을 알 수 있다.
            a[0].add(d); // 초기화

            for(int j=1; j<=g; j++) {
                for(int k=0; k<a[j-1].size(); k++) {
                    a[j].add(a[j-1].get(k));
                }

                for(int l=a[j-1].size()-1; l>=0; l--) {
                    a[j].add((a[j-1].get(l) + 1) % 4);
                }
            }

            // 시작점 visit 처리
            visit[x][y] = true;
            int nx = x;
            int ny = y;
            // 탐색하며 visit 처리
            for(int s=0; s<a[g].size(); s++) {
                nx = nx + dir[a[g].get(s)][0];
                ny = ny + dir[a[g].get(s)][1];
                visit[nx][ny] = true;
            }
        }
    }

    static void pro() {
        int count = 0;
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(visit[i][j] && visit[i+1][j] && visit[i][j+1] && visit[i+1][j+1]) count++;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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

        String readLine() {
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
