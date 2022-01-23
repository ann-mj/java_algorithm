package programmers.study.q3_4.solution2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] input = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.solution(3, input));
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];
        List<List<Integer>> graph = convertArrayToList(computers);

        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;
            bfs(visit, graph, i);
            answer++;
        }

        return answer;
    }

    private void bfs(boolean[] visit, List<List<Integer>> graph, int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visit[i] = true;
        while (!queue.isEmpty()) {
            Integer computer = queue.poll();
            for (Integer connected : graph.get(computer)) {
                if (visit[connected]) continue;
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
