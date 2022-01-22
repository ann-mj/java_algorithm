package programmers.study.q3_6.solution1;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] m = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(solution.solution(m));
    }
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    class Node {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int solution(int[][] maps) {
        int answer = Integer.MAX_VALUE;
        //TODO (n-1, m-1) 에서의 dist 구하기
        int n = maps.length - 1;
        int m = maps[maps.length - 1].length - 1;
        boolean[][] visit = new boolean[n + 1][m + 1];
        int[][] dist = new int[n + 1][m + 1];
        Queue<Node> queue = new LinkedList<>();
        Node node = new Node(0, 0);
        queue.offer(node);
        dist[0][0] = 1;
        visit[0][0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int distance = dist[current.getX()][current.getY()];
            if (current.getX() == n && current.getY() == m) {
                answer = Math.min(answer, distance);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.getX() + dx[i];
                int ny = current.getY() + dy[i];
                if(nx < 0 || ny < 0 || nx > n || ny > m) continue;
                if(maps[nx][ny] == 0) continue;
                if(visit[nx][ny]) continue;
                queue.offer(new Node(nx, ny));
                dist[nx][ny] = distance + 1;
                visit[nx][ny] = true;
            }
        }
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        return answer;
    }
}