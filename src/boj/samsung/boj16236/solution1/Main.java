package boj.samsung.boj16236.solution1;


import java.util.*;

public class Main {

    static int N;
    static int[][] space;
    static int size = 2;
    static int remainBigger = 2;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static Pair shark;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        input();
        solution();
        init();
    }

    static void init() {
        N = 0;
        size = 2;
        remainBigger = 2;
    }

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        space = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                space[i][j] = scan.nextInt();
                if (space[i][j] == 9) {
                    shark = new Pair(i, j, 0);
                }
            }
        }
    }

    static void solution() {
        Queue<Pair> Q = new LinkedList<>();
        Q.add(shark);
        int answer = 0;

        while (true) {
            PriorityQueue<Pair> fish = new PriorityQueue<>();
            boolean[][] visit = new boolean[N][N];
            visit[shark.x][shark.y] = true; // 이 부분이 제일 중요하다.

            while (!Q.isEmpty()) {
                Pair poll = Q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = poll.x + dx[i];
                    int ny = poll.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (visit[nx][ny]) continue;

                    /* 이동할 수 있는 위치인지 확인 */
                    if (space[nx][ny] > size) continue;

                    Pair nextFish = new Pair(nx, ny, poll.distance + 1);
                    Q.add(nextFish);
                    visit[nx][ny] = true;

                    /* 이동하다가, 물고기가 나오면 잡아먹기 위해 List에 넣어둔다. */
                    if (0 < space[nx][ny] && space[nx][ny] < size) {
                        fish.add(nextFish);
                    }
                }
            }

            if (fish.isEmpty()) {
                System.out.println(answer);
                return;
            }
            Pair targetFish = fish.poll();

            answer += targetFish.distance;
            space[shark.x][shark.y] = 0;

            // 물고기 위치로 이동
            shark.x = targetFish.x;
            shark.y = targetFish.y;
            space[targetFish.x][targetFish.y] = 9;

            // 먹는다
            remainBigger--;
            if (remainBigger == 0) {
                size++;
                remainBigger = size;
            }
            Q.add(shark);
        }
    }

    static class Pair implements Comparable<Pair> {
        int x;  // 행
        int y;  // 열
        int distance;

        public Pair(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.distance == o.distance) {
                if (this.x == o.x) {
                    return this.y - o.y; // 오름차순 정렬하면 됨. 좌측이 우선이므로
                }
                return this.x - o.x;
            }
            return this.distance - o.distance;
        }
    }
}