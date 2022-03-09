package BOJ.q1194.solution1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M; // N : 세로, M : 가로
    static int NUM_OF_BITS_BY_DOOR = 64; // 2의 6제곱
    static char[][] map;
    static boolean[][][] visit;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int answer = -1;

    static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];
        visit = new boolean[N][M][NUM_OF_BITS_BY_DOOR];
        for (int i = 0; i < N; i++) {
            String line = scanner.next();
            for (int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        Node start = findStartNode(map);
        queue.add(start);
        visit[start.x][start.y][0] = true; // 열쇠가 하나도 없는 상태

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isNotInsideMap(nx, ny)) continue;
                if (isVisited(nx, ny, current.keys)) continue;
                if (map[nx][ny] == '#') {
                    continue;
                }
                if (map[nx][ny] == '1') {
                    answer = current.count + 1;
                    System.out.println(answer);
                    return;
                }

                if (map[nx][ny] == '.' || map[nx][ny] == '0') {
                    queue.add(new Node(nx, ny, current.count + 1, current.keys));
                    visit[nx][ny][current.keys] = true;
                }

                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    int nextKey = getKey(current.keys, map[nx][ny]);
                    queue.add(new Node(nx, ny, current.count + 1, nextKey));
                    visit[nx][ny][nextKey] = true;
                }

                if (canOpenDoor(nx, ny, current.keys)) {
                    queue.add(new Node(nx, ny, current.count + 1, current.keys));
                    visit[nx][ny][current.keys] = true;
                }
            }
        }
        System.out.println(answer);

    }

    private static boolean canOpenDoor(int nx, int ny, int keys) {
        if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
            return ((keys >> (map[nx][ny] - 'A')) & 1) == 1;
        }
        return false;
    }

    private static int getKey(int keys, char c) {
        return keys | 1 << (c - 'a');
    }

    private static boolean isVisited(int nx, int ny, int keys) {
        return visit[nx][ny][keys];
    }

    private static boolean isNotInsideMap(int x, int y) {
        return y < 0 || x < 0 || x >= N || y >= M;
    }

    private static Node findStartNode(char[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') {
                    return new Node(i, j, 0, 0);
                }
            }
        }
        return null;
    }

    static class Node {
        int x;
        int y;
        int count; // 이동 횟수
        int keys; // 가지고 있는 열쇠를 비트로

        public Node(int x, int y, int count, int keys) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.keys = keys;
        }
    }

    public static void main(String[] args) {
        input();
        bfs();
    }
}
