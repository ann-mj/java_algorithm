package programmers.study.q1_3.q1_3_c;

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
    }

    class Path {
        private Node start, end;

        public Path(Node start, Node end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int hashCode() {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            return sb1.append(start.x).append(start.y).append(end.x).append(end.y).toString().hashCode()
                    + sb2.append(end.x).append(end.y).append(start.x).append(start.y).toString().hashCode();

        }

        @Override
        public boolean equals(Object a) {
            Path obj = (Path) a;
            return (obj.start.x == this.start.x && obj.start.y == this.start.y && obj.end.x == this.end.x && obj.end.y == this.end.y)
                    || (obj.end.x == this.start.x && obj.end.y == this.start.y && obj.start.x == this.end.x && obj.start.y == this.end.y);
        }
    }
}
