package q2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    // 학생 번호는 1~N 까지
    // M 은 키를 비교한 횟수 ( edge 갯수 )
    static int N, M;
    static ArrayList<Integer>[] a;
    static int[] in_bound;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        a = new ArrayList[N+1];
        in_bound = new int[N+1];
        for(int i=0; i<=N; i++) {
            a[i] = new ArrayList();
        }
        for(int j=0; j<M; j++) {
            int x = scan.nextInt(), y = scan.nextInt();
            a[x].add(y); // x -> y
            in_bound[y]++;
        }
    }

    static void pro() {
        Queue<Integer> queue = new LinkedList<>();

        for(int i=1; i<=N; i++) {
            if(in_bound[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            sb.append(node).append(' ');
            for(int x : a[node]) {
                in_bound[x]--;
                if(in_bound[x] == 0) queue.add(x);
            }
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
    }
}
