package BOJ.GRAPH.Q1967.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N; // 노드의 개수
    static int ret; // 결과
    static StringBuilder sb = new StringBuilder();
    static int[] parents; // 각 노드의 parents 배열
    static ArrayList<Node>[] tree;
    static class Node {
        int num; // 번호
        int weight; // parent 에서 자신까지 오는 weight

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    static void solve() {
        for (int i = 1; i < N; i++) {
            List<Node> nodes = tree[i]; // i 와 연결된 node들

            for (int j = 0; j < nodes.size(); j++) {
                Node node = nodes.get(j);
                if(tree[node.num].size() == 1) {
                    // 리프 노드
                    boolean[] visit = new boolean[N + 1]; // 1번 ~ N번까지 visit 체크
                    visit[node.num] = true;
                    dfs(node, visit, 0);
                    visit[node.num] = false;
                }
            }
        }

        sb.append(ret); // 시작 + 끝
    }

    static void dfs(Node node, boolean[] visit, int dist) {

        if(dist != 0 && tree[node.num].size() == 1) {
            ret = Math.max(ret, dist);
            return;
        }

        // 현재 노드와 연결된 노드 탐색
        List<Node> adj = tree[node.num];
        for (int i = 0; i < adj.size(); i++) {
            Node next = adj.get(i);

            if(visit[next.num]) continue;
            visit[next.num] = true;
            dfs(next, visit, dist + next.weight);
            visit[next.num] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];

        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 노드가 12개 면, 간선은 11개가 들어옴. (N-1 개)
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree[parent].add(new Node(child, weight));
            tree[child].add(new Node(parent, weight));
            parents[child] = parent;
        }

        solve();
        System.out.println(sb);
    }
}
