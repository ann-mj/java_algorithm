package samsung.q23290.solution2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int M, S; // 1 ~ 10 , 1 ~ 100
    static long[][] board = new long[5][5]; // 0 : 0 냄새 안남 / 1 : 냄새 남 / 2: 냄새 남
    static int[][] dir = {
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1}};
    static int[] nextDir = new int[3]; // 상어가 3칸 이동할 칸
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static Node shark;
    static List<Integer>[][] fishes = new ArrayList[5][5];
    static List<Path> paths = new ArrayList<>();
    static Path bestPath;
    static int best = 0; // 물고기를 잡아먹을 최대 회수

    static class Node {         // 물고기, 상어의 위치
        int y;
        int x;
        int d;

        public Node(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        boolean move() {
            // 현재 가진 방향 정보로 이동시킨다.
            int ny = y + dir[d][0];
            int nx = x + dir[d][1];
            if (ny < 1 || ny > 4 || nx < 1 || nx > 4
                    || (ny == shark.y && nx == shark.x)  // 상어 있는 칸
                    || (board[ny][nx] > 0)) { // 냄새 나는 칸
                return false;
            }
            y = ny;
            x = nx;
            return true;
        }

        void changeDir(int d) {
            this.d = d;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x=" + x +
                    ", d=" + d +
                    '}';
        }
    }

    static class Path implements Comparable<Path> {
        @Override
        public String toString() {
            return "Path{" +
                    "fishCount=" + fishCount +
                    ", dirs=" + Arrays.toString(dirs) +
                    ", dir=" + dir +
                    '}';
        }

        // 상어의 이동 경로
        int fishCount; // 잡아먹을 물고기 수
        int[] dirs;
        int dir;

        public Path(int fishCount, int[] dirs, int dir) {
            this.fishCount = fishCount;
            this.dir = dir;
            this.dirs = dirs;
        }

        @Override
        public int compareTo(Path o) {
            if (fishCount == o.fishCount) {
                return dir - o.dir;
            } else {
                return o.fishCount - fishCount;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        for(int i=0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                fishes[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            fishes[y][x].add(d); // y,x 에 d 방향을 가진 물고기를 추가한다.
        }

        st = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(st.nextToken());
        int sx = Integer.parseInt(st.nextToken());
        shark = new Node(sy, sx);
        while (S-- > 0) {
            List<Node> copies = new ArrayList<>();

            // 1번 복제
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 5; j++) {
                    for(int d : fishes[i][j]) {
                        Node node = new Node(i, j, d);
                        copies.add(node);
                    }
                }
            }
            // 물고기를 한 칸 이동 시킨다.
            moveFishes();

            // 상어가 연속으로 3칸 이동
            // 상하좌우
            // 3칸을 이동한다.
            best = 0;
            bestPath = new Path(-1, null, -1);
            boolean[][] visit = new boolean[5][5];
            sharkMove(shark.y, shark.x, visit, 0);

            // path 대로 이동시키면서 물고기를 잡아먹자. -> 이후 냄새를 남기자
            for (int a : bestPath.dirs) {        // a 는 1,2,3,4 중 하나
                int ny = shark.y + dy[a - 1];
                int nx = shark.x + dx[a - 1];
                // 물고기 잡아먹자
                for(int i=1; i<5; i++) {
                    for (int j = 1; j < 5; j++) {
                        if (ny == i && nx == j) {
                            fishes[i][j].clear();
                            board[i][j] = 3;        // 냄새 남기기
                        }
                    }
                }
                // 냄새 남기자
                shark.y = ny;
                shark.x = nx;
            }

            // 냄새가 사라진다.
            for (int y = 1; y <= 4; y++) {
                for (int x = 1; x <= 4; x++) {
                    if (board[y][x] > 0) {
                        board[y][x]--;
                    }
                }
            }

            // 물고기 다시 복사
            for (int i = 0; i < copies.size(); i++) {
                Node copy = copies.get(i);
                fishes[copy.y][copy.x].add(copy.d);
            }
        }

        int ret = 0;
        for(int i=1; i<5; i++) {
            for (int j = 1; j < 5; j++) {
                ret += fishes[i][j].size();
            }
        }
        System.out.println(ret);
    }

    private static void moveFishes() {
        List<Node> fishs = new ArrayList<>();
        for(int y=1; y<5;y++) {
            for (int x = 1; x < 5; x++) {
                for(int i=0; i<fishes[y][x].size(); i++) {
                    Node fi = new Node(y, x, fishes[y][x].get(i));
                    boolean isSuccess = false;
                    while (!isSuccess) {
                        isSuccess = fi.move();
                        if (!isSuccess) {
                            fi.changeDir((fi.d + 7) % 8);
                        }
                    }
                    fishs.add(fi);
                }
                fishes[y][x].clear();
            }
        }
        for(Node f : fishs) {
            fishes[f.y][f.x].add(f.d);
        }
    }

    private static void sharkMove(int sy, int sx, boolean[][] visit, int cnt) {
        if (cnt == 3) {
            int toEat = 0;                  // 물고기가 몇마리 인지 확인
            int ny = shark.y;
            int nx = shark.x;
            for (int d : nextDir) {         // nextDir 안에는 1,2,3,4 상좌하우
                ny = ny + dy[d - 1];
                nx = nx + dx[d - 1];
                if (ny < 1 || ny > 4 || nx < 1 || nx > 4) {
                    return;
                }
                for(int i=1; i<5; i++) {
                    for (int j = 1; j < 5; j++) {
                        if(ny == i && nx == j) {
                            toEat += fishes[i][j].size();
                        }
                    }
                }
            }
            if (best <= toEat) {
                best = toEat;
                int[] nextDirec = new int[3];
                System.arraycopy(nextDir,0,nextDirec, 0,3);
                Path path = new Path(best, nextDirec, convertDir(nextDirec));
                if(bestPath.compareTo(path) > 0) {
                    bestPath = path;
                }
            }
            return;
        }

        for (int i = 1; i <= 4; i++) {
            // vis 체크
            int ny = sy + dy[i - 1];
            int nx = sx + dx[i - 1];
            if(ny < 1 || ny > 4 || nx < 1 || nx >4) continue;
            if (visit[ny][nx]) continue;
            visit[ny][nx] = true;
            nextDir[cnt] = i;
            sharkMove(ny, nx, visit, cnt + 1);
            visit[ny][nx] = false;
        }
    }

    private static int convertDir(int[] nextDir) {
        int res = 0;
        int mul = 100;
        for (int i = 0; i < 3; i++) {
            res += mul * nextDir[i];
            mul /= 10;
        }
        return res;
    }
}
