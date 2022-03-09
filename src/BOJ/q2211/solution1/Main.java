package BOJ.q2211.solution1;

import java.util.*;

public class Main {
    static int N, M;
    static List<Edge>[] graph;
    static List<Edge> result;

    public static void main(String[] args) {
        input();
        process();
    }

    static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        graph = new ArrayList[N + 1];
        result = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int time = scanner.nextInt();
            Edge edge = new Edge(from, to, time);
            graph[from].add(edge);
            Edge reverse = new Edge(to, from, time);
            graph[to].add(reverse);
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int time;

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    static class Edge {
        int from;
        int to;
        int time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }

        @Override
        public boolean equals(Object obj) {
            Edge edge = (Edge) obj;
            return (this.from == edge.from && this.to == edge.to) || (this.from == edge.to && this.to == edge.from);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    private static void process() {
        // 1번 노드에서 각 노드간의 최단거리에 있는 경로들을 구하자.
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(1, 0));
        int[] dist = new int[N + 1];
        int[] parents = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();

            for (Edge edge : graph[current.index]) {
                if (dist[edge.to] > dist[current.index] + edge.time) {
                    dist[edge.to] = dist[current.index] + edge.time;
                    priorityQueue.add(new Node(edge.to, dist[edge.to]));
                    parents[edge.to] = current.index; // 이 부분이 핵심인 듯
                }
            }
        }

        System.out.println(N - 1);
        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i] + " " + i);
        }
    }
}
