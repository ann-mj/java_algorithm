package programmers.study.q1_3.q1_3_a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

            // 0 : -5 , 10 : 5
            if (next.x < -5 || next.y < -5 || next.x > 5 || next.y > 5) {
                continue;
            }

            int reverseDirection = getReverseDirection(direction);

            // 인접한 노드인지를 체크
            if(!checkVisit(visit, current, next, direction, reverseDirection)) {
                // 현재 노드 방문처리
                visit[current.x + 5][current.y + 5][direction] = true;
                // 반대 방향 노드도 방문 처리
                visit[next.x + 5][next.y + 5][reverseDirection] = true;
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

    private boolean checkVisit(boolean[][][] visit, Node current, Node next, int dir, int reverseDir) {
        // 5를 더한 이유 : -5 는 배열 인덱스로 접근안됨.
        return visit[current.x + 5][current.y + 5][dir] && visit[next.x + 5][next.y + 5][reverseDir];
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
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void changeNode(Node node) {
            this.x = node.x;
            this.y = node.y;
        }
    }
}
