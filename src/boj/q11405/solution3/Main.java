package boj.q11405.solution3;

import java.util.*;

/*
 * 정답 코드
 * */
public class Main {
    public static void main(String[] args) {
        input();
        process();
    }

    static final int MAX_VALUE = (int) 1e10;
    static int N, M;
    static int[] booksToBuyByPerson;
    static int[] booksHasBookStore;
    static int[][] deliveryCost;

    // 용량 ( 구매하고자 하는 책 개수 또는 보유하고 있는 책 )
    static int[][] capacities;
    // 유량 ( 배송 횟수 )
    static int[][] flows;
    static List<Integer>[] adjacentNodes;
    static int[][] costs; // 간선의 가중치들

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        capacities = new int[N + M + 2][N + M + 2];
        flows = new int[N + M + 2][N + M + 2];
        booksToBuyByPerson = new int[N];
        booksHasBookStore = new int[M];
        deliveryCost = new int[M][N];

        for (int i = 0; i < N; i++) {
            booksToBuyByPerson[i] = scanner.nextInt();
        }

        for (int i = 0; i < M; i++) {
            booksHasBookStore[i] = scanner.nextInt();
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                deliveryCost[i][j] = scanner.nextInt();
            }
        }
    }

    private static void process() {
        init();
        int answer = 0;
        int[] distance = new int[N + M + 2];
        int[] previousVisitNode = new int[N + M + 2];
        int source = 0;
        int sink = N + M + 1;
        while (true) {
            Queue<Integer> nodes = new LinkedList<>();
            boolean[] isInQueue = new boolean[N + M + 2];

            Arrays.fill(distance, MAX_VALUE);
            Arrays.fill(previousVisitNode, -1);

            nodes.offer(source);
            distance[source] = 0;
            isInQueue[source] = true;
            previousVisitNode[source] = source;

            while (!nodes.isEmpty()) {
                int currentNode = nodes.poll();
                isInQueue[currentNode] = false;

                for (int adjacentNode : adjacentNodes[currentNode]) {
                    int capacity = capacities[currentNode][adjacentNode];
                    int flow = flows[currentNode][adjacentNode];
                    if (isShorterDistance(adjacentNode, currentNode, distance) && isFlowable(capacity,flow)) {
                        distance[adjacentNode] = distance[currentNode] + costs[currentNode][adjacentNode]; // 최단 거리 업데이트
                        previousVisitNode[adjacentNode] = currentNode;

                        if (!isInQueue[adjacentNode]) {
                            isInQueue[adjacentNode] = true;
                            nodes.offer(adjacentNode);
                        }
                    }
                }
            }

            if (notFoundPath(previousVisitNode, sink)) {
                break;
            }

            int flow = getMinimalFlow(previousVisitNode, source, sink);
            updateVisitedPipeFlow(previousVisitNode, source, sink, flow);
            answer += getCost(previousVisitNode, source, sink, flow);
        }

        System.out.println(answer);
    }

    private static void init() {
        costs = new int[N + M + 2][N + M + 2];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {

                /* 서점 i 에서 사람 j 에게 드는 배송비를 costs[i+1][j+M+1] 라고 하였다.
                cost 는 추후, 최단거리를 구할 때 비용으로 계산된다.
                edge 의 weight 와 같다고 생각하자.*/
                costs[i + 1][j + M + 1] = deliveryCost[i][j];
                costs[j + M + 1][i + 1] = -deliveryCost[i][j];
            }
        }

        adjacentNodes = new ArrayList[N + M + 2];
        for (int i = 0; i < N + M + 2; i++) {
            adjacentNodes[i] = new ArrayList<>();
        }

        /* source -> 서점 */
        for (int i = 0; i < M; i++) {
            capacities[0][i+1] = booksHasBookStore[i];
            adjacentNodes[0].add(i+1);
            adjacentNodes[i + 1].add(0);
        }

        /* 사람 -> sink */
        for (int i = 0; i < N; i++) {
            // sink = N+M+1
            capacities[i + M + 1][N + M + 1] = booksToBuyByPerson[i];
            adjacentNodes[i + M + 1].add(N + M + 1);
            adjacentNodes[N + M + 1].add(i + M + 1);
        }

        /* 서점 -> 사람 */
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                capacities[i][j + M + 1] = MAX_VALUE;
                adjacentNodes[i].add(j + M + 1);
                adjacentNodes[j + M + 1].add(i);
            }
        }
    }

    private static int getCost(int[] previousVisitNode, int source, int sink, int flow) {
        int cost = 0;
        for (int i = sink; i != source; i = previousVisitNode[i]) {
            int from = previousVisitNode[i];
            cost += flow * costs[from][i];
        }
        return cost;
    }

    private static void updateVisitedPipeFlow(int[] previousVisitNode, int source, int sink, int flow) {
        for (int i = sink; i != source; i = previousVisitNode[i]) {
            int from = previousVisitNode[i];
            int to = i;
            flows[from][to] += flow;
            flows[to][from] -= flow;
        }
    }

    private static int getMinimalFlow(int[] previousVisitNode, int source, int sink) {
        int min = MAX_VALUE;
        for (int i = sink; i != source; i = previousVisitNode[i]) {
            int from = previousVisitNode[i];
            int to = i;
            int residualCapacity = capacities[from][to] - flows[from][to];
            min = Math.min(min, residualCapacity);
        }
        return min;
    }

    private static boolean notFoundPath(int[] previousVisitNode, int sink) {
        return previousVisitNode[sink] == -1;
    }

    private static boolean isFlowable(int capacity, int flow) {
        return capacity - flow > 0;
    }

    // 시작점(source) ~ adjacentNode 까지 가는 거리의 최소값보다 작다면 true
    private static boolean isShorterDistance(int adjacentNode, int currentNode, int[] distance) {
        return distance[adjacentNode] > distance[currentNode] + costs[currentNode][adjacentNode];
    }
}
