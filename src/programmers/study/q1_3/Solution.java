package programmers.study.q1_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        String input = "ULURRDLLU";
        Solution s = new Solution();
        System.out.println(s.solution(input));
    }
    public int solution(String dirs) {
        int answer = 0;
        int[][] arr = new int[11][11]; // -5 부터 5까지 라 11칸이 존재

        ArrayList<Node>[][] adj = new ArrayList[11][11];

        // 좌상단을 0,0 으로 생각하면, 우리가 출발해야할 실제 좌표는 5, 5 이다
        int x = 5; // 초기 좌표
        int y = 5; // 초기 좌표
        int nx = 0; // 다음 x 좌표
        int ny = 0; // 다음 x 좌표

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                adj[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < dirs.length(); i++) {
            Node nextDirection = getDirection(dirs.charAt(i));
            nx = x + nextDirection.x;
            ny = y + nextDirection.y;
            // 0 : -5 , 10 : 5
            if (nx < 0 || ny < 0 || nx > 10 || ny > 10) {
                continue;
            }

            boolean visited = false;
            for (int j = 0; j < adj[x][y].size(); j++) {
                if(nx == adj[x][y].get(j).x && ny == adj[x][y].get(j).y) {
                    visited = true;
                    break;
                }
            }

            if(!visited) {
                adj[x][y].add(new Node(nx, ny));
            }
            x = nx;
            y = ny;
            if(visited) continue;
            answer++;
        }
        return answer;
    }

    private Node getDirection(char dir) {
        Node u = new Node(0, -1);
        Node d = new Node(0, 1);
        Node r = new Node(1, 0);
        Node l = new Node(-1, 0);
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