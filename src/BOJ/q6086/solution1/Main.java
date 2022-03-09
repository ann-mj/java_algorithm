package BOJ.q6086.solution1;

import java.util.*;

/**
 * 포드-폴커슨 알고리즘 : Source(시작점) → Sink(도착점) 으로동시에 보낼 수 있는,데이터나 사물의 최대 양을 구하는 알고리즘
 * Capacity: 용량 (간선에서 소화 가능한 최대 양 or 값)
 * Flow: 유량 (간선에서 용량을 점유하고 있는, 사용하고있는 양 or 값)
 * c(a, b): 정점 a 에서 b로, 소화 가능한(남은) 용량 값
 * f(a, b): 정점 a 에서 b로, 사용하고 있는(쓴) 유량 값
 * Residual Capacity : 잔여 용량 ( c(a,b) - f(a,b) )
 */
public class Main {
    public static void main(String[] args) {
        input();
        process();
    }

    static final int NUM_OF_ALPHABETS = 52;
    static int[][] capacities = new int[NUM_OF_ALPHABETS][NUM_OF_ALPHABETS];
    static int[][] flows = new int[NUM_OF_ALPHABETS][NUM_OF_ALPHABETS];

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            int from = changeAlphabetToInt(scanner.next().charAt(0));
            int to = changeAlphabetToInt(scanner.next().charAt(0));
            int capacity = scanner.nextInt();

            /* A -> B 를 연결하는 간선이 2개 이상 존재할 수 있다. 파이프가 병렬로 연결된 경우는 각 용량을 더해서 1개로 합칠 수 있다. 그래서 누적 합을 계산한다. */
            capacities[from][to] += capacity;
            /* 양방향으로 흐를 수 있다. */
            capacities[to][from] += capacity;
        }
    }

    private static int changeAlphabetToInt(char ch) {
        if (Character.isLowerCase(ch)) {
            return ch - 'A' - 6;
        }
        return ch - 'A';
    }

    private static void process() {
        int answer = 0;
        int[] previousVisitedPipe = new int[NUM_OF_ALPHABETS];
        int source = 0; // 'A'
        int sink = 25; // 'Z'

        while (true) {
            Arrays.fill(previousVisitedPipe, -1);

            Queue<Integer> nodes = new LinkedList<>();
            nodes.offer(source);
            previousVisitedPipe[source] = source;

            while (!nodes.isEmpty()) {
                int currentNode = nodes.poll();
                for (int i = 0; i < NUM_OF_ALPHABETS; i++) {
                    int capacity = capacities[currentNode][i];
                    int flow = flows[currentNode][i];
                    if (!isFirstVisit(previousVisitedPipe[i])) continue;
                    if (!isFlowable(capacity, flow)) continue;
                    nodes.offer(i);
                    previousVisitedPipe[i] = currentNode;
                }
            }

            if (notFoundPath(previousVisitedPipe, sink)) {
                break;
            }

            // 최소 flow 를 찾자.
            int flow = getMinimalFlow(previousVisitedPipe, source, sink);
            updateVisitedPipeFlow(previousVisitedPipe, source, sink, flow);

            answer += flow;
        }
        System.out.println(answer);
    }

    private static boolean notFoundPath(int[] previousVisitedPipe, int sink) {
        return previousVisitedPipe[sink] == -1;
    }

    private static boolean isFirstVisit(int previousVisitedPipe) {
        return previousVisitedPipe == -1;
    }

    private static void updateVisitedPipeFlow(int[] previousVisitedPipe, int source, int sink, int flow) {
        /* i = source 라면, 무한 반복이 된다. 주의할 것 */
        for (int i = sink; i != source; i = previousVisitedPipe[i]) {
            int from = previousVisitedPipe[i];
            int to = i;
            flows[from][to] += flow;
            flows[to][from] -= flow;
        }
    }

    private static int getMinimalFlow(int[] previousVisitedPipe, int source, int sink) {
        int min = Integer.MAX_VALUE;
        for (int i = sink; i != source; i = previousVisitedPipe[i]) {
            int from = previousVisitedPipe[i];
            int residualCapacity = capacities[from][i] - flows[from][i];
            min = Math.min(min, residualCapacity);
        }
        return min;
    }

    private static boolean isFlowable(int capacity, int flow) {
        return capacity - flow > 0;
    }
}
