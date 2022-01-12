package programmers.study.q1_3.q1_3_b;

class Solution {
    static final int[] dirX = {0, 0, -1, 1}; // 순서대로 LRUD
    static final int[] dirY = {-1, 1, 0, 0};
    static final int L = 0;
    static final int R = 1;
    static final int U = 2;
    static final int D = 3;

    public int solution(String dirs) {
        int answer = 0;
        boolean[][][] visit = new boolean[11][11][4];
        Node current = new Node(0, 0);
        Node next = new Node(0, 0);

        for (int i = 0; i < dirs.length(); i++) {
            int direction = convertDirection(dirs.charAt(i));
            Node dir = getDirection(direction);
            next.x = current.x + dir.x;
            next.y = current.y + dir.y;

            if (next.x < -5 || next.y < -5 || next.x > 5 || next.y > 5) {
                continue;
            }

            Path path = new Path(current, next);

            // 방문한 경로인지를 체크
            if (!path.checkVisit(visit, direction)) {
                visit = path.doVisit(visit, direction); // 해당 경로 방문처리
                answer++;
            }

            current.x = next.x;
            current.y = next.y;
        }
        return answer;
    }

    private int getReverseDirection(int dir) {
        if (Integer.compare(dir, L) == 0) {
            return R;
        } else if (Integer.compare(dir, R) == 0) {
            return L;
        } else if (Integer.compare(dir, U) == 0) {
            return D;
        } else {
            return U;
        }
    }

    private Node getDirection(int dir) {
        return new Node(dirX[dir], dirY[dir]);
    }

    private char convertDirection(char ch) {
        if (ch == 'L') {
            return L;
        } else if (ch == 'R') {
            return R;
        } else if (ch == 'U') {
            return U;
        } else {
            return D;
        }
    }

    class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Path {
        private Node start, end;

        public Path(Node start, Node end) {
            this.start = start;
            this.end = end;
        }

        // 해당 경로를 방문했는지를 확인한다.
        public boolean checkVisit(boolean[][][] visit, int direction) {
            // start 에서 dir 확인
            // end 에서 reverse dir 확인
            return visit[start.x + 5][start.y + 5][direction] && visit[end.x + 5][end.y + 5][getReverseDirection(direction)];
        }

        // 방문처리 한다.
        public boolean[][][] doVisit(boolean[][][] visit, int direction) {
            // 현재 노드 방문처리
            visit[start.x + 5][start.y + 5][direction] = true;
            // 반대 방향 노드도 방문 처리
            visit[end.x + 5][end.y + 5][getReverseDirection(direction)] = true;
            return visit;
        }
    }

}