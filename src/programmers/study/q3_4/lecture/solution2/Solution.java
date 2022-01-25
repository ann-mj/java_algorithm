package programmers.study.q3_4.lecture.solution1;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;
            visitAllConnectedComputers(i, computers, visit);
            answer++;
        }
        return answer;
    }

    private void visitAllConnectedComputers(int i, int[][] computers, boolean[] visit) {
        // BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        while (!queue.isEmpty()) {
            int c = queue.poll();

            for (int j = 0; j < computers[c].length; j++) {
                if (visit[j]) continue;
                if (computers[c][j] == 1) {
                    visit[j] = true;
                    queue.offer(j);
                }
            }
        }
    }
}
