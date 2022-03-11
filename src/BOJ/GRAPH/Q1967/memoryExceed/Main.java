package BOJ.GRAPH.Q1967.memoryExceed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N; // 노드의 개수
    static int ret; // 결과
    static int[][] arr;
    static List<Node> tree = new ArrayList<>(); // 인풋 트리
    static StringBuilder sb = new StringBuilder();
    static int[] parents; // 각 노드의 parents 배열
    static class Node {
        int num; // 번호
        List<Node> child = new ArrayList<>();
        int parent; // 부모 노드
        int weight; // parent 에서 자신까지 오는 weight

        public Node(int num, int parent, int weight) {
            this.num = num;
            this.parent = parent;
            this.weight = weight;
        }

        public void appendChild(Node node) {
            child.add(node);
        }
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            Node node = tree.get(i);
            // 리프 노드
            if(node.child.size() == 0) {
                boolean[] visit = new boolean[N + 1]; // 1번 ~ N번까지 visit 체크
                visit[node.num] = true;
                dfs(node, visit, 0);
                visit[node.num] = false;
            }
        }

        sb.append(ret); // 시작 + 끝
    }

    static void dfs(Node node, boolean[] visit, int dist) {

        if(dist != 0 && node.child.size() == 0) {
            ret = Math.max(ret, dist);
            return;
        }

        // 부모 노드 담기
        if(!visit[node.parent] && node.parent > 0) {
            visit[node.parent] = true;
            // tree 에서 부모 노드를 찾자.
            //TODO node.parent 가 0이 되면 어떡하지 ? 그런 경우가 있나? 이부분 생각을 못했다.
            Node parent = tree.get(node.parent - 1); // array index 는 0 부터 시작

            dfs(parent, visit, dist + node.weight);
        }

        // 현재 노드의 자식 노드 탐색
        for (int i=node.num; i<N; i++) {
            Node candi = tree.get(i);
            // TODO !visit[candi.num] 체크를 까먹지 말자.
            // TODO  candi.num > node.num 인 경우에만 돌아도 답이 나온다.
            if(candi.parent == node.num && !visit[candi.num] && candi.num > node.num) {
                visit[candi.num] = true;
                dfs(candi, visit, dist + candi.weight); // child.weight : 현재 노드 -> 자식 노드 로 가는 가중치
                visit[candi.num] = false;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        parents = new int[N + 1];

        // 노드가 12개 면, 간선은 11개가 들어옴. (N-1 개)
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            arr[parent][child] = weight;
            parents[child] = parent;
        }


        // tree 만들기
        for (int i = 1; i <= N; i++) {
            int parent = parents[i];
            Node node = new Node(i, parent, arr[parent][i]);
            for (int j = i; j <= N; j++) {
                if (arr[i][j] == 0) continue;
                node.appendChild(new Node(j, i, arr[i][j]));
            }
            tree.add(node);
        }

        solve();
        System.out.println(sb);
    }
}
