package programmers.study.q3_4.solution1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] input = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.solution(3,input));
    }
    static List<List<Integer>> graph;
    static boolean[] visit;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visit = new boolean[n];
        graph = convertArrayToList(computers);

        for (int i = 0; i < n; i++) {
            if(visit[i]) continue;
            bfs(i);
            answer++;
        }

        return answer;
    }

    private void bfs(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visit[i] = true;
        while (!queue.isEmpty()) {
            Integer computer = queue.poll();
            for (Integer connected : graph.get(computer)) {
                if(visit[connected]) continue;
                queue.add(connected);
                visit[connected] = true;
            }
        }
    }

    private List<List<Integer>> convertArrayToList(int[][] computers) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < computers.length; i++) {
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers.length; j++) {
                if (i == j) continue;
                if (computers[i][j] == 1) {
                    result.get(i).add(j);
                }
            }
        }
        return result;
    }
}
