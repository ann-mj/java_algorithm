package programmers.study.q1_3.solution4;

import java.util.*;

class Solution {
    static final int[] dirX = {0, 0, -1, 1}; // 순서대로 LRUD
    static final int[] dirY = {-1, 1, 0, 0};
    static final int L = 0;
    static final int R = 1;
    static final int U = 2;
    static final int D = 3;

    public int solution(String dirs) {
        Node current = new Node(0, 0);
        Node next = new Node(0, 0);
        Set<Path> pathSet = new HashSet<>();

        for (int i = 0; i < dirs.length(); i++) {
            Node dir = getDirection(dirs.charAt(i));
            next.x = current.x + dir.x;
            next.y = current.y + dir.y;
            if (next.x < -5 || next.y < -5 || next.x > 5 || next.y > 5) {
                continue;
            }

            Path path = new Path(current, next);
            pathSet.add(path);

            current.x = next.x;
            current.y = next.y;
        }
        return pathSet.size();
    }

    private Node getDirection(char dir) {
        int dirIndex = convertDirection(dir);
        return new Node(dirX[dirIndex], dirY[dirIndex]);
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

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }

        @Override
        public boolean equals(Object a) {
            Node obj = (Node) a;
            return this.x == obj.x && this.y == obj.y;
        }
    }

    class Path {
        private Node start, end;

        public Path(Node start, Node end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int hashCode() {
            return start.hashCode() + end.hashCode();

        }

        @Override
        public boolean equals(Object a) {
            Path obj = (Path) a;

            /* start 끼리 같거나, start 와 end 가 서로 같으면 동일한 경로이다. */
            return start.equals(obj.start) && end.equals(obj.end) || end.equals(obj.start) && start.equals(obj.end);
        }
    }
}
