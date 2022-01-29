package boj.q5014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    // 최단 거리에선 BFS 를 쓰자
    static int F,S,G,U,D;

    static int[] a; // 엘리베이터 전체 층수 가지고 있는 배열 O(N)
    static boolean[] visit; // 엘리베이터 층수 방문 여부 체크
    static int count = Integer.MAX_VALUE; // 버튼의 수의 최소값
    static Queue<Node> q = new LinkedList<>();
    static int[] dir;
    static void input() {
        F = scan.nextInt();
        S = scan.nextInt();
        G = scan.nextInt();
        U = scan.nextInt();
        D = scan.nextInt();
        a = new int[F+1]; // 1부터 F 까지 , 0은 없는걸로 생각
        dir = new int[]{U, -D};
        visit = new boolean[F+1];
    }

    static void bfs(Node x) {
        q.add(x);
        visit[x.floor] = true;

        while(!q.isEmpty()) {
            Node n = q.poll();
            if(n.floor == G) {
                count = n.cnt;
                return;
            }

            for(int i =0; i < 2; i++) {
                int nx = dir[i] + n.floor;
                if(dir[i] == 0 || nx < 1 || nx > F || visit[nx]) continue;
                Node b = new Node(nx, n.cnt+1);
                visit[nx] = true;
                q.add(b);
            }
        }
    }

    static void pro() {
        Node start = new Node(S, 0);
        bfs(start);
        if(count == Integer.MAX_VALUE) System.out.println("use the stairs");
        else System.out.println(count);
    }


    public static void main(String[] args) {
        input();
        pro();
    }

    // F층 건물의 총 높이
    // G층 가려는 곳의 위치
    // S : 현재 위치
    // U 층 위, D 층 아래에는 엘리베이터가 없음
    // G 층에 도착하려면 버튼을 적어도 몇 번 눌러야 하는지 구하는 프로그램을 작성하자.
    // 1층 부터 시작, 가장 높은 층은 F 층
    // 엘리베이터로 이동할 수 없을 때는 "use the stairs" 출력

    static class Node {
        int floor; // 현재 층 수
        int cnt; // 현재 노드까지 오는데 걸리는 cnt
        public Node(int floor, int cnt) {
            this.floor = floor;
            this.cnt = cnt;
        }
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
