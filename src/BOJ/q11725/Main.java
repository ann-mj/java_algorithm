package BOJ.q11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    // 인접리스트로 트리를 표현해보자.
    static ArrayList<Integer>[] adj;
    static int[] parent;

    static void input() {
        N = scan.nextInt();
        adj = new ArrayList[N+1];
        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i=1; i<N; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y); // x -> y 로 가는 것
            adj[y].add(x);
        }
    }

    static void dfs(int x, int par) {
        for(int y : adj[x]) {
            if(y == par) continue;
            parent[y] = x;
            dfs(y, x);
        }
    }

    static void pro() {
        // 1번 노드 부터 시작
        dfs(1, -1);

        for(int i=2; i<=N; i++) {
            sb.append(parent[i]).append('\n');
        }
        System.out.println(sb);
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
