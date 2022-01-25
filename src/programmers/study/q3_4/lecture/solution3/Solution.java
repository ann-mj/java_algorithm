package programmers.study.q3_4.lecture.solution2;

import java.util.LinkedList;
import java.util.Queue;
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
        Stack<Integer> stack = new Stack<>();
        stack.push(i);
        while (!stack.isEmpty()) {
            int c = stack.pop();

            for (int j = 0; j < computers[c].length; j++) {
                if (visit[j]) continue;
                if (computers[c][j] == 1) {
                    visit[j] = true;
                    stack.push(j);
                }
            }
        }
    }
}
