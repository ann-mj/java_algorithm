package programmers.study.q1_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        Map<Character, Integer> D = new HashMap<>();
        D.put('L', 0);
        D.put('R', 1);
        D.put('U', 2);
        D.put('D', 3);
        boolean[][][] visit = new boolean[11][11][4];
        int curX = 0, curY = 0, nx, ny;
        for (int i = 0; i < dirs.length(); i++) {
            boolean isVisit;
            Node dir = getDirection(dirs.charAt(i));
            nx = curX + dir.x;
            ny = curY + dir.y;
            // 0 : -5 , 10 : 5
            if (nx < -5 || ny < -5 || nx > 5 || ny > 5) {
                continue;
            }

            // 인접한 노드인지를 체크
            isVisit = checkVisit(visit, curX, curY, nx, ny, D.get(dirs.charAt(i)), D.get(getReverseDirection(dirs.charAt(i))));

            // 현재 노드 방문처리
            if(!isVisit) {
                visit[curX + 5][curY + 5][D.get(dirs.charAt(i))] = true;
                // 반대 방향 구하기
                char reverseDirection = getReverseDirection(dirs.charAt(i));
                visit[nx + 5][ny + 5][D.get(reverseDirection)] = true;
                answer++;
            }
            curX = nx;
            curY = ny;
        }
        return answer;
    }

    private char getReverseDirection(char charAt) {
        if (Character.compare('U', charAt) == 0) {
            return 'D';
        } else if (Character.compare('D', charAt) == 0) {
            return 'U';
        } else if (Character.compare('R', charAt) == 0) {
            return 'L';
        } else {
            return 'R';
        }
    }

    private boolean checkVisit(boolean[][][] visit, int curX, int curY, int nx, int ny, int dir, int reverseDir) {
        // 5를 더한 이유 : -5 는 배열 인덱스로 접근안됨.
        return visit[curX + 5][curY + 5][dir] && visit[nx + 5][ny + 5][reverseDir];
    }

    private Node getDirection(char dir) {
        Node u = new Node(0, -1);
        Node d = new Node(0, 1);
        Node r = new Node(-1, 0);
        Node l = new Node(1, 0);
        Map<Character, Node> map = new HashMap<>();
        map.put('U', u);
        map.put('D', d);
        map.put('R', r);
        map.put('L', l);

        return map.get(dir);
    }

    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}