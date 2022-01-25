package programmers.study.q3_4.lecture.solution3;

import java.util.Stack;

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
        for (int j = 0; j < computers[i].length; j++) {
            if (visit[j]) continue;
            if (computers[i][j] == 1) {
                visit[j] = true;
                visitAllConnectedComputers(j, computers, visit);
            }
        }
    }
}
